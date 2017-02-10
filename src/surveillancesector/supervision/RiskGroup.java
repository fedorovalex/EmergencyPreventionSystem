package surveillancesector.supervision;


public enum RiskGroup {
    NONE(1, 2),
    MINOR(3, 4),
    NORMAL(5, 7),
    MAJOR(8, 13),
    CRITICAL(14, Integer.MAX_VALUE);

    private final int maxCount;
    private final int minCount;

    private RiskGroup(int minCount, int maxCount) {
        this.maxCount = maxCount;
        this.minCount = minCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public int getMinCount() {
        return minCount;
    }

    public static RiskGroup identifyGroup(int count) {
        for (RiskGroup riskGroup: RiskGroup.values()) {
            if (count <= riskGroup.getMaxCount() && count >= riskGroup.getMinCount()) {
                return riskGroup;
            }
        }
        return NONE;
    }
}
