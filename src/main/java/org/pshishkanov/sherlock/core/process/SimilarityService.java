package org.pshishkanov.sherlock.core.process;

import org.pshishkanov.sherlock.core.model.AtomicFloat;
import org.pshishkanov.sherlock.core.model.SourceCode;
import org.pshishkanov.sherlock.core.process.algorithm.rkrgst.RKRGSTAlgorithm;
import org.pshishkanov.sherlock.core.process.algorithm.winnowing.WinnowingAlgorithm;
import org.pshishkanov.sherlock.core.process.language.java.JavaPrepocessing;
import org.pshishkanov.sherlock.core.process.language.java.JavaTokenization;
import org.pshishkanov.sherlock.core.repository.SourceCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by pshishkanov on 14/05/15.
 */

@Service
@EnableAsync
public class SimilarityService {

    private static Logger log = Logger.getLogger(SimilarityService.class.getName());

    @Autowired
    SourceCodeRepository sourceCodeRepository;

    private Map<String, IAlgorithm> algorithms;

    private Float total_weight = 0f;

    {
        IAlgorithm algorithm;
        algorithms = new HashMap<>();
        algorithm = new RKRGSTAlgorithm();
        total_weight += algorithm.getWeight();
        algorithms.put(algorithm.getName(), algorithm);
        algorithm = new WinnowingAlgorithm();
        total_weight += algorithm.getWeight();
        algorithms.put(algorithm.getName(), algorithm);
    }

    public Optional<Float> syncProcess(SourceCode sourceCode) {
        sourceCode.setId(UUID.randomUUID().toString());
        return Optional.ofNullable(Float.valueOf(process(sourceCode).get().getPlagiarismProbability()));
    }

    @Async
    public void asyncProcess(SourceCode sourceCode, String uuid) {
        sourceCode.setId(uuid);
        process(sourceCode);
    }

    private Optional<SourceCode> process(SourceCode incoming_source_code) {

        ITokenization tokenization;
        IPreprocessing preprocessing;

        switch (incoming_source_code.getLanguage()) {
            case "java" :
                tokenization = new JavaTokenization();
                preprocessing = new JavaPrepocessing();
                break;
//          case "python" :
//              ...
//              break;
            default:
                return Optional.empty();
        }

        incoming_source_code.setSourceText(preprocessing.process(incoming_source_code.getSourceText()));

        Optional<List<String>> tokens = tokenization.process(incoming_source_code.getSourceText());

        if (tokens.isPresent()) {
            incoming_source_code.setTokens(tokens.get());
        } else {
            return Optional.empty();
        }

        AtomicFloat max_plagiarism_probability = new AtomicFloat();

        List<SourceCode> all_source_code_by_language = sourceCodeRepository.findByLanguage(incoming_source_code.getLanguage());
        all_source_code_by_language.forEach(source_code_from_db -> {
            Float current_max_plagiarism_probability = max_plagiarism_probability.floatValue();
            Float current_plagiarism_probability = calculate(source_code_from_db.getTokens(), incoming_source_code.getTokens());
            max_plagiarism_probability.set(Float.max(current_max_plagiarism_probability, current_plagiarism_probability));
        });

        incoming_source_code.setPlagiarismProbability(Float.toString(max_plagiarism_probability.get()));

        sourceCodeRepository.save(incoming_source_code);

        return Optional.of(incoming_source_code);
    }

    private Float calculate(List<String> p, List<String> t) {
        Map<String, Float> plagiarism_probability = new HashMap<>();
        algorithms.forEach((algorithm_name, algorithm) -> plagiarism_probability.put(algorithm_name, algorithm.process(p, t)));

        AtomicFloat result_probability = new AtomicFloat();

        plagiarism_probability.forEach((algorithm_name, algorithm_probability) -> {
            result_probability.set(result_probability.floatValue() + (algorithms.get(algorithm_name).getWeight() * algorithm_probability) / total_weight);
        });
        return result_probability.floatValue();
    }
}
