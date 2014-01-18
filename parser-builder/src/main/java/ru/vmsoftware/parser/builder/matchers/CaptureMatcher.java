package ru.vmsoftware.parser.builder.matchers;

import ru.vmsoftware.parser.builder.CaptureContext;
import ru.vmsoftware.parser.builder.CaptureData;
import ru.vmsoftware.parser.builder.CaptureListener;
import ru.vmsoftware.parser.builder.ParserConfig;
import ru.vmsoftware.parser.builder.iterators.CharIterator;

import java.util.List;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-19-01
 */
class CaptureMatcher implements TokenMatcher {

    private CaptureListener listener;
    private TokenMatcher matcher;

    public CaptureMatcher(CaptureListener listener, TokenMatcher matcher) {
        this.listener = listener;
        this.matcher = matcher;
    }

    @Override
    public boolean match(final CharIterator iter, ParserConfig config) {
        final CaptureData prevCapture = config.getCapture();
        final CaptureData capture = new CaptureData();
        try {
            config.setCapture(capture);

            final int startPos = iter.position();
            if (matcher.match(iter, config)) {
                listener.onCapture(new CaptureContext() {
                    @Override
                    public CharSequence getCapture() {
                        return iter.subSequence(startPos);
                    }

                    @Override
                    public <T> T getValue() {
                        return capture.getValue();
                    }

                    @Override
                    public <T> List<T> getValues() {
                        return capture.getValues();
                    }

                    @Override
                    public <T> T getValue(String name) {
                        return capture.getValue(name);
                    }

                    @Override
                    public <T> List<T> getValues(String name) {
                        return capture.getValues(name);
                    }

                    @Override
                    public void addValue(String name, Object e) {
                        prevCapture.addValue(name, e);
                    }

                    @Override
                    public void addValue(Object e) {
                        prevCapture.addValue(e);
                    }
                });
                return true;
            }
            return false;

        } finally {
            config.setCapture(prevCapture);
        }
    }
}
