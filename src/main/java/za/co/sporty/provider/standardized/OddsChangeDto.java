package za.co.sporty.provider.standardized;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import za.co.sporty.provider.constants.EMessageType;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class OddsChangeDto {
    private EMessageType messageType;
    private String eventId;
    private List<OddsEntry> oddsEntryList;
    private Date timestamp;

    public OddsChangeDto(String eventId, List<OddsEntry> oddsEntryList) {
        this.messageType = EMessageType.ODDS_CHANGE;
        this.eventId = eventId;
        this.oddsEntryList = oddsEntryList;
        this.timestamp = new Date();
    }
}
