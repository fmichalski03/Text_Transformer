package pl.put.poznan.transformer.logic;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */

public class TextTransformer implements TextTransformerInterface {

    public TextTransformer(){
    }

    public String transform(String text){
        return text;
    }
}