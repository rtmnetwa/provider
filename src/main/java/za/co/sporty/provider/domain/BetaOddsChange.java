package za.co.sporty.provider.domain;

import lombok.Data;

import java.util.Map;

@Data
public class BetaOddsChange extends BetaProvider {
    private Map<String, Double> odds;
}
