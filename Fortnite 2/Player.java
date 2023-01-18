import java.awt.*;
public class Player
{
    private static int x = 500;
    private static int y = 500; 
    private int size;
    private Color color;
    public static boolean left, right, up, down;

    
    public Player()
    {
        x = 500; 
        y = 500;
        size = 50;
        color = Color.YELLOW;
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
        if(right == true)
        {
            x += 1;
        }
        else if( left == true)
        {
            x -= 1;
        }
        if( up == true)
        {
            y -= 1;
        }
        else if( down == true)
        {
            y += 1;
        }
        if( x <= 0 )
        {
            x += Game.WIDTH;
        }
        if( x >= Game.WIDTH - size )
        {
            x -= Game.WIDTH;
        }
        if( y <= 0 )
        {
           y += Game.HEIGHT; 
        }
        if( y >= Game.HEIGHT - size )
        {
            y -= Game.HEIGHT;
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
        return x + size - 10;
    }

    public int getBottomSide()
    {
        return y + size - 10;
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
