package za.co.sporty.provider.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import za.co.sporty.provider.constants.EProviderMarket;
import za.co.sporty.provider.domain.AlphaBetSettlement;
import za.co.sporty.provider.domain.AlphaOddsChange;
import za.co.sporty.provider.domain.BetaBetSettlement;
import za.co.sporty.provider.domain.BetaOddsChange;
import za.co.sporty.provider.standardized.OddsChangeDto;
import za.co.sporty.provider.standardized.OddsEntry;
import za.co.sporty.provider.standardized.SettlementDto;
import za.co.sporty.provider.constants.IProviderConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProviderUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProviderUtil.class);
    /**
     * Converts the message payload to the correct object
     * @param messagePayload
     * @return
     */
    public static Object getPayload(JsonNode messagePayload) {
        String type;

        if (messagePayload.get(IProviderConstants.BETA_KEY_TYPE) != null) {
            type = messagePayload.get(IProviderConstants.BETA_KEY_TYPE).asString();
        }
        else if (messagePayload.get(IProviderConstants.ALPHA_KEY_TYPE) != null) {
            type = messagePayload.get(IProviderConstants.ALPHA_KEY_TYPE).asString();
        } else {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();

        return switch (type) {
            case IProviderConstants.ALPHA_ODDS_TYPE -> mapper.convertValue(messagePayload, AlphaOddsChange.class);
            case IProviderConstants.ALPHA_SETTLEMENT_TYPE -> mapper.convertValue(messagePayload, AlphaBetSettlement.class);
            case IProviderConstants.BETA_ODDS_TYPE -> mapper.convertValue(messagePayload, BetaOddsChange.class);
            case IProviderConstants.BETA_SETTLEMENT_TYPE -> mapper.convertValue(messagePayload, BetaBetSettlement.class);
            default -> throw new IllegalArgumentException("Invalid message type: " + type);
        };
    }

    /**
     * Returns the message payload in a standardized format
     *
     * @param providerOddsChange
     * @return
     */
    public static OddsChangeDto mapToStandardizedOddsChange(Object providerOddsChange) {

        if (providerOddsChange instanceof AlphaOddsChange alphaOddsChange) {
            LOGGER.info("AlphaOddsChange: " + "\nEvent Id: " + alphaOddsChange.getEvent_id() + "\nMessageType: "+ alphaOddsChange.getMsg_type() + "\n" + alphaOddsChange);
            return new OddsChangeDto(alphaOddsChange.getEvent_id(), mapToOddsEntry(alphaOddsChange.getValues()));
        }
        else if (providerOddsChange instanceof BetaOddsChange betaOddsChange) {
            LOGGER.info("BetaOddsChange: " + "\nEvent Id: " + betaOddsChange.getEvent_id() + "\nMessageType: "+ betaOddsChange.getType() + "\n" + betaOddsChange);
            return new OddsChangeDto(betaOddsChange.getEvent_id(), mapToOddsEntry(betaOddsChange.getOdds()));
        }

        return null;
    }

    /**
     * Maps the markets entry to the OddsEntry object
     * @param markets
     * @return
     */
    private static List<OddsEntry> mapToOddsEntry(Map<String, Double> markets) {
        List<OddsEntry> oddsEntryList = new ArrayList<>();
        if(markets != null && !markets.isEmpty()) {
            markets.forEach(
                    (key, value) -> {
                        oddsEntryList.add(new OddsEntry(EProviderMarket.fromString(key), value));
                    }
            );
        }
        return oddsEntryList;
    }

    /**
     * Maps the provider settlement to the standardized format
     * @param providerSettlement
     * @return
     */
    public static SettlementDto mapToStandardizedSettlement(Object providerSettlement) {
        if (providerSettlement instanceof AlphaBetSettlement alphaBetSettlement) {
            LOGGER.info("AlphaBetSettlement: " + "\nEvent Id: " + alphaBetSettlement.getEvent_id() + "\nMessageType: "+ alphaBetSettlement.getMsg_type() + "\n" + alphaBetSettlement);
            return new SettlementDto(alphaBetSettlement.getEvent_id(), EProviderMarket.fromString(alphaBetSettlement.getOutcome()).getValue());
        }
        else if (providerSettlement instanceof BetaBetSettlement betaBetSettlement) {
            LOGGER.info("BetaBetSettlement: " + "\nEvent Id: " + betaBetSettlement.getEvent_id() + "\nMessageType: "+ betaBetSettlement.getType() + "\n" + betaBetSettlement);
            return new SettlementDto(betaBetSettlement.getEvent_id(), EProviderMarket.fromString(betaBetSettlement.getResult()).getValue());
        }
        return null;
    }
}
