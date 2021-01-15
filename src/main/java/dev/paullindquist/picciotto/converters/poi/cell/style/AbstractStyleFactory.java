package dev.paullindquist.picciotto.converters.poi.cell.style;

import dev.paullindquist.picciotto.parse.css.Rule;

import java.util.Optional;

public abstract class AbstractStyleFactory {
    public static Optional<AbstractStyleFactory> getStyleFactory(String selector) {
        // TODO: find a place for these strings..
        switch (selector) {
            case "background":
                return Optional.of(new BackgroundStyleFactory());
            case "border":
                return Optional.of(new BorderStyleFactory());
        }
        return Optional.empty();
    }

    public abstract PoiStyle getStyle(Rule rule);
}
