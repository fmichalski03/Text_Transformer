package pl.put.poznan.transformer.logic;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class TextTransformer {

    private final String[] transforms;

    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }

    public String transform(String text){
        String result = text;
        for (String transform : transforms) {
            if ("upper".equalsIgnoreCase(transform)) {
                result = result.toUpperCase();
            } else if ("reverse".equalsIgnoreCase(transform)) {
                result = new StringBuilder(result).reverse().toString();
            }
            // Dodaj inne transformacje tutaj
        }
        return result;
    }
}
