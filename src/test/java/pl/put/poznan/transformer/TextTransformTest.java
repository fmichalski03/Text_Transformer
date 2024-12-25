package pl.put.poznan.transformer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pl.put.poznan.transformer.logic.*;
public class TextTransformTest {
    
    private TextTransformerInterface transformer;

    @BeforeEach
    public void setUp(){
        transformer = new TextTransformer();
    }

    @Test
    @DisplayName("Pojedyncze powtorzenie slowa")
    public void singleRepetitionTest(){
        try{
            transformer = (TextTransformerDecorator) TransformRemove.class.getDeclaredConstructor(TextTransformerInterface.class)
            .newInstance(transformer);
        } catch (Exception e) {
                System.exit(-1);
            }
            Assertions.assertEquals("do na tu", transformer.transform("do do na na tu"));
    }

    @Test
    @DisplayName("Kilkukrotne powtorzenie slow")
    public void consecutiveRepetitionTest(){
        try{
            transformer = (TextTransformerDecorator) TransformRemove.class.getDeclaredConstructor(TextTransformerInterface.class)
            .newInstance(transformer);
        } catch (Exception e) {
                System.exit(-1);
            }
            Assertions.assertEquals("do na tu", transformer.transform("do do do do na na na tu"));
    }
    @Test
    @DisplayName("Kilkukritne powtorzenie slow")
    public void tolatexTest(){
        try{
            transformer = (TextTransformerDecorator) TransformToLatex.class.getDeclaredConstructor(TextTransformerInterface.class)
            .newInstance(transformer);
        } catch (Exception e) {
                System.exit(-1);
            }
            Assertions.assertEquals("\\~{} \\$ \\&", transformer.transform("~ $ &"));
    }
}