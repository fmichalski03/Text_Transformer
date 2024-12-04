package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class that reverses the input text while preserving the original capitalization of each character on specific positions.
 * It extends the {@link TextTransformerDecorator} class and overrides the {@code transform} and {@code decorate} methods.
 *
 * <p>This class performs a text transformation that reverses the string and ensures the capitalization
 * of characters remains consistent on specific position in the input text.</p>
 *
 * <p>Example transformations:</p>
 * <ul>
 *   <li>"Hello" -> "Olleh"</li>
 *   <li>"sIemA" -> "aMeiS"</li>
 * </ul>
 *
 * @author  Adam Pilawski
 * @since 1.0
 */
public class TransformInverse extends TextTransformerDecorator{
    /**
     * Logger instance for logging events and messages within the {@code TransformShorten} class.
     */
    private static final Logger logger = LoggerFactory.getLogger(TransformInverse.class);
    /**
     * Constructs a {@code TransformInverse} object with the specified {@link TextTransformerInterface}.
     *
     * @param transformer the {@link TextTransformerInterface} to be used for text transformation
     */
    public TransformInverse(TextTransformerInterface transformer) {
        super(transformer);
    }

    /**
     * Transforms the given text by reversing its content while preserving the capitalization of characters on specific positions.
     *
     * @param text the input text to be transformed
     * @return the transformed text with reversed characters and original capitalization
     */
    public String transform(String text){
        String transformed = super.transform(text);
        String decorated = decorate(transformed);
        logger.debug(String.format("Transformed string %s to %s", text, decorated));
        return decorated;
    }

    /**
     * Reverses the input text and adjusts capitalization to match the original positions of characters.
     *
     * @param text the input text to be reversed
     * @return the reversed text with adjusted capitalization
     */
    @Override
    public String decorate(String text) {
        char[] original = text.toCharArray();
        String result = new StringBuilder(text).reverse().toString();
        char[] reversed = result.toCharArray();

        for (int i = 0; i < original.length; i++) {
            if (Character.isUpperCase(original[i])) {
                reversed[i] = Character.toUpperCase(reversed[i]);
            } else if (Character.isLowerCase(original[i])) {
                reversed[i] = Character.toLowerCase(reversed[i]);
            }
        }
        result = new String(reversed);
        return result;
    }
}