package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static java.lang.Character.isUpperCase;

public class TransformInverse extends TextTransformerDecorator{
    private static final Logger logger = LoggerFactory.getLogger(TransformInverse.class);
    public TransformInverse(TextTransformerInterface transformer) {
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