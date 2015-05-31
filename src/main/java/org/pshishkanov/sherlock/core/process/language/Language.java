package org.pshishkanov.sherlock.core.process.language;

/**
 * Created by pshishkanov on 14/05/15.
 */
public enum Language {

    JAVA("Java"), PYTHON("Python");

    private String name;

    Language(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
