import java.awt.*;
public class enemyShoot
{
    private static int x;
    private static int y;
    private static int size;
    private static Color color;
    public static boolean left, right, up, down;
    public static boolean fastLeft, fastRight, fastUp, fastDown;

    
    public enemyShoot()
    {
        x = 1500; 
        y = 1500;
        size = 25;
        color = Color.GRAY;
    }

    public enemyShoot(int X, int Y)
    {
        x = X; 
        y = Y;
        size = 25;
        color = Color.GRAY;
    }
    
    public static void randomPosition()
    {
        x = (int)(Math.random()*Game.WIDTH);
        y = (int)(Math.random()*Game.HEIGHT);
    }

    public static void draw( Graphics page )
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
        size = (int)(Math.random()*11+10);
    }

    public static void move()

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
        if( fastRight == true)
        {
            x += 3;
        }
        else if( fastLeft == true)
        {
            x -= 3;
        }
        if( fastUp == true)
        {
            y -= 3;
        }
        else if( fastDown == true)
        {
            y += 3;
        }
    }

   
    public static void setLeft( boolean t)
    {
        left = t;
    }

    public static void setRight( boolean t)
    {
        right = t;
    }

    public static void setUp( boolean t)
    {
        up = t;
    }

    public static void setDown( boolean t)
    {
        down = t;
    }

    public static void setFastLeft( boolean t)
    {
        fastLeft = t;
    }

    public static void setFastRight( boolean t)
    {
        fastRight = t;
    }

    public static void setFastUp( boolean t)
    {
        fastUp = t;
    }

    public static void setFastDown( boolean t)
    {
        fastDown = t;
    }

   
    public int getX()
    {
         return x;
    }

    public int getY()
    {
        return y;
    }
    
    public static int getSize()
    { return size; }
       
    public static int getRightSide()
    {
        return x + size - 5;
    }

    public static int getBottomSide()
    {
        return y + size - 5;
    }
}


