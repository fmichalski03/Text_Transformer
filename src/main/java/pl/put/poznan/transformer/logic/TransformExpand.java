package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransformExpand extends TextTransformerDecorator{
    private static final Logger logger = LoggerFactory.getLogger(TransformExpand.class);
    public TransformExpand(TextTransformerInterface transformer) {
        super(transformer);
    }

    public String transform(String text){
        String transformed = super.transform(text);
        String decorated = decorate(transformed);
        logger.debug(String.format("Transformed string %s to %s", text, decorated));
        return decorated;
    }

    private String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    @Override
    public String decorate(String text) {
        String[][] abbreviations = {
                {"prof.", "profesor"},
                {"dr", "doktor"},
                {"np.", "na przykład"},
                {"itd.", "i tak dalej"},
                {"itp.", "i tym podobne"},
                {"cd.", "ciąg dalszy"},
                {"cdn.", "ciąg dalszy nastąpi"},
                {"lek.", "lekarz"},
                {"lic.", "licencjat"},
                {"mgr", "magister"},
                {"nr", "numer"},
                {"str.", "strona"},
                {"tj.", "to jest"},
                {"tzn.", "to znaczy"},
                {"tzw.", "tak zwany"},
                {"ul.", "ulica"},
                {"al.", "aleja"}
        };
        for(int i = 0; i < abbreviations.length; i++) {
            text = text.replace(abbreviations[i][0], abbreviations[i][1]);
            text = text.replace(capitalize(abbreviations[i][0]), capitalize(abbreviations[i][1]));
        }
        return text;
    }
}