package app.models.plans;


import app.errors.GenericException;
import app.models.enums.Category;
import app.models.enums.Plan;

public class MusicSubscriptionPlan extends SubscriptionPlan {

    public static final int free = 0;
    public static final int personal = 100;
    public static final int premimum = 250;
    private Plan plan;

    public MusicSubscriptionPlan(Plan plan) {
        super(Category.MUSIC);
        this.plan = plan;
    }

    @Override
    public int getCharge() {
        switch (plan) {
            case PREMIUM: return premimum;
            case PERSONAL: return personal;
            case FREE: return free;
        }
        throw new GenericException("Unavailable plan for category MUSIC");
    }

    @Override
    public int getValidityInMonths() {
        return plan.getMonths();
    }
}
