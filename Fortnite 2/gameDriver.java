import javax.swing.*;
import java.awt.*;
public class gameDriver
{
    public static void main( String[]args )
    {
     JFrame frame = new JFrame( "Fortnite 2" );
     frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
     frame.setLocation( 0, 0 );
     Game game = new Game();
     frame.getContentPane().add( game );
     frame.pack();
     frame.setVisible( true );
     game.go();
    }
}
