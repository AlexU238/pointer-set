import java.util.Objects;

public class Engine {

    public double milageFromEngine;
    private float volume;
    private float power;
    private static final float CONSUMPION = 0.1f;
    private boolean ignition = false;
    private boolean fuelAccess = false;

    public Engine(float volume, float power) {
        this.volume = volume;
        this.power = power;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "volume=" + volume +
                ", power=" + power +
                ", ignition=" + ignition +
                ", fuelAccess=" + fuelAccess +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return Double.compare(engine.milageFromEngine, milageFromEngine) == 0 &&
                Float.compare(engine.volume, volume) == 0 &&
                Float.compare(engine.power, power) == 0 &&
                ignition == engine.ignition &&
                fuelAccess == engine.fuelAccess;
    }

    @Override
    public int hashCode() {
        return Objects.hash(milageFromEngine, volume, power, ignition, fuelAccess);
    }

    void ignite(Battery battery, Tank tank) {
        if (battery.giveCharge() == 20) {
            ignition = true;

        } else {
            System.out.println("Battery LOW!!!");
        }

        if (tank.giveFuel() == 0.1f) {
            fuelAccess = true;
        } else {
            System.out.println("Fuel level LOW!!!");
        }

        if (ignition == true && fuelAccess == true) {
            System.out.println("Engine started");
        }
    }

    void work(Tank tank, Battery battery,float km) {
        if (ignition == true && fuelAccess == true) {
            tank.fuelFeed(volume * CONSUMPION * km);
            if (tank.getFuelVolume() <= 0) {
                tank.setFuelVolume(0);
                System.out.println("Tank is empty");
                milageFromEngine = volume * CONSUMPION;
            } else {
                System.out.println("Fuel left " + tank.getFuelVolume());
                milageFromEngine = km;
            }

            battery.reCharge();

        } else {
            System.out.println("Start the car first");
        }

    }

    void stop() {
        ignition = false;
        fuelAccess = false;
        System.out.println("Engine stoped");
    }

    public float getVolume() {
        return volume;
    }


    public float getPower() {
        return power;
    }

}
