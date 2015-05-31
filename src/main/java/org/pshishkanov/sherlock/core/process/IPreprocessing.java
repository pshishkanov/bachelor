package org.pshishkanov.sherlock.core.process;

import java.util.Optional;

/**
 * Created by pshishkanov on 14/05/15.
 */
public interface IPreprocessing {

    Optional<String> process(String sourceText);
}
