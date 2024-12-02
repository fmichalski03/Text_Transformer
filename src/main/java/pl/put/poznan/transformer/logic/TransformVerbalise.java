package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransformVerbalise extends TextTransformerDecorator{
    private static final Logger logger = LoggerFactory.getLogger(TransformVerbalise.class);
    public TransformVerbalise(TextTransformerInterface transformer) {
        super(transformer);
    }

    public String transform(String text){
        String transformed = super.transform(text);
        String decorated = decorate(transformed);
        logger.debug(String.format("Transformed string %s to %s", text, decorated));
        return decorated;
    }
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