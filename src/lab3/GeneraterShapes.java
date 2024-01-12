package lab3;

import java.util.ArrayList;

public class GeneraterShapes {

    private static int num_gr = 0;
    public static ArrayList<Shapes> getPoint() {
        Shapes a = new Point("Point 1", new int[] {2, 4});
        Shapes b = new Point("Point 2", new int[] {3, 5});
        Shapes c = new Point("Point 3", new int[] {3, 1});
        Shapes d = new Point("Point 4", new int[] {3, 6});
        ArrayList<Shapes> mas = new ArrayList<>();
        mas.add(a);
        mas.add(b);
        mas.add(c);
        mas.add(d);
        return mas;
    }

    public static ArrayList<Shapes> getTriangle() {
        Shapes a = new Triangle("Triangle 1", new int[] {2, 4}, new int[] {3, 5}, new int[] {6, 7});
        Shapes b = new Triangle("Triangle 2", new int[] {12, 4}, new int[] {7, 4}, new int[] {8, 7});
        Shapes c = new Triangle("Triangle 3", new int[] {3, 1}, new int[] {2, 9}, new int[] {6, 8});
        Shapes d = new Triangle("Triangle 4", new int[] {2, 3}, new int[] {4, 7}, new int[] {3, 6});
        ArrayList<Shapes> mas = new ArrayList<>();
        mas.add(a);
        mas.add(b);
        mas.add(c);
        mas.add(d);
        return mas;
    }

    public static ArrayList<Shapes> getRectangle() {
        Shapes a = new Rectangle("Rectangle 1", new int[] {2, 4}, new int[] {3, 5}, new int[] {6, 7}, new int[] {2, 9});
        Shapes b = new Rectangle("Rectangle 2", new int[] {3, 4}, new int[] {3, 7}, new int[] {4, 4}, new int[] {4, 7});
        Shapes c = new Rectangle("Rectangle 3", new int[] {3, 1}, new int[] {2, 9}, new int[] {6, 8}, new int[] {12, 21});
        Shapes d = new Rectangle("Rectangle 4", new int[] {2, 3}, new int[] {4, 7}, new int[] {3, 6}, new int[] {1, 1});
        ArrayList<Shapes> mas = new ArrayList<>();
        mas.add(a);
        mas.add(b);
        mas.add(c);
        mas.add(d);
        return mas;
    }

    public static ArrayList<Shapes> getEllipse() {
        Shapes a = new Ellipse("Ellipse 1", new int[] {3, 12}, 12, 3, 35);
        Shapes b = new Ellipse("Ellipse 2", new int[] {4, 11}, 5, 1, 0);
        Shapes c = new Ellipse("Ellipse 3", new int[] {4, 4}, 4, 5, 90);
        Shapes d = new Ellipse("Ellipse 4", new int[] {6, 9}, 6, 3, 5);
        ArrayList<Shapes> mas = new ArrayList<>();
        mas.add(a);
        mas.add(b);
        mas.add(c);
        mas.add(d);
        return mas;
    }

    public static Group getAllGroups() {
        Group gr = new Group(getPoint());
        gr.add(getTriangle());
        gr.add(getRectangle());
        gr.add(getEllipse());
        return gr;
    }

