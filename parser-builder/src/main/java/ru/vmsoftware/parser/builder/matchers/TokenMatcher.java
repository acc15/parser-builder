package ru.vmsoftware.parser.builder.matchers;

import ru.vmsoftware.parser.builder.ParserConfig;
import ru.vmsoftware.parser.builder.iterators.CharIterator;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-17-01
 */
public interface TokenMatcher {

    boolean match(CharIterator iter, ParserConfig config);

}
