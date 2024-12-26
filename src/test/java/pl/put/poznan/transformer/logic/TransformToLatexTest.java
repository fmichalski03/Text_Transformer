package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TransformToLatexTest {

    private TextTransformerInterface transformer;

    @Test
    @DisplayName("Testy dla specyficznych przypadków transformacji na LaTeX")
    public void consecutiveRepetitionTest() {
        transformer = new TransformToLatex(new TextTransformer());
        Assertions.assertEquals("\\~{} \\$ \\&", transformer.transform("~ $ &"));
    }
}
