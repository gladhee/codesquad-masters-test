public enum LottoConstants {

    REQUIRED_NUMBER_COUNT(6),
    MIN_NUMBER(1),
    MAX_NUMBER(45);

    private final int value;

    LottoConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
