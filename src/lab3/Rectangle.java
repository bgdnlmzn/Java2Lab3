package lab3;

import java.util.ArrayList;
import java.util.Arrays;

public class Rectangle extends Shapes implements Area, Cyrcle {
    public Rectangle(String name, int[] numbers1, int[] numbers2, int[] numbers3, int[] numbers4) {
        super(name, new ArrayList<>(Arrays.asList(numbers1, numbers2, numbers3, numbers4)));
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void draw() {}

    @Override
    public Shapes move(int ed_x, int ed_y) {
        return new Rectangle(super.name + " (" + (super.edit_num + 1) + ")", new int[] {super.coordinates.get(0)[0] + ed_x, super.coordinates.get(0)[1] + ed_y}, new int[] {super.coordinates.get(1)[0] + ed_x, super.coordinates.get(1)[1] + ed_y}, new int[] {super.coordinates.get(2)[0] + ed_x, super.coordinates.get(2)[1] + ed_y}, new int[] {super.coordinates.get(3)[0] + ed_x, super.coordinates.get(3)[1] + ed_y});
    }

    @Override
    public double count_area() {
        double diagonal = Math.sqrt(Math.pow(Math.abs(coordinates.get(0)[0] - coordinates.get(2)[0]), 2) + Math.pow(Math.abs(coordinates.get(0)[1] - coordinates.get(2)[1]), 2));

        double side_1 = Math.sqrt(Math.pow(Math.abs(coordinates.get(0)[0] - coordinates.get(1)[0]), 2) + Math.pow(Math.abs(coordinates.get(0)[1] - coordinates.get(1)[1]), 2));
        double side_2 = Math.sqrt(Math.pow(Math.abs(coordinates.get(1)[0] - coordinates.get(2)[0]), 2) + Math.pow(Math.abs(coordinates.get(1)[1] - coordinates.get(2)[1]), 2));
        double half_meter_1 = (side_1 + side_2 + diagonal) / 2.0;

        double side_3 = Math.sqrt(Math.pow(Math.abs(coordinates.get(2)[0] - coordinates.get(3)[0]), 2) + Math.pow(Math.abs(coordinates.get(2)[1] - coordinates.get(3)[1]), 2));
        double side_4 = Math.sqrt(Math.pow(Math.abs(coordinates.get(3)[0] - coordinates.get(0)[0]), 2) + Math.pow(Math.abs(coordinates.get(3)[1] - coordinates.get(0)[1]), 2));
        double half_meter_2 = (side_3 + side_4 + diagonal) / 2.0;

        return Math.sqrt(half_meter_1 * (half_meter_1 - side_1) * (half_meter_1 * side_2) * (half_meter_1 * diagonal)) + Math.sqrt(half_meter_2 * (half_meter_2 - side_3) * (half_meter_2 * side_4) * (half_meter_2 * diagonal));
    }

    @Override
    public Shapes enlarge(double x) {
        double centre_1x = (coordinates.get(0)[0] + coordinates.get(2)[0]) / 2.0; //y = k1 * x + b1
        double centre_1y = (coordinates.get(0)[1] + coordinates.get(2)[1]) / 2.0;
        double k_1 = (double) (coordinates.get(2)[1] - coordinates.get(0)[1]) / (double) (coordinates.get(2)[0] - coordinates.get(0)[0]);
        double b_1 = centre_1y - k_1 * centre_1x;

        double centre_2x = (coordinates.get(1)[0] + coordinates.get(3)[0]) / 2.0; //y = k2 * x + b2
        double centre_2y = (coordinates.get(1)[1] + coordinates.get(3)[1]) / 2.0;
        double k_2 = (double) (coordinates.get(3)[1] - coordinates.get(1)[1]) / (double) (coordinates.get(3)[0] - coordinates.get(1)[0]);
        double b_2 = centre_2y - k_2 * centre_2x;

        double main_x = (b_2 - b_1) / (k_1 - k_2);
        double main_y = k_1 * main_x + b_1;

        int x_0 = (int) (x * coordinates.get(0)[0] - main_x);
        int y_0 = (int) (x * coordinates.get(0)[1] - main_y);

        int x_1 = (int) (x * coordinates.get(1)[0] - main_x);
        int y_1 = (int) (x * coordinates.get(1)[1] - main_y);

        int x_2 = (int) (x * coordinates.get(2)[0] - main_x);
        int y_2 = (int) (x * coordinates.get(2)[1] - main_y);

        int x_3 = (int) (x * coordinates.get(3)[0] - main_x);
        int y_3 = (int) (x * coordinates.get(3)[1] - main_y);

        return new Rectangle(super.name + " (" + (super.edit_num + 1) + ")", new int[] {x_0, y_0}, new int[] {x_1, y_1}, new int[] {x_2, y_2}, new int[] {x_3, y_3});
    }

    @Override
    public Ellipse build_cyrcle() {
        double sc_mult_012 = (coordinates.get(0)[0] - coordinates.get(1)[0]) * (coordinates.get(1)[0] - coordinates.get(2)[0]) + (coordinates.get(0)[1] - coordinates.get(1)[1]) * (coordinates.get(1)[1] - coordinates.get(2)[1]);
        double angle_012 = (180 / Math.PI) * Math.acos(sc_mult_012 / (Math.sqrt(Math.pow(coordinates.get(0)[0] - coordinates.get(1)[0], 2) + Math.pow(coordinates.get(0)[1] - coordinates.get(1)[1], 2)) * Math.sqrt(Math.pow(coordinates.get(2)[0] - coordinates.get(1)[0], 2) + Math.pow(coordinates.get(2)[1] - coordinates.get(1)[1], 2))));

        double sc_mult_032 = (coordinates.get(0)[0] - coordinates.get(3)[0]) * (coordinates.get(3)[0] - coordinates.get(2)[0]) + (coordinates.get(0)[1] - coordinates.get(3)[1]) * (coordinates.get(3)[1] - coordinates.get(2)[1]);
        double angle_032 = (180 / Math.PI) * Math.acos(sc_mult_032 / (Math.sqrt(Math.pow(coordinates.get(0)[0] - coordinates.get(3)[0], 2) + Math.pow(coordinates.get(0)[1] - coordinates.get(3)[1], 2)) * Math.sqrt(Math.pow(coordinates.get(2)[0] - coordinates.get(3)[0], 2) + Math.pow(coordinates.get(2)[1] - coordinates.get(3)[1], 2))));

        double sc_mult_103 = (coordinates.get(0)[0] - coordinates.get(3)[0]) * (coordinates.get(0)[0] - coordinates.get(1)[0]) + (coordinates.get(0)[1] - coordinates.get(3)[1]) * (coordinates.get(0)[1] - coordinates.get(1)[1]);
        double angle_103 = (180 / Math.PI) * Math.acos(sc_mult_103 / (Math.sqrt(Math.pow(coordinates.get(0)[0] - coordinates.get(3)[0], 2) + Math.pow(coordinates.get(0)[1] - coordinates.get(3)[1], 2)) * Math.sqrt(Math.pow(coordinates.get(0)[0] - coordinates.get(1)[0], 2) + Math.pow(coordinates.get(0)[1] - coordinates.get(1)[1], 2))));

        double sc_mult_123 = (coordinates.get(1)[0] - coordinates.get(2)[0]) * (coordinates.get(2)[0] - coordinates.get(3)[0]) + (coordinates.get(1)[1] - coordinates.get(2)[1]) * (coordinates.get(2)[1] - coordinates.get(3)[1]);
        double angle_123 = (180 / Math.PI) * Math.acos(sc_mult_123 / (Math.sqrt(Math.pow(coordinates.get(1)[0] - coordinates.get(2)[0], 2) + Math.pow(coordinates.get(1)[1] - coordinates.get(2)[1], 2)) * Math.sqrt(Math.pow(coordinates.get(2)[0] - coordinates.get(3)[0], 2) + Math.pow(coordinates.get(2)[1] - coordinates.get(3)[1], 2))));

        if (angle_012 + angle_032 != 180 || angle_103 + angle_123 != 180)
            return null;

        double half_02_x = (coordinates.get(0)[0] + coordinates.get(2)[0]) / 2.0;
        double half_02_y = (coordinates.get(0)[1] + coordinates.get(2)[1]) / 2.0;

        double half_12_x = (coordinates.get(1)[0] + coordinates.get(2)[0]) / 2.0;
        double half_12_y = (coordinates.get(1)[1] + coordinates.get(2)[1]) / 2.0;

        double a = coordinates.get(0)[0] - coordinates.get(2)[0];
        double b = coordinates.get(0)[1] - coordinates.get(2)[1];
        double c = coordinates.get(1)[0] - coordinates.get(2)[0];
        double d = coordinates.get(1)[1] - coordinates.get(2)[1];

        int main_y = (int) ((d * half_12_y + c * half_12_x - (c * b * half_02_y / a) - (a * c * half_02_x / a) * a) / (a * d - d * c));
        int main_x = (int) ((b * half_02_y - b * main_y + half_02_x * a) / a);

        int height = (int) Math.sqrt(Math.pow(main_x - coordinates.get(0)[0], 2) + Math.pow(main_y - coordinates.get(0)[1], 2) * 2);

        return new Ellipse(super.name + " (окружность)", new int[] {main_x, main_y}, height, height, 0);

    }
}

