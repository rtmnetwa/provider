package za.co.sporty.provider.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class BetaProvider {
    private String type;
    private String event_id;
}
