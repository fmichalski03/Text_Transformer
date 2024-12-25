// package pl.put.poznan.transformer;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import static org.mockito.Mockito.*;



// import pl.put.poznan.transformer.logic.*;

// public class TextTransformMockTest {

//     private TextTransformerInterface transformerMock;
//     private TextTransformerDecorator decorator;

//     @BeforeEach
//     public void setUp() {
//         transformerMock = Mockito.mock(TextTransformerInterface.class);
//         decorator = new TransformRemove(transformerMock);
//     }

//     @Test
//     @DisplayName("Wywołanie metody transform z odpowiednimi parametrami")
//     public void transformMethodCalledTest() {
//         // Given
//         String input = "do do na na tu";

//         // When
//         decorator.transform(input);

//         // Then
//         verify(transformerMock, times(1)).transform(input);
//     }
// }
