import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        ArrayList<Car> cars = new ArrayList<Car>();
        LinkedHashSet<Car> carLinkedHashSet = new LinkedHashSet<Car>();
        CarManager carManager = new CarManager(cars, carLinkedHashSet);

        carLinkedHashSet.add(carManager.getCar("BMW"));
        carLinkedHashSet.add(carManager.getCar("HONDA"));
        carLinkedHashSet.add(carManager.getCar("AUDI"));
        carLinkedHashSet.add(carManager.getCar("DODGE"));
        carLinkedHashSet.add(carManager.getCar("TOYOTA"));
        carLinkedHashSet.add(new Car(new Body("GREEN", CarBrands.TOYOTA, new Tank(CarBrands.TOYOTA.getTankMaxVolume(), 0), BodyTypes.SEDAN),
                new SteeringWheel(30), new Engine(CarBrands.TOYOTA.getEngiveVolume(), 120f), new Chassis(2.0f)));
        cars.add(carManager.getCar("BMW"));

        carManager.changeWheelTypeByDiameterAndBodyColor(carManager.enterFloatParameter(), carManager.enterLine(), carManager.enterWheelType());
        carManager.showCarsWithWheelTypeAndDiameter(carManager.enterFloatParameter(), carManager.enterFloatParameter(), carManager.enterWheelType());
        carManager.removeCarsWithWheelDiameterInRange(carManager.enterFloatParameter(), carManager.enterFloatParameter());


    }
}
