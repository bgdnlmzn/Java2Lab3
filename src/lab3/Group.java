package lab3;

import java.util.ArrayList;
import java.util.Collections;

public class Group extends Shapes implements Area {

    private static int num_gr = 1;

    private ArrayList<Shapes> mas = new ArrayList<>();
    private ArrayList<ArrayList<Shapes>> grMas = new ArrayList<>();
    public Group(Shapes[] mas) {
        super();
        Collections.addAll(this.mas, mas);
    }
    public Group(ArrayList<Shapes> mas) {
        super();
        this.mas.addAll(mas);
    }
    public Group() {}

    public Group(Shapes element) {
        super();
        this.mas.add(element);
    }

    public void add (Shapes element) {
        mas.add(element);
    }

    public void add (ArrayList<Shapes> mas) {
        this.mas.addAll(mas);
    }

    public void add (Group elements) {
        grMas.add(elements.getMas());
        num_gr += 1;
        //Collections.addAll(mas, elements.getMas().toArray(new Lab3.Heo_shapes[elements.getMas().size() - 1]));
    }

    public void remove(String name) {
        for (int i = 0; i < mas.size(); i++) {
            if (mas.get(i).getName().equals(name)) {
                mas.remove(i);
                break;
            }
        }
    }

    public void remove(int index) {
        if (mas.size() <= index)
            return;
        mas.remove(index);
    }

    public Shapes getHeoShape(int index) {
        return mas.get(index);
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        for (Shapes ma : mas) {
            description.append(ma.toString());
        }
        return description.toString();
    }

    @Override
    public void draw() {
        for (Shapes ma : mas) {
            ma.draw();
        }
    }

    @Override
    public Shapes move(int ed_x, int ed_y) {
        int len = mas.size();
        for (int i = 0; i < len; i++) {
            mas.set(i, mas.get(i).move(ed_x, ed_y));
        }

        return null;
    }

    @Override
    public double count_area() {
        double area = 0;
        for (Shapes ma : mas) {
            if (ma instanceof Point)
                continue;
            else if (ma instanceof Triangle)
                area += ((Triangle) ma).count_area();
            else if (ma instanceof Rectangle)
                area += ((Rectangle) ma).count_area();
            else
                area += ((Ellipse) ma).count_area();
        }
        return area;
    }

    @Override
    public Shapes enlarge(double x) {
        double area = 0;
        for (int i = 0; i < mas.size(); i++) {
            if (mas.get(i) instanceof Point)
                continue;
            else if (mas.get(i) instanceof Triangle)
                mas.set(i, ((Triangle) mas.get(i)).enlarge(x));
            else if (mas.get(i) instanceof Rectangle)
                mas.set(i, ((Rectangle) mas.get(i)).enlarge(x));
            else
                mas.set(i, ((Ellipse) mas.get(i)).enlarge(x));
        }
        return null;
    }

    public void setMas(ArrayList<Shapes> mas) {
        this.mas = mas;
    }

    public ArrayList<Shapes> getMas() {
        return mas;
    }
}
