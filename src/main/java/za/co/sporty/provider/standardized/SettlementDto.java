package za.co.sporty.provider.standardized;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import za.co.sporty.provider.constants.EMessageType;

import java.util.Date;

@Getter
@Setter
@ToString
public class SettlementDto {

    private EMessageType messageType;
    private String eventId;
    private String result;
    private Date timestamp;

    public SettlementDto(String eventId, String result) {
        this.messageType = EMessageType.BET_SETTLEMENT;
        this.eventId = eventId;
        this.result = result;
        this.timestamp = new Date();
    }
}
