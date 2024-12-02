package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransformLower extends TextTransformerDecorator{
    private static final Logger logger = LoggerFactory.getLogger(TransformLower.class);
    public TransformLower(TextTransformerInterface transformer) {
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
        return text.toLowerCase();
    }
}