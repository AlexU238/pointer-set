import java.util.Arrays;

public class Chassis {
    private Wheel[] frontWheels;
    private Wheel[] backWheels;


    public Chassis(float pressure) {
        this.frontWheels = new Wheel[]{new Wheel(pressure), new Wheel(pressure)};
        this.backWheels = new Wheel[]{new Wheel(pressure), new Wheel(pressure)};
    }

    @Override
    public String toString() {
        return "Chassis{" +
                "frontWheels=" + Arrays.toString(frontWheels) +
                ", backWheels=" + Arrays.toString(backWheels) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chassis chassis = (Chassis) o;
        return Arrays.equals(frontWheels, chassis.frontWheels) &&
                Arrays.equals(backWheels, chassis.backWheels);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(frontWheels);
        result = 31 * result + Arrays.hashCode(backWheels);
        return result;
    }

    WheelTypes getWheelType(){
        WheelTypes type=null;
        for(Wheel w: frontWheels){
            type= w.getType();
            break;
        }
        return type;
    }

    void turn(String dir) {
        System.out.println("Front wheels turning " + dir);
    }

    void changeFrontWheel(int index, Wheel wheel) {
        frontWheels[index] = wheel;
    }

    void changeBackWheel(int index, Wheel wheel) {
        backWheels[index] = wheel;
    }

    void changeAllWheelType(WheelTypes type) {
        for (Wheel fW : frontWheels) {
            fW.changeType(type);
        }
        for (Wheel bW : backWheels) {
            bW.changeType(type);
        }
    }

    void pumpAllTo(float p) {
        for (Wheel fW : frontWheels) {
            fW.pumpTo(p);
        }
        for (Wheel bW : backWheels) {
            bW.pumpTo(p);
        }
    }

    void pumpToMax() {
        for (Wheel fW : frontWheels) {
            fW.pumpToMax();
        }
        for (Wheel bW : backWheels) {
            bW.pumpToMax();
        }
    }

    float getWheelPressure() {
        float pTmp = 0;
        for (Wheel fW : frontWheels) {
            pTmp = pTmp + fW.getPressure();
        }
        for (Wheel bW : backWheels) {
            pTmp = pTmp + bW.getPressure();
        }
        return pTmp / (frontWheels.length + backWheels.length);
    }

    void doublePressure(){
        this.pumpAllTo(this.getWheelPressure()*2);
    }

    public Wheel[] getFrontWheels() {
        return frontWheels;
    }

    public Wheel[] getBackWheels() {
        return backWheels;
    }

}
