package de.frontsy.picciotto.converters.poi.cell.style;

import de.frontsy.picciotto.parse.css.Rule;

import java.util.Optional;

public abstract class AbstractStyleFactory {
    public static Optional<AbstractStyleFactory> getStyleFactory(String selector) {
        // TODO: find a place for these strings..
        switch (selector) {
            case "background":
                return Optional.of(new BackgroundStyleFactory());
            case "border-top":
            case "border-right":
            case "border-bottom":
            case "border-left":
            case "border":
                return Optional.of(new BorderStyleFactory());
            case "color":
                return Optional.of(new ColorStyleFactory());
            case "font-style":
            case "font-variant":
            case "font-weight":
            case "font-stretch":
            case "font-size":
            case "line-height":
            case "font-family":
                return Optional.of(new FontStyleFactory());
        }
        return Optional.empty();
    }
    public abstract PoiStyle getStyle(Rule rule);
}
