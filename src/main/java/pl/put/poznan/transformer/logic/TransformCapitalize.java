package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static java.lang.Character.isUpperCase;

public class TransformCapitalize extends TextTransformerDecorator{
    private static final Logger logger = LoggerFactory.getLogger(TransformCapitalize.class);
    public TransformCapitalize(TextTransformerInterface transformer) {
        super(transformer);
    }

    public String transform(String text){
        String transformed = super.transform(text);
        String decorated = decorate(transformed);
        logger.debug(String.format("Transformed string %s to %s", text, decorated));
        return decorated;
    }

    private String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    @Override
    public String decorate(String text) {
        return capitalize(text);
    }
}