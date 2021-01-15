package dev.paullindquist.picciotto.structure;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Color {
    String hex;
    String name;
}
