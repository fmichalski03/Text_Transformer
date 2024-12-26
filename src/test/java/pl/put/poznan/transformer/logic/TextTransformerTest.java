package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TextTransformerTest {

    private TextTransformerInterface transformer;

    @Test
    @DisplayName("Test bazowy transformacji")
    public void baseTransformerTest(){
        transformer = new TextTransformer();
        Assertions.assertEquals("test", transformer.transform("test"));
        Assertions.assertEquals("", transformer.transform(""));
    }
}
