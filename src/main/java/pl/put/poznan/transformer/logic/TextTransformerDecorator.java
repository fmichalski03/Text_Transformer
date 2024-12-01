package pl.put.poznan.transformer.logic;

public abstract class TextTransformerDecorator implements TextTransformerInterface {
    private TextTransformerInterface transformer;

    public TextTransformerDecorator(TextTransformerInterface transformer){
        this.transformer = transformer;
    }

    @Override
    public String transform(String text){
        String transformed = transformer.transform(text);
        return transformed;
    }

    public abstract String decorate(String text);
}