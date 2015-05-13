package org.pshishkanov.sherlock.model;

import org.pshishkanov.sherlock.security.model.Account;

/**
 * Created by pshishkanov on 21/04/15.
 */

public class Record {

    /* Исходный тест программы */
    private String sourceText;

    /* Исходный текст программы после разбора ANTLR */
    private String tokens;

    /* Учётная запись пользователя, приславшего запись */
    private Account account;


    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
