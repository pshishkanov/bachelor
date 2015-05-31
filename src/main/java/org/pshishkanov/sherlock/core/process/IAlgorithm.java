package org.pshishkanov.sherlock.core.process;

import java.util.List;

/**
 * Created by pshishkanov on 14/05/15.
 */
public interface IAlgorithm {

    Float process(List<String> p, List<String> t);

    String getName();

}
