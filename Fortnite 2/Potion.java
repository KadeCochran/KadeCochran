import java.awt.*;
public class Potion
{
    private static int x = 500;
    private static int y = 500; 
    private int size;
    private Color color;
    public static boolean left, right, up, down;

    public Potion(int ex, int why)
    {
        x = ex; 
        y = why;
        size = 25;
        color = new Color(66, 200, 245);
    } 

    public Potion()
    {
        x = 1500; 
        y = 1500;
        size = 30;
        color = new Color(66, 200, 245);
    } 
    
    public void draw( Graphics page )
    {
        page.setColor( color );
        page.fillOval( x, y, size, size);
    }
    
    public int getXLocation()
    { return x; }

    public int getYLocation()
    { return y; }
    
       public int getX()
    {
        return x + 5;
    }

    public int getY()
    {
        return y + 5;
    }

    public int getRightSide()
    {
        return x + size - 5;
    }

    public int getBottomSide()
    {
        return y + size - 5;
    }
}
