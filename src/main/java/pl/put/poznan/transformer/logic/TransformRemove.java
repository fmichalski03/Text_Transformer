package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.*;

public class TransformRemove extends TextTransformerDecorator{

	private static final Logger logger = LoggerFactory.getLogger(TransformRemove.class);
    public TransformRemove(TextTransformerInterface transformer) {
        super(transformer);
    }

    public String transform(String text){
        String transformed = super.transform(text);
        String decorated = decorate(transformed);
        logger.debug(String.format("Transformed string %s to %s", text, decorated));
        return decorated;
    }

    @Override
    public String decorate (String text){
        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();
        String previousWord = "";
        
        for (String word : words) {
            if (!word.equals(previousWord)) {
                if (result.length() > 0) {
                    result.append(" ");
                }
                result.append(word);
            }
            previousWord = word;
        }
        
        return result.toString();
    }
}

