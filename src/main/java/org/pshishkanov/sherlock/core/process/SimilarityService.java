package org.pshishkanov.sherlock.core.process;

import org.pshishkanov.sherlock.core.model.SourceCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by pshishkanov on 14/05/15.
 */

@Service
public class SimilarityService {

    private static Logger log = Logger.getLogger(SimilarityService.class.getName());

    public Optional<Float> syncProcess(SourceCode sourceCode) {
        log.info("syncProcess start");
        String uuid = UUID.randomUUID().toString();
        sourceCode.setId(uuid);
        log.info("syncProcess end");
        return Optional.ofNullable(process(sourceCode).getPlagiarismProbability().get("TOTAL"));
    }

    public String asyncProcess(SourceCode sourceCode) {
        log.info("asyncProcess start");
        String uuid = UUID.randomUUID().toString();
        sourceCode.setId(uuid);
        async(sourceCode);
        log.info("asyncProcess end");
        return uuid;
    }

    @Async
    public void async(SourceCode sourceCode) {
        for (int i = 1; i <= 10; i++ ) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {

            }
            log.info("process do ...");

        }
        //process(sourceCode);
    }

    private SourceCode process(SourceCode sourceCode) {
        /* TODO Реализовать общую логику */
        for (int i = 1; i <= 10; i++ ) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {

            }
            log.info("process do ...");

        }
        return new SourceCode();
    }
}
