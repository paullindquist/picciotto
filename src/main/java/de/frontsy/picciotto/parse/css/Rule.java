package de.frontsy.picciotto.parse.css;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Rule {
    private final String property;
    private final Map<String, String> values;
}
