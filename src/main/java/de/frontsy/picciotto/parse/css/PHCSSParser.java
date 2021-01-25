package de.frontsy.picciotto.parse.css;

import com.helger.css.ECSSVersion;
import com.helger.css.decl.CSSDeclaration;
import com.helger.css.decl.CSSDeclarationList;
import com.helger.css.decl.shorthand.CSSShortHandDescriptor;
import com.helger.css.decl.shorthand.CSSShortHandRegistry;
import com.helger.css.decl.visit.CSSVisitor;
import com.helger.css.decl.visit.DefaultCSSVisitor;
import com.helger.css.decl.visit.ICSSVisitor;
import com.helger.css.property.ECSSProperty;
import com.helger.css.reader.CSSReaderDeclarationList;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@Slf4j
public class PHCSSParser implements CSSParser {

    @Override
    public Set<Rule> parseSheet(String css) {
        return null;
    }

    @Override
    public Set<Rule> parseInline(String css) {
        Set<Rule> rules = new HashSet<>();
        final CSSDeclarationList aDeclList = CSSReaderDeclarationList.readFromString(css, ECSSVersion.CSS30);
        if (aDeclList == null)
            throw new IllegalStateException("Failed to parse CSS: " + css);
        // Create a custom visitor
        final ICSSVisitor aVisitor = new DefaultCSSVisitor() {
            @Override
            public void onDeclaration(@Nonnull final CSSDeclaration declaration) {
                Map<String, String> properties = new HashMap<>();
                if (declaration.getProperty().equals(ECSSProperty.BORDER)) {
                    final CSSShortHandDescriptor aSHD = CSSShortHandRegistry.getShortHandDescriptor(ECSSProperty.BORDER);
                    for (CSSDeclaration splitDeclaration : aSHD.getSplitIntoPieces(declaration)) {
                        properties.put(splitDeclaration.getProperty(), splitDeclaration.getExpressionAsCSSString());
                    }
                } else if (declaration.getProperty().equals(ECSSProperty.BORDER_TOP.getName())) {
                    final CSSShortHandDescriptor aSHD = CSSShortHandRegistry.getShortHandDescriptor(ECSSProperty.BORDER_TOP);
                    for (CSSDeclaration splitDeclaration : aSHD.getSplitIntoPieces(declaration)) {
                        properties.put(splitDeclaration.getProperty(), splitDeclaration.getExpressionAsCSSString());
                    }
                } else if (declaration.getProperty().equals(ECSSProperty.BORDER_RIGHT.getName())) {
                    final CSSShortHandDescriptor aSHD = CSSShortHandRegistry.getShortHandDescriptor(ECSSProperty.BORDER_RIGHT);
                    for (CSSDeclaration splitDeclaration : aSHD.getSplitIntoPieces(declaration)) {
                        properties.put(splitDeclaration.getProperty(), splitDeclaration.getExpressionAsCSSString());
                    }
                } else if (declaration.getProperty().equals(ECSSProperty.BORDER_BOTTOM.getName())) {
                    final CSSShortHandDescriptor aSHD = CSSShortHandRegistry.getShortHandDescriptor(ECSSProperty.BORDER_BOTTOM);
                    for (CSSDeclaration splitDeclaration : aSHD.getSplitIntoPieces(declaration)) {
                        properties.put(splitDeclaration.getProperty(), splitDeclaration.getExpressionAsCSSString());
                    }
                } else if (declaration.getProperty().equals(ECSSProperty.BORDER_LEFT.getName())) {
                    final CSSShortHandDescriptor aSHD = CSSShortHandRegistry.getShortHandDescriptor(ECSSProperty.BORDER_LEFT);
                    for (CSSDeclaration splitDeclaration : aSHD.getSplitIntoPieces(declaration)) {
                        properties.put(splitDeclaration.getProperty(), splitDeclaration.getExpressionAsCSSString());
                    }
                } else if (declaration.getProperty().equals(ECSSProperty.BACKGROUND.getName())) {
                    final CSSShortHandDescriptor aSHD = CSSShortHandRegistry.getShortHandDescriptor(ECSSProperty.BACKGROUND);
                    for (CSSDeclaration splitDeclaration : aSHD.getSplitIntoPieces(declaration)) {
                        properties.put(splitDeclaration.getProperty(), splitDeclaration.getExpressionAsCSSString());
                    }
                } else {
                    properties.put(declaration.getProperty(), declaration.getExpressionAsCSSString());
                }
                rules.add(Rule.builder().property(declaration.getProperty()).values(properties).build());
            }
        };
        CSSVisitor.visitAllDeclarations(aDeclList, aVisitor);
        return rules;
    }
}
