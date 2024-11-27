package pl.put.poznan.transformer.logic;

public class TextTransformResponse {
    private String transformedText;

    public TextTransformResponse(String transformedText) {
        this.transformedText = transformedText;
    }

    public String getTransformedText() {
        return transformedText;
    }

    public void setTransformedText(String transformedText) {
        this.transformedText = transformedText;
    }
}

