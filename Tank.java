import java.util.Objects;

public class Tank {

    private float fuelVolume;
    private float maxFuelVolume;

    public Tank(float maxFuelVolume,float fuelVolume) {
        this.maxFuelVolume=maxFuelVolume;
        if (fuelVolume > 0) {
            if (fuelVolume <= maxFuelVolume) {
                this.fuelVolume = fuelVolume;
                System.out.println("Fuel level: " + fuelVolume);
            } else {
                this.fuelVolume = maxFuelVolume;
                System.out.println("Fuel MAX");
            }
        } else {
            System.out.println("No Fuel");
            this.fuelVolume = 0;
        }
    }

    float giveFuel() {
        float drop = 0.1f;
        fuelVolume = fuelVolume - drop;
        return drop;
    }

    void fuelFeed(float fuel) {
        fuelVolume = fuelVolume - fuel;
    }

    void reFuel(float fuel) {
        fuelVolume = fuelVolume + fuel;
        if (fuelVolume > maxFuelVolume) {
            fuelVolume = maxFuelVolume;
            System.out.println("Fuel level:" + fuelVolume);
        } else {
            System.out.println("Fuel level: " + fuelVolume);
        }

    }

    @Override
    public String toString() {
        return "Tank{" +
                "fuelVolume=" + fuelVolume +
                ", maxFuelVolume=" + maxFuelVolume +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tank tank = (Tank) o;
        return Float.compare(tank.fuelVolume, fuelVolume) == 0 &&
                Float.compare(tank.maxFuelVolume, maxFuelVolume) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fuelVolume, maxFuelVolume);
    }

    public float getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(float fuelVolume) {
        this.fuelVolume = fuelVolume;
    }
}
