package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static java.lang.Character.isUpperCase;

public class TransformUpper extends TextTransformerDecorator{
    private static final Logger logger = LoggerFactory.getLogger(TransformUpper.class);
    public TransformUpper(TextTransformerInterface transformer) {
        super(transformer);
    }

    public String transform(String text){
        String transformed = super.transform(text);
        String decorated = decorate(transformed);
        logger.debug(String.format("Transformed string %s to %s", text, decorated));
        return decorated;
    }
    @Override
    public String decorate(String text) {
        return text.toUpperCase();
    }
}