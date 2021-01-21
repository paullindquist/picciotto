package de.frontsy.picciotto.parse.css;

import com.steadystate.css.parser.CSSOMParser;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSStyleDeclaration;

import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@Slf4j
public class SteadyStateCSSParser implements CSSParser {
    private final CSSOMParser cssomParser = new CSSOMParser();

    @Override
    public Set<Rule> parseSheet(String css) {
        return null;
    }

    @Override
    public Set<Rule> parseInline(String css) {
        Set<Rule> rules = new HashSet<>();
        CSSStyleDeclaration styleDeclaration = null;
        try {
            styleDeclaration = cssomParser.parseStyleDeclaration(new InputSource(new StringReader(css)));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (styleDeclaration != null) {
            int styleDeclarationLength = styleDeclaration.getLength();
            Map<String, String> properties = new HashMap<>();
            for (int i = 0; i < styleDeclarationLength; i++) {
                final String propertyName = styleDeclaration.item(i);
                final String propertyValue = styleDeclaration.getPropertyValue(propertyName);
                properties.put(propertyName, propertyValue);
            }
            Rule rule = Rule.builder()
                .values(properties)
                .build();
            rules.add(rule);
        }
        return rules;
    }
}
