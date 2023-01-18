import java.awt.*;
public class Enemy
{
    private static int x;
    private static int y; 
    private int size;
    private Color color;
    public static boolean left, right, up, down;
    private int xInc = (int)(Math.random() * 2); 
    private int yInc = (int)(Math.random() * 2);
    

    public Enemy()
    {
        x = (int)(Math.random()*Game.WIDTH - 50) + 50;
        y = (int)(Math.random()*Game.HEIGHT - 50) + 50;
        size = 50;
 
        color = new Color( (int)(Math.random()*256),(int)(Math.random()*256), (int)(Math.random()*256) );
    }

    public void randomPosition()
    {
        x = (int)(Math.random()*Game.WIDTH);
        y = (int)(Math.random()*Game.HEIGHT);
    }

    public void draw( Graphics page )
    {
        page.setColor( color );
        page.fillOval( x, y, size, size);
    }

    public void changeColor()
    {
        color = new Color( (int)(Math.random()*256),(int)(Math.random()*256), (int)(Math.random()*256) );
    }

    public void changeSize()
    {
        size = (int)(Math.random()*51+50);
    }

    public void move()
    {
        x = x + xInc;
        if( x <= 0 || x >= Game.WIDTH - size )
        {
            xInc *= -1;
        }
        y = y + yInc;
        if( y <= 0 || y >= Game.HEIGHT - size )
        {
            yInc *= -1;
        }
    }
   
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
    
    public static void setLeft( boolean t )
    {
        left = t;
    }

    public static  void setRight( boolean t )
    {
        right = t;
    }

    public static void setUp( boolean t )
    {
        up = t;
    }

    public static void setDown( boolean t )
    {
        down = t;
    }

    public int getXLocation()
     { return x; }

    public int getYLocation()
    { return y; }

    public boolean getRight()
    { return right; }

    public boolean getLeft()
    { return left; }

    public boolean getUp()
    { return up; }

    public boolean getDown()
    { return down; }
}

