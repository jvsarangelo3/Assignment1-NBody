import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


public class NBody extends JPanel implements ActionListener {
    // creating variables
    private String name, size;
    private double mass, xVelo, yVelo, xForce, yForce;
    private int xCoord, yCoord;
    private Color color;

    // main
    public static void main(String[] args) {
        // temp list created
        Lists<NBody> tempList = null;
        // tempScale stores the scale
        double tempScale = 0;

        // reading file, type in terminal "java NBody nbody_input.txt"
        // to read file
        File input = new File(args[0]);
        try {
            Scanner sc = new Scanner(input);
            // stores the type of list to use, arraylist or linked list
            // the first line always indicates if it's arraylist or linked list
            String typeOfList = sc.nextLine();

            // deciding whether to use arraylist or linked list or just invalid list type
            if(typeOfList.equals("ArrayList")) {
                tempList = new ArrayList<>();
            } else if (typeOfList.equals("LinkedList")) {
                tempList = new LinkedList<>();
            } else {
                System.out.println("Invalid list type.");
            }

            // storing the scale
            tempScale = Double.parseDouble(sc.nextLine());
            // removing the commas in the text lines
            sc.useDelimiter(",");
            // adding all the values into the list
            while (sc.hasNext()) {
                // making sure the list isn't null
                assert tempList != null;
                // calls NBody function to add each value into the array
                tempList.add(new NBody(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.nextLine()));
            }
            // close scanner
            sc.close();
        } catch (FileNotFoundException e) {
            // file not found exception
            System.out.println("File not found.");
        }

        // creating object for NBody constructor, using the temp list and temp scale
        NBody nBody = new NBody(tempList, tempScale);
        // object for jframe
        JFrame jf = new JFrame();
        // title of jframe
        jf.setTitle("N-Body Problem");
        // window size
        jf.setSize(768, 768);
        // putting nBody object into jframe
        jf.add(nBody);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // NBody constructor that sets each variable according to the parameters
    public NBody(String name, String mass, String x_coord, String y_coord, String x_velo, String y_velo, String size) {
        this.name = name;
        this.mass = Double.parseDouble(mass);
        this.xCoord = Integer.parseInt(x_coord);
        this.yCoord = Integer.parseInt(y_coord);
        this.xVelo = Double.parseDouble(x_velo);
        this.yVelo = Double.parseDouble(y_velo);
        this.size = size.substring(1);

        // random object for colors
        Random r = new Random();
        // random color for each nBody
        color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }

    // get method to return the mass
    public double getMass() {
        return mass;
    }

    // get x-coordinate to return the x-coord
    public int getxCoord() {
        return xCoord;
    }

    // get y-coordinate to return the y-coord
    public int getyCoord() {
        return yCoord;
    }

    // get x-velocity to return the x-velo
    public double getxVelo() {
        return xVelo;
    }

    // get y-velocity to return the y-velo
    public double getyVelo() {
        return yVelo;
    }

    // returning the size
    public int Size() {
        return Integer.parseInt(size);
    }

    // get method to return the color
    public Color getColor() {
        return color;
    }

    // force function to calculate the force
    public void force(NBody a, double scale) {
        // setting to current
        NBody b = this;
        // storing coordinates
        double x = a.xCoord - b.xCoord;
        double y = a.yCoord - b.yCoord;
        // calculating the distance
        double distance = Math.sqrt(x * x + y * y);
        // storing force
        double force = (6.673e-11 * b.mass * a.mass / ((distance * distance) / scale));
        // calculating the x force and y force
        b.xForce += force * x / distance;
        b.yForce += force * y / distance;
    }

    // resetting force function
    public void resetForce() {
        xForce = 0;
        yForce = 0;
    }

    // updating the position of the nBodies
    public void updatePos() {
        // updating x and y velocities
        xVelo += xForce/mass;
        yVelo += yForce/mass;
        // updating x and y coordinates
        xCoord += xVelo;
        yCoord += yVelo;
    }

    // creating a new list
    private Lists<NBody> list;
    // creating scale
    private double scale;

    // setting variables
    public NBody(Lists<NBody> newList, double newScale) {
        list = newList;
        scale = newScale;
    }

    // function to "paint" the animation
    public void paintComponent(Graphics g) {
        // calls super.paint
        super.paintComponent(g);
        // sets timer
        Timer time = new Timer(400, this);
        // looping to create bodies
        for (int i = 0; i < list.size(); i++) {
            // setting random color
            g.setColor(list.get(i).getColor());
            // creating the nbodies into circles
            g.fillOval(list.get(i).getxCoord(), list.get(i).getyCoord(), list.get(i).Size(), list.get(i).Size());
        }
        // starts timer
        time.start();
    }

    // action performed updates the function and repaints for the animation
    public void actionPerformed(ActionEvent e) {
        updateShapes();
        repaint();
    }

    // updating the shapes function
    public void updateShapes() {
        int i;
        // loops through list
        for (i = 0; i < list.size() - 1; i++) {
            // calculate the force
            list.get(i).force(list.get(i + 1), scale);
            // update position
            list.get(i).updatePos();
            // reset the force
            list.get(i).resetForce();
        }
        // as long as there's more than one value in the list
        if(list.size() > 1) {
            // calculate force
            list.get(i).force(list.get(i-1), scale);
            // update position
            list.get(i).updatePos();
            // reset force
            list.get(i).resetForce();
        }
    }
}
