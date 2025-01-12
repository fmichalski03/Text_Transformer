package pl.put.poznan.transformer.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

public class TransformToLatexMockTest {

    private TextTransformerInterface mockTextTransformer;
    private TransformToLatex transformer;

    @BeforeEach
    public void setUp() {
        mockTextTransformer = Mockito.mock(TextTransformerInterface.class);
        transformer = new TransformToLatex(mockTextTransformer);
    }


    @Test
    @DisplayName("Test if decorate method is called with correct argument")
    public void testDecorateMethodCalled() {
        // Given
        String input = "~ $ &";
        when(mockTextTransformer.transform(input)).thenReturn(input);

        // When
        transformer.transform(input);

        // Then
        String result = transformer.transform(input);
        assertEquals("\\~{} \\$ \\&", result);
    }

    @Test
    @DisplayName("Test if transform method returns correct result")
    public void testTransformMethodReturnsCorrectResult() {
        // Given
        String input = "~ $ &";
        when(mockTextTransformer.transform(input)).thenReturn(input);

        // When
        String result = transformer.transform(input);

        // Then
        assertEquals("\\~{} \\$ \\&", result);
    }

    @Test
    @DisplayName("Test if transform method handles null input")
    public void testTransformMethodHandlesNullInput() {
        // Given
        String input = null;

        // When and Then
        assertThrows(NullPointerException.class, () -> transformer.transform(input));
    }


    @Test
    @DisplayName("Test if transform method handles special characters")
    public void testTransformMethodHandlesSpecialCharacters() {
        // Given
        String input = "# % _ { } ~ ^ ' \"";
        when(mockTextTransformer.transform(input)).thenReturn(input);

        // When
        String result = transformer.transform(input);

        // Then
        assertEquals("\\# \\% \\_ \\{ \\} \\~{} \\^{} \\' \\\"", result);
    }
}