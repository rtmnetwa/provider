package za.co.sporty.provider.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.JsonNode;
import za.co.sporty.provider.service.MessageProcessor;

@RestController
@RequestMapping("provider-alpha")
public class ProviderAlphaController {

    private final MessageProcessor messageProcessor;

    /**
     * Constructor dependency injection
     * @param messageProcessor
     */
    public ProviderAlphaController(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }

    /**
     * Takes in a Generic JsonNode obj and sends it to the message processor for standardization
     * @param messagePayload
     */
    @PostMapping("/feed")
    public void handleMessage(@RequestBody JsonNode messagePayload) {
        messageProcessor.processMessage(messagePayload);
    }
}
