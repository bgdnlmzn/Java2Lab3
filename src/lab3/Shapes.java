package lab3;

import java.util.ArrayList;

public abstract class Shapes {

    protected ArrayList<int[]> coordinates;

    protected String name;
    protected int edit_num = 1;

    public Shapes(String name, ArrayList<int[]> coordinates) {
        this.coordinates = new ArrayList<>(coordinates);
        this.name = name;
    }

    public Shapes() {}

    public String toString() {
        StringBuilder options = new StringBuilder(name).append("\nКооординаты точек: ");
        for (int i = 0; i < coordinates.size(); i++) {
            options.append("{").append(coordinates.get(i)[0]).append(", ").append(coordinates.get(i)[1]).append("}");
            if (i != coordinates.size() - 1)
                options.append(", ");
        }
        options.append("\n\n");
        return options.toString();
    }
    public abstract void draw();
    public abstract Shapes move(int ed_x, int ed_y);

    public ArrayList<int[]> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<int[]> coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public ArrayList<int[]> coordinates() {
        return this.coordinates;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Shapes newObj = (Shapes) obj;
        boolean cond = true;
        for (int i = 0; i < coordinates.size(); i++) {
            if (this.coordinates.get(i)[0] != newObj.coordinates.get(i)[0] || this.coordinates.get(i)[1] != newObj.coordinates.get(i)[1]) {
                cond = false;
                break;
            }
        }
        return this.name.compareTo(newObj.name) == 0 && cond;
    }

    @Override
    public int hashCode() {
        //System.out.println(coordinates.hashCode());
        return 31 * this.name.hashCode() + coordinates.hashCode() + coordinates.get(0)[1];
    }
}


