package org.pshishkanov.sherlock.core.process;

import java.util.List;
import java.util.Optional;

/**
 * Created by pshishkanov on 14/05/15.
 */
public interface ITokenization {

    Optional<List<String>> process(String sourceText);
}
