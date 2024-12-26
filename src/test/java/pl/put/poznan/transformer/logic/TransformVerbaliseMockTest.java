package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TransformVerbaliseMockTest {

    private TextTransformerInterface mockTextTransformer;
    private TransformVerbalise transformer;

    @BeforeEach
    public void setUp() {
        mockTextTransformer = Mockito.mock(TextTransformerInterface.class);
        transformer = new TransformVerbalise(mockTextTransformer);
    }

    @Test
    @DisplayName("Test czy deleguje do TextTransformer")
    public void testDelegationToTextTransformer() {
        when(mockTextTransformer.transform("123")).thenReturn("sto dwadzieścia trzy");
        String result = transformer.transform("123");
        assertEquals("sto dwadzieścia trzy", result);
        verify(mockTextTransformer, times(1)).transform("123");
    }

    @Test
    @DisplayName("Test obsługi pustego wejścia")
    public void testEmptyInput() {
        when(mockTextTransformer.transform("")).thenReturn("");
        String result = transformer.transform("");
        assertEquals("", result);
        verify(mockTextTransformer, times(1)).transform("");
    }

    @Test
    @DisplayName("Test dla wartości null")
    public void testNullInput() {
        when(mockTextTransformer.transform(null)).thenThrow(new NullPointerException());
        assertThrows(NullPointerException.class, () -> transformer.transform(null));
        verify(mockTextTransformer, times(1)).transform(null);
    }
}
