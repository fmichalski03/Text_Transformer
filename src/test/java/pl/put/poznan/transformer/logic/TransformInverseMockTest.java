package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransformInverseMockTest {

    private TextTransformerInterface mockTextTransformer;
    private TransformInverse transformer;

    @BeforeEach
    public void setUp() {
        mockTextTransformer = Mockito.mock(TextTransformerInterface.class);
        transformer = new TransformInverse(mockTextTransformer);
    }

    @Test
    @DisplayName("Test czy odwołuje do TextTransformer")
    public void testToTextTransformer() {
        when(mockTextTransformer.transform("TeXTtransFORMer")).thenReturn("TeXTtransFORMer");
        String result = transformer.transform("TeXTtransFORMer");
        assertEquals("ReMRofsnaRTTXet", result);
        verify(mockTextTransformer, times(1)).transform("TeXTtransFORMer");
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
