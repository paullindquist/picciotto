package de.frontsy.picciotto.convert.poi.cell;

import de.frontsy.picciotto.parse.css.Rule;
import org.apache.poi.ss.usermodel.BorderStyle;

import java.util.Map;
import java.util.Optional;

public class PoiBorderStyleFactory {
    public static Optional<BorderStyle> getBorder(Rule rule) {
        Map<String, String> values = rule.getValues();
        if (values.containsKey("border-width")) {
            String width = values.get("border-width");
            String style = values.get("border-style");
            if (style.equals("solid")) {
                if (width.indexOf("px") > 0) {
                    String pixelString = width.substring(0, width.indexOf("px"));
                    try {
                        Integer pixels = Integer.valueOf(pixelString);
                        if (pixels <= 2) {
                            return Optional.of(BorderStyle.THIN);
                        } else if (pixels <= 5) {
                            return Optional.of(BorderStyle.MEDIUM);
                        } else {
                            return Optional.of(BorderStyle.THICK);
                        }
                    } catch (Exception e) {
                        return Optional.empty();
                    }
                } else {
                    if (width.equals("thin")) {
                        return Optional.of(BorderStyle.THIN);
                    } else if (width.equals("medium")) {
                        return Optional.of(BorderStyle.MEDIUM);
                    } else if (width.equals("thick")) {
                        return Optional.of(BorderStyle.THICK);
                    }
                }
            } else if (style.equals("dotted")) {
                if (width.indexOf("px") > 0) {
                    String pixelString = width.substring(0, width.indexOf("px"));
                    try {
                        Integer pixels = Integer.valueOf(pixelString);
                        if (pixels <= 2) {
                            return Optional.of(BorderStyle.DOTTED);
                        } else {
                            return Optional.of(BorderStyle.MEDIUM_DASH_DOT); // is this right?
                        }
                    } catch (Exception e) {
                        return Optional.empty();
                    }
                } else {
                    if (width.equals("thin")) {
                        return Optional.of(BorderStyle.DOTTED);
                    } else {
                        return Optional.of(BorderStyle.MEDIUM_DASH_DOT);
                    }
                }
            } else if (style.equals("dashed")) {
                if (width.indexOf("px") > 0) {
                    String pixelString = width.substring(0, width.indexOf("px"));
                    try {
                        Integer pixels = Integer.valueOf(pixelString);
                        if (pixels <= 2) {
                            return Optional.of(BorderStyle.DASHED);
                        } else {
                            return Optional.of(BorderStyle.MEDIUM_DASHED);
                        }
                    } catch (Exception e) {
                        return Optional.empty();
                    }
                } else {
                    if (width.equals("thin")) {
                        return Optional.of(BorderStyle.DASHED);
                    } else {
                        return Optional.of(BorderStyle.MEDIUM_DASHED);
                    }
                }
            }
        }
        return null;
    }
}
