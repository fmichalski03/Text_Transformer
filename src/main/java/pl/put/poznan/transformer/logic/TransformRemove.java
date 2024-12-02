package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.*;

/**
* A class that decorates text by removing repeated twice or more times words in sentence.
* This class extends {@link TextTransformerDecorator} and removes repeated words in sequence.
*
* Example:
* <ul>
*   <li>Input: "this is is a test test"</li>
*   <li>Output: "this is a test"</li>
* </ul>
*
* @author      Filip Michalski
* @since       1.2
*/

public class TransformRemove extends TextTransformerDecorator{

	private static final Logger logger = LoggerFactory.getLogger(TransformRemove.class);

    /**
    * Constructs a {@code TransformToLatex} object with a reference to another {@link TextTransformerInterface}.
    *
    * @param transformer the base transformer to be decorated
    */
    public TransformRemove(TextTransformerInterface transformer) {
        super(transformer);
    }
    /**
     * Transforms the given text, removing multiple occurence of the same word by decorating
     * the output of the base transformer, logging primary and decorated text.
     * 
     * @param text the input text to transform
     * @return the transformed text without repetitions
     */

    public String transform(String text){
        String transformed = super.transform(text);
        String decorated = decorate(transformed);
        logger.debug(String.format("Transformed string %s to %s", text, decorated));
        return decorated;
    }

    /**
     * Removes consecutive repeated words from the given text.
     * Words are split by whitespace, and only unique sequential words are preserved.
     * 
     * 
     * @param text the input text to process
     * @return the text with consecutive repeated words removed
     */

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

