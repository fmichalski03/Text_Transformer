package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformRequest;
import pl.put.poznan.transformer.logic.TextTransformResponse;
import pl.put.poznan.transformer.logic.*;

import java.util.Arrays;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/{text}")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @PostMapping(consumes = "application/json", produces = "application/json")
    public TextTransformResponse transformText(@RequestBody TextTransformRequest request){

        // Logujemy wejście

        logger.debug("Received text: {}", request.getText());
        logger.debug("Transforms: {}", Arrays.toString(request.getTransforms()));

        // Wykonujemy transformacje
        TextTransformerInterface transformer = new TextTransformer();

        for (String transformation : request.getTransforms()) {
            String className = null;
            switch(transformation) {
                case "inverse":
                    className = "TransformInverse";
                    break;
                case "upper":
                    className = "TransformUpper";
                    break;
                case "reverse":
                    className = "TransformReverse";
                    break;
                case "lower":
                    className = "TransformLower";
                    break;
                case "capitalize":
                    className = "TransformCapitalize";
                    break;
                case "expand":
                    className = "TransformExpand";
                    break;
                case "verbalise":
                    className = "TransformVerbalise";
                    break;
            }

            if (className == null) {
                return new TextTransformResponse("Nie istnieje tranformacja " + transformation);
            }

            try {
                Class<?> decoratorClass = Class.forName("pl.put.poznan.transformer.logic." + className);
                transformer = (TextTransformerDecorator) decoratorClass.getDeclaredConstructor(TextTransformerInterface.class).newInstance(transformer);
            } catch (ClassNotFoundException e) {
                return new TextTransformResponse("Nie istnieje tranformacja " + transformation);
            } catch (Exception e) {
                return new TextTransformResponse("Transformacja nie powiodła się " + e);
            }
        }

        String result = transformer.transform(request.getText());

        return new TextTransformResponse(result);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<TextTransformResponse> get(@PathVariable String text, @RequestParam(value = "transforms", defaultValue = "upper,escape") String[] transforms) {

        // Logujemy wejście
        logger.debug("Received text: {}", text);
        logger.debug("Transforms: {}", Arrays.toString(transforms));

        // Wykonujemy transformacje
        TextTransformerInterface transformer = new TextTransformer();
        for (String transformation : transforms) {
            String className = "";
            switch(transformation) {
                case "inverse":
                    className = "TransformInverse";
                    break;
                case "upper":
                    className = "TransformUpper";
                    break;
                case "reverse":
                    className = "TransformReverse";
                    break;
                case "lower":
                    className = "TransformLower";
                    break;
                case "capitalize":
                    className = "TransformCapitalize";
                    break;
                case "expand":
                    className = "TransformExpand";
                    break;
                case "verbalise":
                    className = "TransformVerbalise";
                    break;
            }

            try {
                Class<?> decoratorClass = Class.forName("pl.put.poznan.transformer.logic." + className);
                transformer = (TextTransformerDecorator) decoratorClass.getDeclaredConstructor(TextTransformerInterface.class)
                        .newInstance(transformer);
            } catch (ClassNotFoundException e) {
                TextTransformResponse res = new TextTransformResponse("Nie istnieje tranformacja " + transformation);
                return ResponseEntity.badRequest().body(res);

            } catch (Exception e) {
                TextTransformResponse res = new TextTransformResponse("Transformacja nie powiodła się " + e);
                return ResponseEntity.status(500).body(res);
            }

        }

        TextTransformResponse res = new TextTransformResponse(transformer.transform(text));
        return ResponseEntity.ok(res);
    }
}


