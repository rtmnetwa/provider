package za.co.sporty.provider.domain;

import lombok.Data;

import java.util.Map;

@Data
public class AlphaOddsChange extends AlphaProvider {
    private Map<String, Double> values;
}
