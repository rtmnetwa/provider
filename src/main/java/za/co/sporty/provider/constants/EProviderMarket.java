package za.co.sporty.provider.constants;

public enum EProviderMarket {
    HOME("HOME"),
    DRAW("DRAW"),
    AWAY("AWAY");

    private String value;
    private EProviderMarket(String value) {
        this.value = value;
    }
    public static EProviderMarket fromString(String value) {
        return switch (value.toLowerCase()) {
            case IProviderConstants.ALPHA_MARKET_HOME , IProviderConstants.BETA_MARKET_HOME -> HOME;
            case IProviderConstants.ALPHA_MARKET_DRAW , IProviderConstants.BETA_MARKET_DRAW -> DRAW;
            case IProviderConstants.ALPHA_MARKET_AWAY ,IProviderConstants.BETA_MARKET_AWAY -> AWAY;
            default -> throw new IllegalArgumentException("Invalid market value: " + value);
        };
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
