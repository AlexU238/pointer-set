import java.util.Objects;

public class Wheel {

    private float pressure;
    private static final float MAX_PRESSURE = 3.2f;
    private static final WheelTypes DEFAULT_TYPE = WheelTypes.ALL_SEASON;
    private WheelTypes type;

    public Wheel(float pressure) {
        this.type = WheelTypes.ALL_SEASON;
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "pressure=" + pressure +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wheel wheel = (Wheel) o;
        return Float.compare(wheel.pressure, pressure) == 0 &&
                type == wheel.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pressure, type);
    }

    void pumpToMax() {
        pressure = MAX_PRESSURE;
    }

    void pumpAlltoMax(Wheel[] wheels) {
        for (Wheel w : wheels) {
            w.pumpToMax();
        }
    }

    void changeType(WheelTypes type) {
        this.type = type;
    }

    void pumpTo(float p) {
        pressure = pressure + p;
    }

    public float getPressure() {
        return pressure;
    }

    public WheelTypes getType() {
        return type;
    }
}
