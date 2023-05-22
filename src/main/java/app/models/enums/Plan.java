package app.models.enums;

public enum Plan {
    FREE(1),
    PERSONAL(1),
    PREMIUM(3);

    int months;

    Plan(int months) {
        this.months = months;
    }

    public int getMonths() {
        return this.months;
    }

}
