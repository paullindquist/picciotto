package de.frontsy.picciotto.convert.poi.cell.style;

import de.frontsy.picciotto.parse.css.Rule;

import java.util.Optional;

public abstract class AbstractStyleFactory {
    public static Optional<AbstractStyleFactory> getStyleFactory(String selector) {
        // TODO: find a place for these strings..
        switch (selector) {
            case "background":
                return Optional.of(new BackgroundStyleFactory());
            case "border-top":
                return Optional.of(new BorderTopStyleFactory());
            case "border-right":
                return Optional.of(new BorderRightStyleFactory());
            case "border-bottom":
                return Optional.of(new BorderBottomStyleFactory());
            case "border-left":
                return Optional.of(new BorderLeftStyleFactory());
            case "border":
                return Optional.of(new BorderStyleFactory());
            case "color":
                return Optional.of(new ColorStyleFactory());
            case "font-style":
                return Optional.of(new FontStyleFactory());
            case "font-variant":
                return Optional.of(new FontVariantFactory());
            case "font-weight":
                return Optional.of(new FontWeightStyleFactory());
            case "text-align":
                return Optional.of(new TextAlignStyleFactory());
            case "font-stretch":
            case "font-size":
            case "line-height":
            case "font-family":
                return Optional.of(new FontFamilyStyleFactory());
        }
        return Optional.empty();
    }
    public abstract PoiStyle getStyle(Rule rule);
}
