package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransformToLatex extends TextTransformerDecorator {
	private static final Logger logger = LoggerFactory.getLogger(TransformToLatex.class);
    public TransformToLatex(TextTransformerInterface transformer) {
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
        text = text.replace("&", "\\&");
        text = text.replace("$", "\\$");
        text = text.replace("%", "\\%");
        text = text.replace("#", "\\#");
        text = text.replace("_", "\\_");
        text = text.replace("{", "\\{");
        text = text.replace("}", "\\}");
        text = text.replace("~", "\\~{}");
        text = text.replace("^", "\\^{}");
        text = text.replace("'", "\\'");
        text = text.replace("\"", "\\\"");
        
        return text;
    }
}

