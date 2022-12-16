import java.util.*;
import java.awt.Graphics;

/**
 * This class is a specialized Linked List of Points that represents a
 * Tour of locations attempting to solve the Traveling Salesperson Problem
 * 
 * @author
 * @version
 */

public class Tour implements TourInterface
{
    // instance variables
    
    //represent the head and tail of the linked list
    private ListNode listHead;
    private ListNode listTail;
    
    //represents the size/length of the list
    private int listSize;
    
    //represents how far the next city is
    private double distanceTraveled;
    
    // constructor
    public Tour()
    {
        //set both fisr and last nodes so they are empty
        this.listHead = null;
        this.listTail = null;
        //set the size of the list to 0
        this.listSize = 0;
        //set the distance travelled to 0 (when first used the salesperson hasnt travelled anywhere yet)
        this.distanceTraveled = 0;
    }
        
    //return the number of points (nodes) in the list   
    public int size()
    {
        //returns the length/size/how many points are in the list
        return listSize;
    }

    // append Point p to the end of the list
    
    // in order to do this, this class checks to see if there are any points yet. If there are none it sets the front to the new point, 
    // and if it doesnt it adds a new point to the end before setting tail to the new 'last' point. finally it updates the total size of the list
    public void add(Point p)
    {
        ListNode tail = new ListNode(p);
        if(listHead == null){
            listHead = tail;
        }
        else{
            listTail.next = tail;
        }
        listTail = tail;
        listSize++;
    } 
    
    // print every node in the list 
    
    //to do this we simply use a while loop to determine whther or not a node is empty. as long as it isnt empty we will iterate through the 
    //list until we get to an empty node, indicating there are no more points
    public void print()
    {   
        ListNode node = listHead;
        while(node != null){
            System.out.println(node.data);
            node = node.next;
        }
    }
    
    // draw the tour using the given graphics context
    
    // to do this, we create a node that will traverse through the list, and then set up a while loop to iterate through the list until it
    // hits an empty node (much like the last method). it then checks if there is a point, and if not it takes the x and y coordinates from the 
    // front point. When there is a point it takes the x and the y variable, of the front point and the new point, and draw a line between them.
    // for a more cosmetically pleasing look i added a try-catch statement to add a short delay between drawing points so you can see the program
    // working its magic.
    public void draw(Graphics g)
    {
        ListNode node = listHead;
        int nextX = 0;
        int nextY = 0;
        while(node != null){
            int x = (int)node.data.getX();
            int y = (int)node.data.getY();
            g.fillOval(x - 2, y - 2, 5, 5);
            if(node.next == null){
                nextX = (int)listHead.data.getX();
                nextY = (int)listHead.data.getY();
            }
            else{
                nextX = (int)node.next.data.getX();
                nextY = (int)node.next.data.getY();
            }
            g.drawLine(x, y, nextX, nextY);
            node = node.next;
        }
    }
    
    //calculate the distance of the Tour, but summing up the distance between adjacent points
    //NOTE p.distance(p2) gives the distance where p and p2 are of type Point
    
    //again, this method takes the same approach as the previous two, by making making a node to traverse the list, and then will iterate
    //through the list until it hits an empty node indicating the end of the list. while iterating it, it will grab the x and y points of
    //both the current and next points and then using the distance formula to make the calculation. As the iteration reaches the end of the
    //list, the last node connects up to the first node again, cmopoleting a full circuit.
    public double distance()
    {
        ListNode node = listHead;
        double dist = 0;
        double nextX = 0.0;
        double nextY = 0.0; 
        while(node != null){
            double x = node.data.getX();
            double y = node.data.getY();
            if(node.next != null){
                nextX = node.next.data.getX();
                nextY = node.next.data.getY();
            }
            else{
                nextX = listHead.data.getX();
                nextY = listHead.data.getY();
            }
            dist += (double) Math.sqrt(Math.pow((nextX - x),2) + Math.pow((nextY - y), 2));
            node = node.next;
        }
        return dist;
    }

