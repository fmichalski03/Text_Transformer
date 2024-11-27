package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformRequest;
import pl.put.poznan.transformer.logic.TextTransformResponse;

import java.util.Arrays;

@RestController
@RequestMapping("/transform")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @PostMapping(consumes = "application/json", produces = "application/json")
    public TextTransformResponse transformText(@RequestBody TextTransformRequest request) {

        // Logujemy wejście
        logger.debug("Received text: {}", request.getText());
        logger.debug("Transforms: {}", Arrays.toString(request.getTransforms()));

        // Wykonujemy transformacje
        TextTransformer transformer = new TextTransformer(request.getTransforms());
        String result = transformer.transform(request.getText());

        // Zwracamy odpowiedź w formacie JSON
        return new TextTransformResponse(result);
    }
}

