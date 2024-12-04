package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that decorates text by transforming numerical values to their verbal representations in Polish.
 * It extends the {@link TextTransformerDecorator} class and overrides the transform and decorate methods.
 * 
 * <p>This class uses regular expressions to identify numerical values in the input text and converts them to their corresponding verbal forms.
 * It supports integer values from 0 to 1000 and decimal values with up to two decimal places.</p>
 * 
 * <p>Example transformations:</p>
 * <ul>
 *   <li>"123" -> "sto dwadzieścia trzy"</li>
 *   <li>"45.67" -> "czterdzieści pięć i sześćdziesiąt siedem setnych"</li>
 *   <li>"0.1" -> "dziesięć setnych"</li>
 *   <li>"0.06" -> "sześć setnych"</li>
 * </ul>
 * 
 * <p>Logging is used to debug the transformation process, showing the original and transformed strings.</p>
 * 
 * <p>Note: This class assumes that numerical values in input text are formatted accordingly.</p>
 * 
 * @author  Krzysztof Smal
 * @since   1.1
 */
public class TransformVerbalise extends TextTransformerDecorator{

    /**
     * Logger instance for logging events and messages within the TransformVerbalise class.
     */
    private static final Logger logger = LoggerFactory.getLogger(TransformVerbalise.class);

    /**
     * Constructs a TransformVerbalise object with the specified TextTransformerInterface.
     *
     * @param transformer the TextTransformerInterface to be used for text transformation
     */
    public TransformVerbalise(TextTransformerInterface transformer) {
        super(transformer);
    }

    /**
     * Transforms the given text by applying a series of transformations and decorations.
     *
     * @param text the input text to be transformed
     * @return the transformed and decorated text
     */
    public String transform(String text){
        String transformed = super.transform(text);
        String decorated = decorate(transformed);
        logger.debug(String.format("Transformed string %s to %s", text, decorated));
        return decorated;
    }
    
    
    /**
     * Transforms numerical values in the input text to their verbal representation in Polish.
     *
     * @param text the input text containing numerical values to be transformed
     * @return the transformed text with numerical values verbalized in Polish
     */
    @Override
    public String decorate(String text) {
        String number_regex = "\\b(?:0(?:\\.\\d+)?|[1-9]\\d*(?:\\.\\d+)?)\\b"; // "\\b\\d+(?:\\.\\d+)?\\b"
        String dot_regex = "\\.";
        String[] units = new String[] {"", "jeden", "dwa", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć"};
        String[] teens = new String[] {"dziesięć", "jedenaście", "dwanaście", "trzynaście", "czternaście", "piętnaście", "szesnaście", "siedemnaście", "osiemnaście", "dziewiętnaście"};
        String[] tens = new String[] {"", "", "dwadzieścia", "trzydzieści", "czterdzieści", "pięćdziesiąt", "sześćdziesiąt", "siedemdziesiąt", "osiemdziesiąt", "dziewięćdziesiąt"};
        String[] hundreds = new String[] {"sto", "dwieście", "trzysta", "czterysta", "pięćset", "sześćset", "siedemset", "osiemset", "dziewięćset"};

        Pattern pattern = Pattern.compile(number_regex);
        Matcher matcher = pattern.matcher(text);
        StringBuilder sb = new StringBuilder();
        int lastMatchEnd = 0;

        while (matcher.find()) {
            sb.append(text, lastMatchEnd, matcher.start());
            lastMatchEnd = matcher.end();
            String match = matcher.group();

            int integerPart = match.contains(".") ? Integer.parseInt(match.split(dot_regex)[0]) : Integer.parseInt(match);
            String floatPartString = match.contains(".") ? match.split(dot_regex)[1] : "";

            int floatPart;
            if (!floatPartString.isEmpty())
                floatPart = (floatPartString.length() == 2) ? Integer.parseInt(floatPartString) : (Integer.parseInt(floatPartString) * 10);
            else floatPart = 0;

            if (integerPart < 0 || integerPart > 1000 || floatPartString.length() > 2) {
                sb.append(matcher.group());
            } else if (integerPart == 0) {
                if (floatPart == 0) {
                    sb.append("zero");
                }
            } else if (integerPart == 1000){
                sb.append("tysiąc");
            } else if (integerPart < 10){
                sb.append(units[integerPart]);
            } else {
                int hundredsDigit = integerPart / 100;
                int tensDigit = (integerPart % 100) / 10;
                int unitsDigit = integerPart % 10;
                if (hundredsDigit > 0) {
                    sb.append(hundreds[hundredsDigit - 1]);
                    if (tensDigit > 0 || unitsDigit > 0) {
                        sb.append(" ");
                    }
                }
                if (tensDigit > 1) {
                    sb.append(tens[tensDigit]);
                    if (unitsDigit > 0) {
                        sb.append(" ");
                    }
                } else if (tensDigit == 1) {
                    sb.append(teens[unitsDigit]);
                }
                if (unitsDigit > 0 && tensDigit != 1) {
                    sb.append(units[unitsDigit]);
                }
            }

            if (floatPart > 0 && floatPartString.length() <= 2 && integerPart <= 1000) {
                if (integerPart > 0) {
                    sb.append(" i ");
                }
                if (floatPart == 1) {
                    sb.append("jedna");
                } else if (floatPart == 2) {
                    sb.append("dwie");
                } else if (floatPart < 10) {
                    sb.append(units[floatPart]);
                } else {
                    int tensDigit = floatPart / 10;
                    int unitsDigit = floatPart % 10;
                    if (tensDigit > 1) {
                        sb.append(tens[tensDigit]);
                        if (unitsDigit > 0) {
                            sb.append(" ");
                            if (unitsDigit == 2){
                                sb.append("dwie");
                            } else {
                                sb.append(units[unitsDigit]);
                            }
                        }
                    } else if (tensDigit == 1) {
                        sb.append(teens[unitsDigit]);
                    }
                }

                if (floatPart == 1){
                    sb.append(" setna");
                } else if (floatPart < 5){
                    sb.append(" setne");
                } else if (floatPart < 20){
                    sb.append(" setnych");
                } else if (floatPart % 10 == 2 || floatPart % 10 == 3 || floatPart % 10 == 4){
                    sb.append(" setne");
                } else {
                    sb.append(" setnych");
                }
            }

        }
        sb.append(text.substring(lastMatchEnd));
        return String.valueOf(sb);
    }
}