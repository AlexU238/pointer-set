import java.util.LinkedHashSet;
import java.util.Objects;

public class Car {
    private Body body;
    private SteeringWheel steeringWheel;
    private Engine engine;
    private double milage;
    private Chassis chassis;

    public Car(Body body, SteeringWheel steeringWheel, Engine engine, Chassis chassis) {
        this.body = body;
        this.steeringWheel = steeringWheel;
        this.engine = engine;
        this.milage = 0;
        this.chassis = chassis;
    }

    void start(Battery battery, Tank tank) {
        engine.ignite(battery, tank);
    }

    void stop() {
        engine.stop();
    }

    void paint(String paint) {
        body.repaint(paint);
    }

    void changeFrontWheel(int index, Wheel wheel) {
        chassis.changeFrontWheel(index, wheel);
    }

    void changeBackWheel(int index, Wheel wheel) {
        chassis.changeBackWheel(index, wheel);
    }

    void drive(float km) {
        engine.work(body.getTank(), body.getBattery(), km);
        milage += engine.milageFromEngine;
        System.out.println("Milage: " + milage);
    }

    void turn(Chassis chassis, Direction direction) {
        steeringWheel.turn(chassis, direction);
    }

    void pumpAllWheelsTo(float p) {
        chassis.pumpAllTo(p);
    }

    void pumpAllWheelsToMax() {
        chassis.pumpToMax();
    }

    @Override
    public String toString() {
        return "Car{" +
                "body=" + body +
                ", steeringWheel=" + steeringWheel +
                ", engine=" + engine +
                ", milage=" + milage +
                ", chassis=" + chassis +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.milage, milage) == 0 &&
                Objects.equals(body, car.body) &&
                Objects.equals(steeringWheel, car.steeringWheel) &&
                Objects.equals(engine, car.engine) &&
                Objects.equals(chassis, car.chassis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, steeringWheel, engine, milage, chassis);
    }

    public Body getBody() {
        return body;
    }

    public SteeringWheel getSteeringWheel() {
        return steeringWheel;
    }

    public Engine getEngine() {
        return engine;
    }

    public double getMilage() {
        return milage;
    }

    public Chassis getChassis() {
        return chassis;
    }

    void changeWheelType(WheelTypes type) {
        try {
            this.getChassis().changeAllWheelType(type);
        } catch (IllegalArgumentException e) {
            System.out.println("No such type");
        }
    }

    void changeWheelTypeByDiameterAndBodyColorInLinkedHashSet(LinkedHashSet<Car> set, float diameter, String color, WheelTypes type) {
        Car car = this;
        if (diameter == car.getChassis().getWheelPressure() && color.equals(car.getBody().getColor())) {
            this.changeWheelType(type);
        }
    }

    void checkEqualityLinkedHashSetWheelTypeAndDiameter(LinkedHashSet<Car> set, float min, float max, WheelTypes
            type) {
        if (min < this.getChassis().getWheelPressure() && max > this.getChassis().getWheelPressure() && type.equals(this.getChassis().getWheelType())) {
            System.out.println(this.toString());
        }
    }

    boolean checkEqualityOfCarParametersWheelDiameterBodyType(float min, float max, BodyTypes type) {
        if (this.getBody().getType().equals(type) && this.getSteeringWheel().getDiameter() > min && this.getSteeringWheel().getDiameter() < max) {
            return true;
        }
        return false;
    }

    void removeCarsWithDiameterFromLinkedHashSet(float min, float max, LinkedHashSet<Car> set) {
        if (min < this.getChassis().getWheelPressure() && max > this.getChassis().getWheelPressure()) {
            set.remove(this);
        }
    }

    void checkEqualityOfWheelDiameterAndBodyColor(float d, String color) {
        if (d == this.getChassis().getWheelPressure() && color.equals(this.getBody().getColor())) {
            System.out.println(this.getBody().getBrand());
        }
    }


}
