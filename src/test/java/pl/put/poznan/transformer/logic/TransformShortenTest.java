package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TransformShortenTest {

    private TextTransformerInterface transformer;

    @BeforeEach
    public void setUp() {
        transformer = new TransformShorten(new TextTransformer());
    }

    @Test
    @DisplayName("Pojedyńcze słowa")
    public void testWords() {
        Assertions.assertEquals("Dr", transformer.transform("Doktor"));
        Assertions.assertEquals("prof.", transformer.transform("profesor"));
        Assertions.assertEquals("mgr", transformer.transform("magister"));
    }

    @Test
    @DisplayName("Kilka słów")
    public void testMultipleWords() {
        Assertions.assertEquals("tzw. Cdn.", transformer.transform("tak zwany Ciąg dalszy nastąpi"));
        Assertions.assertEquals("Ul. al. itd.", transformer.transform("Ulica aleja i tak dalej"));
    }
}