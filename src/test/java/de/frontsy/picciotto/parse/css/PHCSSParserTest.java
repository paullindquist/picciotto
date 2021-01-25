package de.frontsy.picciotto.parse.css;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PHCSSParserTest {

    @Test
    public void should() {
        String cell = "border:dashed red;border-bottom: green";
        PHCSSParser parser = new PHCSSParser();
        Set<Rule> parsed = parser.parseInline(cell);

        System.out.println(parsed);

    }

}