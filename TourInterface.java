import java.awt.Graphics;

public interface TourInterface
{
    public int size();
    public double distance();
    
    public void add(Point p);
    public void insertNearest(Point p);
    public void insertSmallest(Point p);
    
    public void print();
    public void draw(Graphics g);
}
