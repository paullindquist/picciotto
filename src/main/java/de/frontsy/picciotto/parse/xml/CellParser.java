package de.frontsy.picciotto.parse.xml;

import de.frontsy.picciotto.convert.poi.cell.style.AbstractStyleFactory;
import de.frontsy.picciotto.convert.poi.cell.style.PoiStyle;
import de.frontsy.picciotto.parse.css.CSSParser;
import de.frontsy.picciotto.parse.css.Rule;
import de.frontsy.picciotto.structure.Cell;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;

import javax.xml.namespace.QName;
import java.util.*;

@Slf4j
@Builder
public class CellParser {
    public static final Cell ERROR_CELL = Cell.builder().value("ERROR").build();
    private CSSParser cssParser;

    private Optional<Integer> determineSpan(XmlCursor cursor, QName spanName) {
        Integer span = null;
        String spanAttribute = cursor.getAttributeText(spanName);
        if (spanAttribute != null && !spanAttribute.isBlank()) {
            try {
                span = Integer.parseInt(spanAttribute);
            } catch (NumberFormatException nfe) {
                log.warn(spanName + " attribute value: " + spanAttribute + " was not a number");
            }
        }
        if (span != null) {
            return Optional.of(span);
        } else {
            return Optional.empty();
        }
    }

    private Map<String, PoiStyle> findStyles(String styleAttribute) {
        Map<String, PoiStyle> styles = new HashMap<>();
        Set<Rule> rules = new HashSet<>();
        try {
            rules.addAll(cssParser.parseInline(styleAttribute));
        } catch (IllegalStateException illegalStateException) {
            log.error("Found invalid CSS: " + styleAttribute);
        }
        for (Rule rule : rules) {
            Optional<AbstractStyleFactory> styleFactory = AbstractStyleFactory.getStyleFactory(rule.getProperty());
            if (styleFactory.isPresent()) {
                AbstractStyleFactory factory = styleFactory.get();
                PoiStyle style = factory.getStyle(rule);
                styles.put(rule.getProperty(), style);
            }
        }
        return styles;
    }

    public Optional<Cell> parse(String xml) {
        XmlCursor cursor = null;
        try {
            cursor = XmlObject.Factory.parse(xml).newCursor();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (cursor != null) {
            cursor.toFirstChild();
            String styleAttribute = cursor.getAttributeText(QNames.STYLE);
            Map<String, PoiStyle> styles;
            if (styleAttribute != null) {
                styles = findStyles(styleAttribute);
            } else {
                styles = new HashMap<>();
            }
            return Optional.of(
                Cell.builder()
                    .colspan(determineSpan(cursor, QNames.COLSPAN).orElse(0))
                    .rowspan(determineSpan(cursor, QNames.ROWSPAN).orElse(0))
                    .styles(styles)
                    .value(cursor.getTextValue())
                    .build());
        } else {
            return Optional.empty();
        }
    }
}
