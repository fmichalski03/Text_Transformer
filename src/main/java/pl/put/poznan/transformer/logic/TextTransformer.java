package pl.put.poznan.transformer.logic;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class TextTransformer {

    private final String[] transforms;

    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }

    private String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    public String transform(String text){
        String result = text;
        for (String transform : transforms) {
            if ("upper".equalsIgnoreCase(transform)) {
                result = result.toUpperCase();
            } else if ("reverse".equalsIgnoreCase(transform)) {
                result = new StringBuilder(result).reverse().toString();
            } else if ("lower".equalsIgnoreCase(transform)) {
                result = result.toLowerCase();
            } else if ("capitalize".equalsIgnoreCase(transform)) {
                result = capitalize(result);
            } 
        }
        return result;
    }
}
