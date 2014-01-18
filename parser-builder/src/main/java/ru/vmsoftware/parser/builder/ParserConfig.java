package ru.vmsoftware.parser.builder;

import ru.vmsoftware.parser.builder.matchers.MatcherFactory;
import ru.vmsoftware.parser.builder.matchers.TokenMatcher;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-18-01
 */
public class ParserConfig {

    private TokenMatcher skip = MatcherFactory.nothing();
    private CaptureData capture = new CaptureData();

    public CaptureData getCapture() {
        return capture;
    }

    public void setCapture(CaptureData capture) {
        this.capture = capture;
    }

    public TokenMatcher getSkip() {
        return skip;
    }

    public void setSkip(TokenMatcher skip) {
        this.skip = skip;
    }

}
