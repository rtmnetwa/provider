package za.co.sporty.provider.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import za.co.sporty.provider.domain.AlphaBetSettlement;
import za.co.sporty.provider.domain.AlphaOddsChange;
import za.co.sporty.provider.domain.BetaBetSettlement;
import za.co.sporty.provider.domain.BetaOddsChange;
import za.co.sporty.provider.standardized.OddsChangeDto;
import za.co.sporty.provider.standardized.SettlementDto;

@Service
public class MessageProcessor {

    private final Logger LOGGER =  LoggerFactory.getLogger(MessageProcessor.class);
    public void processMessage(JsonNode messagePayload) {
        Object payload = ProviderUtil.getPayload(messagePayload);

        if (payload != null) {
            processOddsChange(payload);
            processBetSettlement(payload);
        }
    }

    /**
     * Processes the odds change into a standardized format
     * @param payload
     */
    private void processOddsChange(Object payload) {
        if (payload instanceof AlphaOddsChange alphaOddsChange || payload instanceof BetaOddsChange betaOddsChange) {
            OddsChangeDto standardizedOddsChange = ProviderUtil.mapToStandardizedOddsChange(payload);
            assert standardizedOddsChange != null;
            System.out.println("Console OddsChange: " + standardizedOddsChange);
            LOGGER.info("Logger OddsChange: " + standardizedOddsChange);
        }
    }

    /**
     * Processes the bet settlement into a standardized format
     * @param payload
     */
    private void processBetSettlement(Object payload) {
        if (payload instanceof AlphaBetSettlement alphaBetSettlement || payload instanceof BetaBetSettlement betaBetSettlement) {
            SettlementDto standardizedBetSettlement = ProviderUtil.mapToStandardizedSettlement(payload);
            assert standardizedBetSettlement != null;
            System.out.println("Console BetSettlement: " + standardizedBetSettlement);
            LOGGER.info("Logger BetSettlement: " + standardizedBetSettlement);
        }
    }
}
