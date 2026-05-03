package za.co.sporty.provider.standardized;

import lombok.*;
import za.co.sporty.provider.constants.EProviderMarket;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
public class OddsEntry {
    private EProviderMarket eProviderMarket;
    private double value;
}