    // add Point p to the list according to the NearestNeighbor heuristic
    
    //to do this, we once again make a node to traverse the the list before checking whether or not there is a point. If there isnt we simply
    //add the given point. we then take the taversing node and set it equal to the first node in the list. We then move on to the list iteration
    //where we set the distance variable to the distance between two points the method is dealing with in its current state (the current and 
    //the possible next point), before checking whether or not the distance is greater or less than the current minimum distance. Should it be 
    //be less than the minimum distance, that distance becomes the new minimum. the temporary node called at the begining is then called to save 
    //the current node with the shortest distance. whether or not the if statement is executed doesnt affect the program from moving to the next
    //node, as only the shortest node is saved and therefore if it isnt the shortest there is no point in saving it. once the iteration is 
    //finished, the traversal node (node) is set to the node after the shortest distance node (temp). That temp node is then set as a new 
    //ListNode from the private class, and then the node after that is set to the traversal node (node).
    public void insertNearest(Point p)
    {
        
        double dist;
        double min=Integer.MAX_VALUE; 
        ListNode node = listHead; 
        ListNode temp = listHead; 
        if(node == null){
            add(p); 
            listSize--;
        }
        node = listHead; 
        while(node != null){ 
            dist = p.distance(node.data); 
            if(min > dist){
                min=dist;
                temp=node;
                //listSize++;
            }
            node = node.next;
            //listSize++;2 
        }
        node=temp.next;
        temp.next = new ListNode(p);
        temp.next.next=node;
        listSize++;
    }
        
    // add Point p to the list according to the InsertSmallest heuristic
    
    //first step here is to make sure we have at least 3 points added, and wherever there isnt one we add a new one.We then take a node and look
    //at the distance between it and the point (One), the next node and the point (Two), and finally the current and next nodes (Three). We then
    //Start our iterations and use the tempMin variable to look at the difference between this point and a new before using comparing temopMin 
    //to the minimum distance (either the current minimum or -1 in the first iteration). we then check another 3 distances: node to the head of
    //the list (One) , the node to the point (Two), and the poiunt to the head of the list(Three). We use the same three variables not because i
    //was lazy and didnt want to write 3 new variables at the time, but because we dont want to waste space. we make the same calculation as
    //earlier, reusing tempMin and minDist but this time if the new value of temp min is lower we'll set the node to the tail, while if it isnt, 
    //we move to the next node and check if that is the best point (aka if it has the shortest distance).
    
    //Edit: thanks to advice from other students, i have changed the process so that the One, Two, and Three variables arent stored and instead 
    //      make the calculation based soley on the values of the variables without assigning them. This saves storage so woo-hoo
    
    public void insertSmallest(Point p)
    { 
        //create variables 'n objects
        double minDist = -1;
        double fbDistMin = Double.POSITIVE_INFINITY; 
        double tempMin;
        ListNode node = listHead;
        ListNode point = new ListNode(p);
        ListNode best = node; 
        if (node == null){ 
            add(p); 
        }
        else if (node.next == null){ 
            add(p);
        } 
        else if (node.next.next == null){ 
            add(p);
        } 
        else {
            while(node.next != null){ 
                tempMin = p.distance(node.data) + p.distance(node.next.data) - node.data.distance(node.next.data);                 
                if(tempMin < minDist  || minDist < 0) { 
                    minDist = tempMin; 
                    best = node; 
                }
                node = node.next; 
            }
            tempMin = point.data.distance(listHead.data) + node.data.distance(point.data) - node.data.distance(listHead.data);        
            if(tempMin < minDist){
                node.next = point; 
            }
            else{
                point.next = best.next; 
                best.next = point; 
            }
            listSize++;
        }
    }
    
    // This is a private inner class, which is a separate class within a class.
    private class ListNode
    {
        private Point data;
        private ListNode next;
        public ListNode(Point p, ListNode n)
        {
            this.data = p;
            this.next = n;
        }
        
        public ListNode(Point p)
        {
            this(p, null);
        }        
    }
    
    
  

}