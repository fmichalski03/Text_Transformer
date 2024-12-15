package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class that decorates text by transforming it into a LaTeX-compatible format.
 * This class extends {@link TextTransformerDecorator} and replaces special characters
 * with their LaTeX-safe equivalents.

 * @author      Filip Michalski
 * @since       1.2
 */
public class TransformToLatex extends TextTransformerDecorator {

    /**
     * Logger instance for logging events and messages within the {@code TransformShorten} class.
     */
    private static final Logger logger = LoggerFactory.getLogger(TransformToLatex.class);

    /**
     * Constructs a {@code TransformToLatex} object with a reference to another {@link TextTransformerInterface}.
     *
     * @param transformer the base transformer to be decorated
     */
    public TransformToLatex(TextTransformerInterface transformer) {
        super(transformer);
    }

    /**
     * Transforms the given text into a LaTeX-compatible format by decorating
     * the output of the base transformer.
     *
     * @param text the input text to transform
     * @return the transformed text with LaTeX-safe formatting
     */
    @Override
    public String transform(String text) {
        String transformed = super.transform(text);
        String decorated = decorate(transformed);
        logger.debug(String.format("Transformed string %s to %s", text, decorated));
        return decorated;
    }

    /**
     * Replaces special characters in the input text with their LaTeX-safe equivalents.
     *
     * @param text the input text to process
     * @return the text with special characters replaced for LaTeX compatibility
     */
    @Override
    public String decorate(String text) {
        text = text.replace("&", "\\&");
        text = text.replace("$", "\\$");
        text = text.replace("%", "\\%");
        text = text.replace("#", "\\#");
        text = text.replace("_", "\\_");
        text = text.replace("{", "\\{");
        text = text.replace("}", "\\}");
        text = text.replace("~", "\\~{}");
        text = text.replace("^", "\\^{}");
        text = text.replace("'", "\\'");
        text = text.replace("\"", "\\\"");
        
        return text;
    }
}

