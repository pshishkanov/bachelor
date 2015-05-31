package org.pshishkanov.sherlock.web.api;

import org.pshishkanov.sherlock.core.model.SourceCode;
import org.pshishkanov.sherlock.core.process.SimilarityService;
import org.pshishkanov.sherlock.core.repository.SourceCodeRepository;
import org.pshishkanov.sherlock.web.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by pshishkanov on 14/05/15.
 */

@EnableAsync
@Configuration
@ComponentScan
@RestController
@RequestMapping(value = "/sherlock/api")
public class CheckController {

    private static Logger log = Logger.getLogger(CheckController.class.getName());

    @Autowired
    private SimilarityService similarityService;

    @Autowired
    private SourceCodeRepository sourceCodeRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/check/sync")
    public Float syncCheck(@RequestBody SourceCode sourceCode) {
        log.info("syncCheck start");
        Optional<Float> result = similarityService.syncProcess(sourceCode);
        log.info("syncCheck end");
        return result.get();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/check/async")
    public String asyncCheck(@RequestBody SourceCode sourceCode) {
        log.info("asyncCheck start");
        String result = similarityService.asyncProcess(sourceCode);
        log.info("asyncCheck end");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/check/result")
    public String asyncCheck(@RequestBody String id) {
        return sourceCodeRepository.findOne(id).getId();
    }
}
