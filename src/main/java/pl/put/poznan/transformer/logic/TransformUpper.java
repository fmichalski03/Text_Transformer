package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class that decorates text by converting it to uppercase.
 * This class extends {@link TextTransformerDecorator} and converts all letters in the input text to uppercase.
 *
 * Example:
 * <ul>
 *   <li>Input: "this is a test"</li>
 *   <li>Output: "THIS IS A TEST"</li>
 * </ul>
 *
 * @author      Paweł Mazurkiewicz
 * @since       2.0
 */
public class TransformUpper extends TextTransformerDecorator{

    /**
     * Logger instance for logging events and messages within the {@code TransformUpper} class.
     */
    private static final Logger logger = LoggerFactory.getLogger(TransformUpper.class);

    /**
     * Constructs a {@code TransformUpper} object with a reference to another {@link TextTransformerInterface}.
     *
     * @param transformer the base transformer to be decorated
     */
    public TransformUpper(TextTransformerInterface transformer) {
        super(transformer);
    }

    /**
     * Transforms the given text by converting it to uppercase, decorating
     * the output of the base transformer, and logging the primary and decorated text.
     *
     * @param text the input text to transform
     * @return the transformed text in uppercase
     */
    public String transform(String text){
        String transformed = super.transform(text);
        String decorated = decorate(transformed);
        logger.debug(String.format("Transformed string %s to %s", text, decorated));
        return decorated;
    }

    /**
     * Converts the given text to uppercase.
     *
     * @param text the input text to process
     * @return the text converted to uppercase
     */
    @Override
    public String decorate(String text) {
        return text.toUpperCase();
    }
}
