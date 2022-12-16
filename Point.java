import java.awt.geom.Point2D;

/**
 * A simple facade for the class Point2D.Double - a class that represents
 * a point in 2D space with Double precision.
 * This is only here to save you some typing so that you can construct points like this:
 * 
 * Point p = new Point(x,y);
 * rather than:
 * Point2D.Double p = new Point2D.Double(x,y);
 * 
 * You should look up the Point2D.Double documentation though to see what methods this inherits..
 * Of particular interest to you will be the method:
 * 
 * double distance(Point2D)
 * 
 * which returns the distance between this Point and the one provided to the method.
 * 
 * the toString method has been overridden so it prints something reasonable.
 * The default Point2D.Double toString method prints too much stuff.
 * 
 */ 

public class Point extends Point2D.Double
{

    public Point(double x, double y)
    {
        super(x,y);
    }
    
    public String toString()
    {
        return "(" + getX() + "," + getY() +")";
    }

}