package surveillanceSector;


public enum RiskGroup {
    NONE(2),
    MINOR(4),
    NORMAL(7),
    MAJOR(13),
    CRITICAL(Integer.MAX_VALUE);

    private final int maxCount;

    private RiskGroup(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public static RiskGroup identifyGroup(int count) {
        for (RiskGroup riskGroup: RiskGroup.values()) {
            if (riskGroup.getMaxCount() >= count) {
                return riskGroup;
            }
        }
        return null;
    }
}
