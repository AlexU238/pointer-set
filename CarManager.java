import java.util.*;

public class CarManager {

    private List<Car> carList;
    private ArrayList<Integer> carIndexBodyType = new ArrayList<Integer>();
    private LinkedHashSet<Car> carLinkedHashSet;


    public CarManager(List<Car> carList, LinkedHashSet<Car> carLinkedHashSet) {
        this.carList = carList;
        this.carLinkedHashSet = carLinkedHashSet;
    }

    Scanner in = new Scanner(System.in);

    Car getCar(String brand) {

        if (CarBrands.valueOf(brand).equals(CarBrands.AUDI)) {
            return new Car(new Body("BLACK", CarBrands.AUDI, new Tank(CarBrands.AUDI.getTankMaxVolume(), 30), BodyTypes.SEDAN), new SteeringWheel(35), new Engine(CarBrands.AUDI.getEngiveVolume(), CarBrands.AUDI.getEnginePower()), new Chassis(2.0f));
        } else if (CarBrands.valueOf(brand).equals(CarBrands.BMW)) {
            return new Car(new Body("BLUE", CarBrands.BMW, new Tank(CarBrands.BMW.getTankMaxVolume(), 30), BodyTypes.COUPE), new SteeringWheel(35), new Engine(CarBrands.BMW.getEngiveVolume(), CarBrands.BMW.getEnginePower()), new Chassis(2.0f));
        } else if (CarBrands.valueOf(brand).equals(CarBrands.DODGE)) {
            return new Car(new Body("WHITE", CarBrands.DODGE, new Tank(CarBrands.DODGE.getTankMaxVolume(), 30), BodyTypes.MINIVAN), new SteeringWheel(40), new Engine(CarBrands.DODGE.getEngiveVolume(), CarBrands.DODGE.getEnginePower()), new Chassis(2.0f));
        } else if (CarBrands.valueOf(brand).equals(CarBrands.HONDA)) {
            return new Car(new Body("RED", CarBrands.HONDA, new Tank(CarBrands.HONDA.getTankMaxVolume(), 30), BodyTypes.HATCHBACK), new SteeringWheel(30), new Engine(CarBrands.HONDA.getEngiveVolume(), CarBrands.HONDA.getEnginePower()), new Chassis(2.0f));
        } else if (CarBrands.valueOf(brand).equals(CarBrands.TOYOTA)) {
            return new Car(new Body("RED", CarBrands.TOYOTA, new Tank(CarBrands.TOYOTA.getTankMaxVolume(), 30), BodyTypes.SUV), new SteeringWheel(45), new Engine(CarBrands.TOYOTA.getEngiveVolume(), CarBrands.TOYOTA.getEnginePower()), new Chassis(2.0f));
        } else
            return null;
    }


    void addCar() {
        System.out.println("Choose a brand:");
        CarBrands.showAll();
        String brand = enterUpperCaseLine();
        try {
            carList.add(getCar(brand));
        } catch (IllegalArgumentException e) {
            System.out.println("No such brand");
        }
    }

    void findDiameter(float d) {
        ListIterator<Car> iterator = carList.listIterator();
        while (iterator.hasNext())
            if (d == iterator.next().getChassis().getWheelPressure()) {
                System.out.println(iterator.previous().toString());
            }
    }


    void findDiameterAndColor(float d, String color) {
        ListIterator<Car> iterator = carList.listIterator();
        while (iterator.hasNext()) {
            iterator.next().checkEqualityOfWheelDiameterAndBodyColor(d, color);
        }
    }

    void changeRedCarSteeringWheel() {
        ListIterator<Car> iterator = carList.listIterator();
        while (iterator.hasNext()) {
            if ("RED".equals(iterator.next().getBody().getColor())) {
                iterator.previous().getSteeringWheel().changeSteeringWheel("MK_2");
            }
        }
        System.out.println("Steering Wheels in RED cars were changed for -> MK_2");
    }

    void doubleDiameterInCarWithNonDefaultSteeringWheel() {
        ListIterator<Car> iterator = carList.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getSteeringWheel().getType() != SteeringWheel.DEFAULT_TYPE) {
                System.out.println("Double pressure");
                iterator.previous().getChassis().doublePressure();
            }
        }
    }

    void replaceCarWithDifferentPressure(float pressure) {
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getChassis().getWheelPressure() != pressure) {
                System.out.println(carList.get(i).getBody().getBrand() + " has different tire pressure. Replacing...");
                carList.remove(i);
                System.out.println("Choose a brand:");
                CarBrands.showAll();
                String brand = enterUpperCaseLine();
                try {
                    carList.add(i, getCar(brand));
                } catch (IllegalArgumentException e) {
                    System.out.println("No such brand, adding HONDA");
                } finally {
                    carList.add(i, getCar("HONDA"));
                }
            }
        }
    }

    void findBodyTypeCar(BodyTypes type) {
        ListIterator<Car> iterator = carList.listIterator();
        while (iterator.hasNext()) {
            if (type.equals(iterator.next().getBody().getType())) {
                System.out.println(carList.get(iterator.previousIndex()).toString());
                carIndexBodyType.add(iterator.previousIndex());
            }
        }
    }

    void replaceWheelTypeToWinterByDiameter(float min, float max) {
        ListIterator<Car> iterator = carList.listIterator();
        while (iterator.hasNext()) {
            float pressureTmp = iterator.next().getChassis().getWheelPressure();
            if (pressureTmp > min && pressureTmp < max) {
                carList.get(iterator.previousIndex()).getChassis().changeAllWheelType(WheelTypes.WINTER);
                System.out.println("Replaced");
            }
        }
    }

    void removeColoredCars(String color) {
        ListIterator<Car> iterator = carList.listIterator();
        while (iterator.hasNext()) {
            if (color.equals(iterator.next().getBody().getColor())) {
                iterator.remove();
            }
        }
    }

    void removeCarsWithSteeringWheelDiameterAndBodyType(float min, float max, BodyTypes type) {
        ListIterator<Car> iterator = carList.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().checkEqualityOfCarParametersWheelDiameterBodyType(min, max, type)) {
                iterator.remove();
            }
        }
    }

    void changeWheelTypeByDiameterAndBodyColor(float diameter, String color, WheelTypes type) {
        Iterator<Car> iterator = carLinkedHashSet.iterator();
        while (iterator.hasNext()) {
            iterator.next().changeWheelTypeByDiameterAndBodyColorInLinkedHashSet(carLinkedHashSet, diameter, color, type);
        }
    }


    void removeCarsWithWheelDiameterInRange(float min, float max) {
        Iterator<Car> iterator = carLinkedHashSet.iterator();
        while (iterator.hasNext()) {
            iterator.next().removeCarsWithDiameterFromLinkedHashSet(min, max, carLinkedHashSet);
        }
    }

    void showCarsWithWheelTypeAndDiameter(float min, float max, WheelTypes type) {
        Iterator<Car> iterator = carLinkedHashSet.iterator();
        while (iterator.hasNext()) {
            iterator.next().checkEqualityLinkedHashSetWheelTypeAndDiameter(carLinkedHashSet, min, max, type);
        }
    }


    String enterLine() {
        return in.nextLine();
    }

    String enterUpperCaseLine() {
        return enterLine().toUpperCase();
    }

    BodyTypes enterBodyType() {
        return BodyTypes.valueOf(enterUpperCaseLine());
    }

    WheelTypes enterWheelType() {
        return WheelTypes.valueOf(enterUpperCaseLine());
    }

    float enterFloatParameter() {
        return in.nextFloat();
    }
}