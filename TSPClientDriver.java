import java.io.*;
import java.util.*;
import java.awt.*;

public class TSPClientDriver  
{
    private static final String file = "tsp1000.txt";
    private static Scanner input;
    private static Calendar calendar;
    private static Tour tour;
    private static int w, h;
    private static double elapsedTime;
    private static long start, stop;

    public static void main(String[] args) throws FileNotFoundException
    {   
        // ******* test add method ******

        // Make a new Tour
        tour = new Tour();

        //Open file for reading
        input = new Scanner(new File("Data Files",file));

        w = input.nextInt(); //first two lines are width/height maxes
        h = input.nextInt(); //used for graphics scaling

        // get start time
        start = calendar.getInstance().getTimeInMillis();

        // add points to the tour from the file.
        while(input.hasNext())
        {
            tour.add(new Point(input.nextDouble(), input.nextDouble()));
        }

        // get stop time
        stop = calendar.getInstance().getTimeInMillis();
        // calculate elapsed time
        elapsedTime = (double) (stop - start) / 1000;

        if(tour.size() == 5)    // only for initial debugging purposes
        {
            //Print stuff out about it:
            System.out.println("Created Tour with " + tour.size() + " points.");
            System.out.println("Tour has distance " + tour.distance());
            System.out.println("Here is the tour in order of points visited");
            tour.print();
        }

        graphPoints("Points as given");


        // ******* test insertNearest method ******
        // Make a new Tour
        tour = new Tour();

        //Open file for reading
        input = new Scanner(new File("Data Files",file));

        w = input.nextInt(); //first two lines are width/height maxes
        h = input.nextInt(); //used for graphics scaling

        // get start time
        start = calendar.getInstance().getTimeInMillis();

        // add points to the tour from the file.
        while(input.hasNext())
        {
            tour.insertNearest(new Point(input.nextDouble(), input.nextDouble()));
        }

        // get stop time
        stop = calendar.getInstance().getTimeInMillis();
        // calculate elapsed time
        elapsedTime = (double) (stop - start) / 1000;
        graphPoints("Nearest neighbor heuristic");

        // ******* test insertSmallest method ******

        // Make a new Tour
        tour = new Tour();

        //Open file for reading
        input = new Scanner(new File("Data Files",file));

        w = input.nextInt(); //first two lines are width/height maxes
        h = input.nextInt(); //used for graphics scaling

        // get start time
        start = calendar.getInstance().getTimeInMillis();

        // add points to the tour from the file.
        while(input.hasNext())
        {
            tour.insertSmallest(new Point(input.nextDouble(), input.nextDouble()));
        }

        // get stop time
        stop = calendar.getInstance().getTimeInMillis();
        // calculate elapsed time
        elapsedTime = (double) (stop - start) / 1000;
        graphPoints("Smallest increase heuristic");

    }        

    private static void graphPoints(String method)
    {        
        // graph the points
        DrawingPanel panel = new DrawingPanel(w,h);
        Graphics g = panel.getGraphics();
        tour.draw(g);
        g.setColor(Color.RED);
        g.drawString(String.format("Tour with %,d",tour.size()) + " points", 10, 20);
        g.drawString(String.format("%s", method), 10, 40);
        g.drawString(String.format("Tour distance: %,.2f",tour.distance()), 10, 60);
        g.drawString(String.format("Elapsed time: %4.2f seconds", elapsedTime), 10,80);
    }
}