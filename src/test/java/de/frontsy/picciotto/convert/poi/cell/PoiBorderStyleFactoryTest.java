package de.frontsy.picciotto.convert.poi.cell;

import de.frontsy.picciotto.parse.css.Rule;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PoiBorderStyleFactoryTest {

    @Test
    void onePixelShouldReturnThin() {
        Map<String, String> values = Map.of("border-width", "1px", "border-style", "solid");
        Rule rule = Rule.builder().values(values).build();
        Optional<BorderStyle> style = PoiBorderStyleFactory.getBorder(rule);
        assertEquals(BorderStyle.THIN, style.orElseThrow());
    }
    @Test
    void threePixelsShouldReturnMedium() {
        Map<String, String> values = Map.of("border-width", "5px", "border-style", "solid");
        Rule rule = Rule.builder().values(values).build();
        Optional<BorderStyle> style = PoiBorderStyleFactory.getBorder(rule);
        assertEquals(BorderStyle.MEDIUM, style.orElseThrow());
    }
    @Test
    void sixPixelsShouldReturnThick() {
        Map<String, String> values = Map.of("border-width", "6px", "border-style", "solid");
        Rule rule = Rule.builder().values(values).build();
        Optional<BorderStyle> style = PoiBorderStyleFactory.getBorder(rule);
        assertEquals(BorderStyle.THICK, style.orElseThrow());
    }
    @Test
    void onePixelDashedShouldReturnJustDashed() {
        Map<String, String> values = Map.of("border-width", "1px", "border-style", "dashed");
        Rule rule = Rule.builder().values(values).build();
        Optional<BorderStyle> style = PoiBorderStyleFactory.getBorder(rule);
        assertEquals(BorderStyle.DASHED, style.orElseThrow());
    }
    @Test
    void threePixelsDashedShouldReturnMediumDashed() {
        Map<String, String> values = Map.of("border-width", "3px", "border-style", "dashed");
        Rule rule = Rule.builder().values(values).build();
        Optional<BorderStyle> style = PoiBorderStyleFactory.getBorder(rule);
        assertEquals(BorderStyle.MEDIUM_DASHED, style.orElseThrow());
    }
    @Test
    void fivePixelsShouldReturnThickDashed() {
        Map<String, String> values = Map.of("border-width", "5px", "border-style", "dashed");
        Rule rule = Rule.builder().values(values).build();
        Optional<BorderStyle> style = PoiBorderStyleFactory.getBorder(rule);
        assertEquals(BorderStyle.MEDIUM_DASHED, style.orElseThrow());
    }
    @Test
    void onePixelDottedShouldReturnJustDotted() {
        Map<String, String> values = Map.of("border-width", "1px", "border-style", "dotted");
        Rule rule = Rule.builder().values(values).build();
        Optional<BorderStyle> style = PoiBorderStyleFactory.getBorder(rule);
        assertEquals(BorderStyle.DOTTED, style.orElseThrow());
    }
    @Test
    void threePixelsDottedShouldReturnMediumDotted() {
        Map<String, String> values = Map.of("border-width", "3px", "border-style", "dotted");
        Rule rule = Rule.builder().values(values).build();
        Optional<BorderStyle> style = PoiBorderStyleFactory.getBorder(rule);
        assertEquals(BorderStyle.MEDIUM_DASH_DOT, style.orElseThrow());
    }
    @Test
    void fivePixelsShouldReturnThickDotted() {
        Map<String, String> values = Map.of("border-width", "5px", "border-style", "dotted");
        Rule rule = Rule.builder().values(values).build();
        Optional<BorderStyle> style = PoiBorderStyleFactory.getBorder(rule);
        assertEquals(BorderStyle.MEDIUM_DASH_DOT, style.orElseThrow());
    }
}