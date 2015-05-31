package org.pshishkanov.sherlock.core.process.language.java;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.pshishkanov.sherlock.core.process.ITokenization;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by pshishkanov on 14/05/15.
 */
public class JavaTokenization implements ITokenization {

    private static Logger log = Logger.getLogger(JavaTokenization.class.getName());

    @Override
    public Optional<List<String>> process(String sourceCode) {
        try {
            ANTLRInputStream sourceCodeInputStream = new ANTLRInputStream(stringToInputStream(sourceCode));
            Lexer lexer = new JavaLexer(sourceCodeInputStream);
            CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
            JavaParser parser = new JavaParser(commonTokenStream);
            IJavaListener listener = new JavaListener();
            parser.addParseListener(listener);
            parser.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                    throw e;
                }
            });
            parser.compilationUnit();
            return Optional.of(listener.getTokens());
        } catch (IOException ioe) {
            log.warning("Error create new ANTLRInputStream(...)");
            return Optional.empty();
        } catch (RecognitionException re) {
            log.warning("Recognition error (source code : " + sourceCode.replace("\n", " "));
            return Optional.empty();
        } catch (Exception e) {
            log.warning("Unknown error (source code : " + sourceCode.replace("\n", " "));
            return Optional.empty();
        }
    }

    private InputStream stringToInputStream(String string) {
        return new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
    }
}
