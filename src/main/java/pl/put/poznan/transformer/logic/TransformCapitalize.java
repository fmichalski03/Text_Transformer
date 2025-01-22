package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class that decorates text by capitalizing the first letter of each word.
 * This class extends {@link TextTransformerDecorator} and modifies the text so that every word starts with an uppercase letter.
 *
 * Example:
 * <ul>
 *   <li>Input: "this is a test"</li>
 *   <li>Output: "This Is A Test"</li>
 * </ul>
 *
 * @author      Paweł Mazurkiewicz
 * @since       2.0
 */
public class TransformCapitalize extends TextTransformerDecorator{

    /**
     * Logger instance for logging events and messages within the {@code TransformCapitalize} class.
     */
    private static final Logger logger = LoggerFactory.getLogger(TransformCapitalize.class);

    /**
     * Constructs a {@code TransformCapitalize} object with a reference to another {@link TextTransformerInterface}.
     *
     * @param transformer the base transformer to be decorated
     */
    public TransformCapitalize(TextTransformerInterface transformer) {
        super(transformer);
    }

    /**
     * Transforms the given text by capitalizing each word, decorating
     * the output of the base transformer, and logging the primary and decorated text.
     *
     * @param text the input text to transform
     * @return the transformed text with each word capitalized
     */
    public String transform(String text){
        String transformed = super.transform(text);
        String decorated = decorate(transformed);
        logger.debug(String.format("Transformed string %s to %s", text, decorated));
        return decorated;
    }

    /**
     * Capitalizes the first letter of each word in the given text.
     * Words are defined as sequences of characters separated by whitespace.
     *
     * @param text the input text to process
     * @return the text with each word capitalized
     */
    private String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        String[] words = text.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }
        String result = String.join(" ", words);
        return result;
    }

    /**
     * Applies the capitalize transformation to the given text.
     *
     * @param text the input text to process
     * @return the text with each word capitalized
     */
    @Override
    public String decorate(String text) {
        return capitalize(text);
    }
}
