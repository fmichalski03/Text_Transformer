package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TransformInverseTest {

    private TextTransformerInterface transformer;

    @BeforeEach
    public void setUp() {
        transformer = new TransformInverse(new TextTransformer());
    }

    @Test
    @DisplayName("Pojedyńcze słowa")
    public void testWords() {
        Assertions.assertEquals("oNameiS", transformer.transform("sIemanO"));
        Assertions.assertEquals("akInHcetIloP", transformer.transform("poLiTechNikA"));
        Assertions.assertEquals("ReTupmOk", transformer.transform("KoMputEr"));
    }

    @Test
    @DisplayName("Kilka słów")
    public void testMultipleWords() {
        Assertions.assertEquals("KaMilś limAk", transformer.transform("KaMil ślimAk"));
        Assertions.assertEquals("YNozDowz tsoM", transformer.transform("MOst zwoDzonY"));
    }
}