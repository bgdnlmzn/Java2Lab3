package lab3;

import java.util.ArrayList;
import java.util.Collections;

public class Ellipse extends Shapes implements Area {

    private int height, weight, angle;

    public Ellipse(String name, int[] numbers, int height, int weight, int angle) {
        super(name, new ArrayList<>(Collections.singletonList(numbers)));
        this.height = height;
        this.weight = weight;
        this.angle = angle;
    }

    @Override
    public String toString() {
        return super.toString().substring(0, super.toString().length() - 2) + "\nВысота: " + height + "\nШирина: " + weight + "\nУгол наклона: " + angle + "\n\n";
    }

    @Override
    public void draw() {}

    @Override
    public Shapes move(int ed_x, int ed_y) {
        return new Ellipse(super.name + " (" + (super.edit_num + 1) + ")", new int[] {super.coordinates.get(0)[0] + ed_x, super.coordinates.get(0)[1] + ed_y}, height, weight, angle);
    }

    @Override
    public double count_area() {
        double half_h = height / 2.0, half_w = weight / 2.0;

        return Math.PI * half_h * half_w;
    }

    @Override
    public Shapes enlarge(double x) {
        return new Ellipse(super.name + " (" + (super.edit_num + 1) + ")", super.coordinates.get(0), (int) (height * x), (int) (weight * x), angle);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}