    public static Group[] nestedGroups() {
        Shapes a = new Point("Point 1 (Group - " + (num_gr + 1) + ")", new int[] {9, 14});
        Shapes b = new Ellipse("Ellipse 2 (Group - " + (num_gr + 1) + ")", new int[] {4, 11}, 5, 1, 0);
        Shapes c = new Rectangle("Rectangle 3 (Group - " + (num_gr + 1) + ")", new int[] {3, 4}, new int[] {2, 1}, new int[] {6, 12}, new int[] {11, 2});
        Shapes d = new Triangle("Triangle 4 (Group - " + (num_gr + 1) + ")", new int[] {3, 3}, new int[] {4, 4}, new int[] {5, 5});
        ArrayList<Shapes> mas_1 = new ArrayList<>();
        mas_1.add(a);
        mas_1.add(b);
        mas_1.add(c);
        mas_1.add(d);
        num_gr += 1;
        for (int i = 0; i < mas_1.size(); i++) {
            mas_1.get(i).toString();
        }

        Shapes e = new Point("Point 1 (Group - " + (num_gr + 1) + ")", new int[] {2, 12});
        Shapes f = new Ellipse("Ellipse 2 (Group - " + (num_gr + 1) + ")", new int[] {6, 17}, 3, 2, 4);
        Shapes g = new Rectangle("Rectangle 3 (Group - " + (num_gr + 1) + ")", new int[] {34, 4}, new int[] {5, 1}, new int[] {3, 1}, new int[] {1, 2});
        Shapes h = new Triangle("Triangle 4 (Group - " + (num_gr + 1) + ")", new int[] {5, 5}, new int[] {5, 9}, new int[] {12, 14});
        ArrayList<Shapes> mas_2 = new ArrayList<>();
        mas_2.add(e);
        mas_2.add(f);
        mas_2.add(g);
        mas_2.add(h);
        num_gr += 1;
        for (int i = 0; i < mas_2.size(); i++) {
            mas_2.get(i).toString();
        }
        return new Group[] {new Group(mas_1), new Group(mas_2)};
    }

    public static void moveTest() {
        Group gr = getAllGroups();
        int x1 = gr.getMas().get(0).getCoordinates().get(0)[0];
        int y1 = gr.getMas().get(0).getCoordinates().get(0)[1];
        System.out.println(gr.toString());
        gr.move(2, 4);
        int x2 = gr.getMas().get(0).getCoordinates().get(0)[0];
        int y2 = gr.getMas().get(0).getCoordinates().get(0)[1];
        System.out.println("КООРДИНАТЫ СМЕЩЕНЫ НА ВЕКТОР {2; 4}\n\n");
        System.out.println(gr.toString());
        if (x1 == x2 - 2 && y1 == y2 - 4)
            System.out.println("Успех!");
        else
            System.out.println("Провал(");
    }

    public static void areaTest(double x) {
        Group gr = getAllGroups();
        System.out.println(gr.toString());
        double s1 = Math.ceil(gr.count_area());
        System.out.println("Суммарная площадь до: " + s1 + "\n");
        gr.enlarge(2);
        double s2 = Math.ceil(gr.count_area());
        System.out.println(gr.toString());
        System.out.println("Суммарная площадь после: " + s2 + "\n");
        if (Math.floor(Math.sqrt(s2/s1)) == x)
            System.out.println("Успех!");
        else
            System.out.println("Провал(");
    }

    public static void cyrcleTest() {
        Group gr = getAllGroups();
        ArrayList<Shapes> figures = new ArrayList<>();
        ArrayList<Ellipse> cyrcles = new ArrayList<>();
        for (int i = 0; i < gr.getMas().size(); i++) {
            if (gr.getMas().get(i) instanceof Triangle || gr.getMas().get(i) instanceof Rectangle)
                figures.add(gr.getMas().get(i));
        }
        for (int i = 0; i < figures.size(); i++) {
            boolean fl = true;
            if (figures.get(i) instanceof Triangle)
                cyrcles.add(((Triangle) figures.get(i)).build_cyrcle());
            else if (figures.get(i) instanceof Rectangle)
                cyrcles.add(((Rectangle) figures.get(i)).build_cyrcle());
            if (cyrcles.get(i) == null)
                fl = false;
            if (fl)
                System.out.println(figures.get(i).toString() + cyrcles.get(i).toString());
        }
    }

    public static void nestedGroupTest() {
        Group[] gr = nestedGroups();
        num_gr += 1;
        System.out.println("Общая группа: ");
        for (int i = 0; i < gr.length; i++)
            for (int j = 0; j < gr[i].getMas().size(); j++)
                System.out.println((gr[i].getMas().get(j).toString()));

        for (int i = 0; i < gr.length; i++)
            for (int j = 0; j < gr[i].getMas().size(); j++)
                System.out.println((gr[i].getMas().get(j).toString()).replaceFirst("Group - ", "Group - 3/"));
    }
}


