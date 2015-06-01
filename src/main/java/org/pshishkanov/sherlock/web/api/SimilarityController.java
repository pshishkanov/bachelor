package org.pshishkanov.sherlock.web.api;

import org.pshishkanov.sherlock.core.model.SourceCode;
import org.pshishkanov.sherlock.web.validator.SourceCodeValidator;
import org.pshishkanov.sherlock.core.process.SimilarityService;
import org.pshishkanov.sherlock.core.repository.SourceCodeRepository;
import org.pshishkanov.sherlock.web.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by pshishkanov on 14/05/15.
 */

@Configuration
@ComponentScan
@RestController
@RequestMapping(value = "/sherlock/api")
public class SimilarityController {

    private static Logger log = Logger.getLogger(SimilarityController.class.getName());

    @Autowired
    private SimilarityService similarityService;

    @Autowired
    private SourceCodeRepository sourceCodeRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/similarity/sync")
    public ResponseEntity<ApiResponse> syncSimilarity(@Valid @RequestBody SourceCode sourceCode, Principal user) {

        log.info(String.join(" : ", "/sherlock/api/similarity/sync", user.getName(), sourceCode.toString()));

        sourceCode.setUsername(user.getName());

        Optional<Float> plagiarismProbability = similarityService.syncProcess(sourceCode);

        if (plagiarismProbability.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(plagiarismProbability.get().toString()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/similarity/async")
    public ResponseEntity<ApiResponse> asyncSimilarity(@Valid @RequestBody SourceCode sourceCode, Principal user) {

        log.info(String.join(" : ", "/sherlock/api/similarity/async", user.getName(), sourceCode.toString()));

        sourceCode.setUsername(user.getName());

        String uuid = UUID.randomUUID().toString();
        similarityService.asyncProcess(sourceCode, uuid);

        return new ResponseEntity<>(new ApiResponse(uuid), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/similarity/result/{uuid}")
    public ResponseEntity<ApiResponse> resultSimilarity(@PathVariable String uuid) {
        SourceCode search_source_code = sourceCodeRepository.findOne(uuid);
        if (search_source_code != null) {
            return new ResponseEntity<>(new ApiResponse(search_source_code.getPlagiarismProbability()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @InitBinder("sourceCode")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new SourceCodeValidator());
    }
}
