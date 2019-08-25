import java.util.Objects;

public class Battery {
    private int charge;
    private static final int MAX_CHARGE = 100;

    public Battery(int charge) {
        if (charge > 0) {
            if (charge <= MAX_CHARGE) {
                this.charge = charge;
            } else {
                this.charge = MAX_CHARGE;
                System.out.println("Battary MAX");
            }
        } else {
            System.out.println("No charge");
            this.charge = 0;
        }
    }

    @Override
    public String toString() {
        return "Battery{" +
                "charge=" + charge +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battery battery = (Battery) o;
        return charge == battery.charge;
    }

    @Override
    public int hashCode() {
        return Objects.hash(charge);
    }

    int giveCharge() {
        int spark = 20;
        charge = -spark;
        return spark;
    }

    void reCharge() {
        charge = MAX_CHARGE;
    }

    public int getCharge() {
        return charge;
    }
}
