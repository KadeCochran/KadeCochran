import java.awt.*;
public class GoldScar
{
    private static int x = 500;
    private static int y = 500; 
    private int size;
    private Color color;
    public static boolean left, right, up, down;

    public GoldScar(int ex, int why)
    {
        x = ex; 
        y = why;
        size = 25;
        color = new Color(204, 153, 0);
    } 

    public GoldScar()
    {
        x = 1500; 
        y = 1500;
        size = 25;
        color = new Color(204, 153, 0);
    } 
    
    public void draw( Graphics page )
    {
        page.setColor( color );
        page.fillRect( x, y, size * 2, size);
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
        return x + size + size - 5;
    }

    public int getBottomSide()
    {
        return y + size - 5;
    }
}

