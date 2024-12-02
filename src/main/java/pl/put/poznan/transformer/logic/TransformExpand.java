package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class that decorates text by expanding common Polish abbreviations into their full forms.
 * This class extends {@link TextTransformerDecorator} and performs text transformation
 * by replacing predefined abbreviations with their expanded equivalents.
 *
 * Example:
 * <ul>
 *   <li>Input: "prof. dr np. al. 1"</li>
 *   <li>Output: "profesor doktor na przykład aleja 1"</li>
 * </ul>
 *
 * @author      [Paweł Mazurkiewicz]
 * @since       1.2
 */
public class TransformExpand extends TextTransformerDecorator{
    private static final Logger logger = LoggerFactory.getLogger(TransformExpand.class);

     /**
     * Constructs a {@code TransformExpand} object with a reference to another {@link TextTransformerInterface}.
     *
     * @param transformer the base transformer to be decorated
     */
    public TransformExpand(TextTransformerInterface transformer) {
        super(transformer);
    }

     /**
     * Transforms the given text by expanding abbreviations using the base transformer's output,
     * logging the original and transformed text.
     *
     * @param text the input text to transform
     * @return the transformed text with abbreviations expanded
     */
    public String transform(String text){
        String transformed = super.transform(text);
        String decorated = decorate(transformed);
        logger.debug(String.format("Transformed string %s to %s", text, decorated));
        return decorated;
    }
     /**
     * Capitalizes the first letter of the text and converts the rest to lowercase.
     * Used for handling abbreviations that start with a capital letter.
     *
     * @param text the input text to capitalize
     * @return the capitalized text
     */
    private String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    /**
     * Replaces abbreviations in the text with their expanded forms.
     * Both lowercase and capitalized abbreviations are processed.
     *
     * @param text the input text to process
     * @return the text with abbreviations replaced by their full forms
     */
    @Override
    public String decorate(String text) {
        String[][] abbreviations = {
                {"prof.", "profesor"},
                {"dr", "doktor"},
                {"np.", "na przykład"},
                {"itd.", "i tak dalej"},
                {"itp.", "i tym podobne"},
                {"cd.", "ciąg dalszy"},
                {"cdn.", "ciąg dalszy nastąpi"},
                {"lek.", "lekarz"},
                {"lic.", "licencjat"},
                {"mgr", "magister"},
                {"nr", "numer"},
                {"str.", "strona"},
                {"tj.", "to jest"},
                {"tzn.", "to znaczy"},
                {"tzw.", "tak zwany"},
                {"ul.", "ulica"},
                {"al.", "aleja"}
        };
        for(int i = 0; i < abbreviations.length; i++) {
            text = text.replace(abbreviations[i][0], abbreviations[i][1]);
            text = text.replace(capitalize(abbreviations[i][0]), capitalize(abbreviations[i][1]));
        }
        return text;
    }
}
