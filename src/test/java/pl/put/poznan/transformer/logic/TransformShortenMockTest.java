package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TransformShortenMockTest {

    private TextTransformerInterface mockTextTransformer;
    private TransformShorten transformer;

    @BeforeEach
    public void setUp() {
        mockTextTransformer = Mockito.mock(TextTransformerInterface.class);
        transformer = new TransformShorten(mockTextTransformer);
    }

    @Test
    @DisplayName("Test czy odwołuje do TextTransformer")
    public void testToTextTransformer() {
        when(mockTextTransformer.transform("Licencjat to jest Numer")).thenReturn("Licencjat to jest Numer");
        String result = transformer.transform("Licencjat to jest Numer");
        assertEquals("Lic. tj. Nr", result);
        verify(mockTextTransformer, times(1)).transform("Licencjat to jest Numer");
    }

    @Test
    @DisplayName("Test gdy wejście przyjmuje null")
    public void testNull() {
        when(mockTextTransformer.transform(null)).thenThrow(new NullPointerException());
        assertThrows(NullPointerException.class, () -> transformer.transform(null));
        verify(mockTextTransformer, times(1)).transform(null);
    }

    @Test
    @DisplayName("Test pustych danych wejściowych")
    public void testEmpty() {
        when(mockTextTransformer.transform("")).thenReturn("");
        String result = transformer.transform("");
        assertEquals("", result);
        verify(mockTextTransformer, times(1)).transform("");
    }
}
