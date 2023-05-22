package app.models.enums;

public enum TopUpPlan {
    FOUR_DEVICE(4, 50),
    TEN_DEVICE(10, 100);

    int numberOfDevicesAllowed;
    int amount;

    TopUpPlan(int numberOfDevicesAllowed, int amount) {
        this.numberOfDevicesAllowed = numberOfDevicesAllowed;
        this.amount = amount;
    }

    public int getMonthlyCharge() {
        return this.amount;
    }
}
