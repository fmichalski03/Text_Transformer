package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TransformRemoveTest {

    private TextTransformerInterface transformer;

    @BeforeEach
    public void setUp() {
        transformer = new TransformRemove(new TextTransformer());
    }

    @Test
    @DisplayName("Pojedyncze powtórzenie słowa")
    public void singleRepetitionTest() {
        transformer = new TransformRemove(transformer);
        Assertions.assertEquals("do na tu", transformer.transform("do do na na tu"));
    }

    @Test
    @DisplayName("Kilkukrotne powtórzenie słów")
    public void consecutiveRepetitionTest() {
        transformer = new TransformRemove(transformer);
        Assertions.assertEquals("do na tu", transformer.transform("do do do do na na na tu"));
    }
}