package org.pshishkanov.sherlock.core.model;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

public class SourceCode {

    @Id
    private String id;

    /* Исходный тест программы */
    private String sourceText;

    /* Исходный текст программы после разбора ANTLR */
    private List<String> tokens;

    /* Имя пользователя, приславшего запрос */
    private String username;

    /* Список полученных вероятностей плагиата для каждого из алгоритмов:
    *   key - название алгоритма,
    *   value - вероятность плагиата (от 0 до 1)
    */
    private Map<String, Float> plagiarismProbability;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, Float> getPlagiarismProbability() {
        return plagiarismProbability;
    }

    public void setPlagiarismProbability(Map<String, Float> plagiarismProbability) {
        this.plagiarismProbability = plagiarismProbability;
    }
}
