package org.pshishkanov.sherlock.core.model;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class SourceCode {

    @Id
    private String id;

    /* Исходный тест программы */
    @NotNull
    private String sourceText;

    @NotNull
    private String language;

    /* Исходный текст программы после разбора ANTLR */
    private List<String> tokens;

    /* Имя пользователя, приславшего запрос */
    private String username;

    private String plagiarismProbability;

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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public String getPlagiarismProbability() {
        return plagiarismProbability;
    }

    public void setPlagiarismProbability(String plagiarismProbability) {
        this.plagiarismProbability = plagiarismProbability;
    }

    @Override
    public String toString() {
        return "SourceCode{" +
                "sourceText='" + sourceText + '\'' +
                ", id='" + id + '\'' +
                ", language='" + language + '\'' +
                ", tokens=" + tokens +
                ", username='" + username + '\'' +
                ", plagiarismProbability=" + plagiarismProbability +
                '}';
    }
}
