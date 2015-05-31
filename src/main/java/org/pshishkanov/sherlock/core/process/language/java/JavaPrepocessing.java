package org.pshishkanov.sherlock.core.process.language.java;

import org.pshishkanov.sherlock.core.process.IPreprocessing;

import java.util.Optional;

/**
 * Created by pshishkanov on 14/05/15.
 */
public class JavaPrepocessing implements IPreprocessing {
    @Override
    public Optional<String> process(String sourceText) {
        /* TODO Реализовать предварительную обработку исходных текстов */
        return Optional.of(sourceText);
    }
}
