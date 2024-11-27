package pl.put.poznan.transformer.logic;

public class TextTransformRequest {
    private String text;
    private String[] transforms;

    // Gettery i settery
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getTransforms() {
        return transforms;
    }

    public void setTransforms(String[] transforms) {
        this.transforms = transforms;
    }
}

