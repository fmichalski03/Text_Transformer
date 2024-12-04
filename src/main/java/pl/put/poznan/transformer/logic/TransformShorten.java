package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class that shortens commonly used phrases in Polish to their abbreviations.
 * It extends the {@link TextTransformerDecorator} class and overrides the {@code transform} and {@code decorate} methods.
 *
 * <p>This class performs a text transformation that replaces full phrases with their abbreviated forms,
 * while preserving capitalization where appropriate. The transformation is based on a predefined set
 * of phrases and their corresponding abbreviations.</p>
 *
 * <p>Example transformations:</p>
 * <ul>
 *   <li>"profesor" -> "prof."</li>
 *   <li>"na przykład" -> "np."</li>
 *   <li>"Ciąg dalszy nastąpi" -> "Cdn."</li>
 *   <li>"Aleja Jana Pawła II" -> "Al. Jana Pawła II"</li>
 * </ul>
 *
 * <p>Note: This class assumes that input text is properly formatted in Polish.</p>
 *
 * @author  Adam Pilawski
 * @since 1.4
 */

public class TransformShorten extends TextTransformerDecorator{
    /**
     * Logger instance for logging events and messages within the {@code TransformShorten} class.
     */
    private static final Logger logger = LoggerFactory.getLogger(TransformShorten.class);

    /**
     * Constructs a {@code TransformShorten} object with the specified {@link TextTransformerInterface}.
     *
     * @param transformer the {@link TextTransformerInterface} to be used for text transformation
     */
    public TransformShorten(TextTransformerInterface transformer) {
        super(transformer);
    }

    /**
     * Transforms the given text by applying a series of transformations.
     *
     * @param text the input text to be transformed
     * @return the transformed text with abbreviations applied
     */
    public String transform(String text){
        String transformed = super.transform(text);
        String decorated = decorate(transformed);
        logger.debug(String.format("Transformed string %s to %s", text, decorated));
        return decorated;
    }

    /**
     * Capitalizes the first letter of the input text and converts the rest to lowercase.
     *
     * @param text the input text to be capitalized
     * @return the text with the first letter capitalized
     */
    private String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    /**
     * Shortens commonly used phrases in the input text into their standard abbreviations.
     * Both lowercase and capitalized abbreviations are processed.
     *
     * @param text the input text to be transformed
     * @return the text with shortened phrases
     */
    @Override
    public String decorate(String text) {
        String[][] abbreviations = {
                {"profesor", "prof."},
                {"doktor", "dr"},
                {"na przykład", "np."},
                {"i tak dalej", "itd."},
                {"i tym podobne", "itp."},
                {"ciąg dalszy", "cd."},
                {"ciąg dalszy nastąpi", "cdn."},
                {"lekarz", "lek."},
                {"licencjat", "lic."},
                {"magister", "mgr"},
                {"numer", "nr"},
                {"strona", "str."},
                {"to jest", "tj."},
                {"to znaczy", "tzn."},
                {"tak zwany", "tzw."},
                {"ulica", "ul."},
                {"aleja", "al."}
        };
        for(int i = 0; i < abbreviations.length; i++) {
            text = text.replace(abbreviations[i][0], abbreviations[i][1]);
            text = text.replace(capitalize(abbreviations[i][0]), capitalize(abbreviations[i][1]));
        }
        return text;
    }
}
