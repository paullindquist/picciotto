package dev.paullindquist.picciotto.parse.css;

import java.util.Set;

public interface CSSParser {
    public Set<Rule> parseSheet(String css);
    public Set<Rule> parseInline(String css);
}
