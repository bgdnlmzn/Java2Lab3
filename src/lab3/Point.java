package lab3;

import java.util.ArrayList;
import java.util.Collections;

public class Point extends Shapes {
    public Point(String name, int[] numbers) {
        super(name, new ArrayList<>(Collections.singletonList(numbers)));
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void draw() {}

    @Override
    public Shapes move(int ed_x, int ed_y) {
        return new Point(super.name + " (" + (super.edit_num + 1) + ")", new int[] {coordinates.get(0)[0] + ed_x, coordinates.get(0)[1] + ed_y});
    }

}

