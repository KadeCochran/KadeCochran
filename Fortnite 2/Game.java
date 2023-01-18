import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Game extends JPanel implements KeyListener
{
    private Player player;
    private Shoot shoot;
    private Enemy enemy;
    private enemyShoot badShoot;
    private GoldScar goldScar;
    private Potion shieldPotion;
    private static int hits = 1;
    public static final int WIDTH = 1300;
    public static final int HEIGHT = 700;
    private int d = 0;
    private int s = 0;
    public static boolean gameOver = false;
    private static int tick = 0;
    public static int kills = 0;
    private JLabel Kills, end, goldScarLabel, shieldPotionLabel;
    private static boolean haveGoldScar = false;
    private static boolean haveShieldPotion = false;
    public Color GOLD = new Color (204, 153, 0);
    public Color LIGHTBLUE = new Color (66, 200, 245);
    
    public Game()
    { 
        player = new Player();
        enemy = new Enemy();
        goldScar = new GoldScar();
        shieldPotion = new Potion();
        
        setLayout( null );
        setBackground( Color.GREEN );
        setPreferredSize( new Dimension( WIDTH, HEIGHT ) );

        Kills = new JLabel( "Kills: " + kills );
        Kills.setFont( new Font( "Arial", Font.BOLD, 30 ) );
        Kills.setBounds( 0, 10, 500, 40 );
        Kills.setHorizontalAlignment( SwingConstants.CENTER );
        Kills.setVisible( true );
        Kills.setForeground( Color.BLACK );
        this.add( Kills );
         
        goldScarLabel = new JLabel();
        goldScarLabel.setFont( new Font( "Arial", Font.BOLD, 30 ) );
        goldScarLabel.setBounds( 0, 10, 800, 40 );
        goldScarLabel.setHorizontalAlignment( SwingConstants.CENTER );
        goldScarLabel.setVisible( true );
        goldScarLabel.setForeground( GOLD );
        this.add( goldScarLabel );
        
        shieldPotionLabel = new JLabel();
        shieldPotionLabel.setFont( new Font( "Arial", Font.BOLD, 30 ) );
        shieldPotionLabel.setBounds( 0, 10, 1200, 40 );
        shieldPotionLabel.setHorizontalAlignment( SwingConstants.CENTER );
        shieldPotionLabel.setVisible( true );
        shieldPotionLabel.setForeground( LIGHTBLUE );
        this.add( shieldPotionLabel );
        
        
        end = new JLabel();
        end.setFont( new Font( "Arial", Font.BOLD, 75 ) );
        end.setBounds( 0, 300, 1000, 100 );
        end.setHorizontalAlignment( SwingConstants.CENTER );
        end.setVerticalAlignment( SwingConstants.CENTER );
        end.setVisible( true );
        this.add( end );

        addKeyListener( this );
        setFocusable( true );
    }

    public void go()
    {
        while(!gameOver)
        {
            player.move();
            Shoot.move();
            enemy.move();
            enemyShoot.move();
            enemyAI();
            if( goldScar != null && collect( goldScar ) )
            {
                haveGoldScar = true;
                goldScarLabel.setText( "Gold Scar" );
                goldScar = new GoldScar();
            }
            if( shieldPotion != null && collect( shieldPotion ) )
            {
               hits ++;
               haveShieldPotion = true;
               shieldPotionLabel.setText( "Shield Potion" );
               shieldPotion = new Potion();
            }
            if( badShoot != null && shot( player ) )
            {
                if( hits < 2)
                {
                System.out.println ( "You were killed by ninja" );
                end.setText( "You were killed by Ninja" );
                gameOver = true;
                }
                else
                {
                 badShoot = new enemyShoot();
                 hits--;
                 haveShieldPotion = false;
                 shieldPotionLabel.setText( "" );
                }
            }
            if( kills == 10)
            {
                System.out.println ( " #1 Victory Royale!" );
                end.setText( " #1 Victory Royale!" );
                gameOver = true;

            }
            try
            {
                Thread.sleep(2);  
            }
            catch(InterruptedException ex){}
            tick ++;

            repaint();
        }
    }

    public void enemyAI()
    {
        if( shoot != null && hit( enemy ) )
        {
            int Spawn = (int)(Math.random() *10) + 1;
            if( Spawn == 5 && !haveGoldScar)
              {
              goldScar = new GoldScar( enemy.getX(), enemy.getY());  
              }
            if( Spawn == 3 || Spawn == 4 && !haveShieldPotion)
              {
              shieldPotion = new Potion( enemy.getX(), enemy.getY());  
              }
            if( Spawn == 9 && !haveGoldScar && !haveShieldPotion)
             {
              goldScar = new GoldScar( enemy.getX(), enemy.getY());  
              shieldPotion = new Potion( enemy.getX(), enemy.getY());
             }
            enemy = new Enemy();
            kills ++;
            System.out.println( kills );
            Kills.setText( "Kills: " + kills );
        }
        while ( tick > 500 )
        {
            if(player.getYLocation() < enemy.getYLocation())
            {
                if( !badShoot.fastUp )
                {

                    badShoot.setFastDown(false);
                    badShoot = new enemyShoot(enemy.getXLocation() + 12, enemy.getYLocation()+ 12);
                    badShoot.setFastUp(true);
                }

                else if(  badShoot.getX() <= 0 || badShoot.getX() >= Game.WIDTH - badShoot.getSize() || badShoot.getY() <= 0 ||  badShoot.getY() >= Game.HEIGHT - badShoot.getSize())
                {
                    badShoot.setFastUp(false);
                }
                tick = 0;
            }
            else if(player.getYLocation() > enemy.getYLocation())
            {
                if( !badShoot.fastDown )
                {

                    badShoot.setFastUp(false);
                    badShoot = new enemyShoot(enemy.getXLocation() + 12, enemy.getYLocation()+ 12);
                    badShoot.setFastDown(true);
                }

                else if(  badShoot.getX() <= 0 || badShoot.getX() >= Game.WIDTH - badShoot.getSize() || badShoot.getY() <= 0 ||  badShoot.getY() >= Game.HEIGHT - badShoot.getSize())
                {
                    badShoot.setFastDown(false);

                }
                tick = 0;
            }
            if(player.getXLocation() < enemy.getXLocation())
            {
                if( !badShoot.fastLeft )
                {
                    badShoot.setFastUp(false);
                    badShoot.setFastDown(false);
                    badShoot.setFastRight(false);
                    badShoot = new enemyShoot(enemy.getXLocation() + 12, enemy.getYLocation()+ 12);
                    badShoot.setFastLeft(true);
                }

                else if(  badShoot.getX() <= 0 || badShoot.getX() >= Game.WIDTH - badShoot.getSize() || badShoot.getY() <= 0 ||  badShoot.getY() >= Game.HEIGHT - badShoot.getSize())
                {
                    badShoot.setFastLeft(false);
                }
                tick = 0;
            }
            else if(player.getXLocation() > enemy.getXLocation())
            {
                if( !badShoot.fastRight )
                {
                    badShoot.setFastUp(false);
                    badShoot.setFastDown(false);
                    badShoot.setFastLeft(false);
                    badShoot = new enemyShoot(enemy.getXLocation() + 12, enemy.getYLocation()+ 12);
                    badShoot.setFastRight(true);
                }

                else if(  badShoot.getX() <= 0 || badShoot.getX() >= Game.WIDTH - badShoot.getSize() || badShoot.getY() <= 0 ||  badShoot.getY() >= Game.HEIGHT - badShoot.getSize())
                {
                    badShoot.setFastRight(false);
                }
                tick = 0;
            }
        }
    }

    public boolean hit(Enemy enemy)
    {
        if( shoot.getX() < enemy.getRightSide() && shoot.getRightSide() > enemy.getX() && shoot.getY() < enemy.getBottomSide() && shoot.getBottomSide() > enemy.getY())
        {
            return true;
        }
        return false;
    }

    public boolean shot(Player player)
    {
        if( badShoot.getX() < player.getRightSide() && badShoot.getRightSide() > player.getX() && badShoot.getY() < player.getBottomSide() && badShoot.getBottomSide() > player.getY())
        {
            return true;
        }
        return false;
    }

    public boolean collect( GoldScar goldScar )
    {
        if( player.getX() < goldScar.getRightSide() && player.getRightSide() > goldScar.getX() && player.getY() < goldScar.getBottomSide() && player.getBottomSide() > goldScar.getY() )
        {
            return true;
        }
        return false;
    }

    public boolean collect( Potion shieldPotion )
    {
        if( player.getX() < shieldPotion.getRightSide() && player.getRightSide() > shieldPotion.getX() && player.getY() < shieldPotion.getBottomSide() && player.getBottomSide() > shieldPotion.getY() )
        {
            return true;
        }
        return false;
    }
    
    public void paintComponent( Graphics page )
    {
        super.paintComponent( page );
        player.draw( page );
        shoot.draw( page );
        enemy.draw( page );
        badShoot.draw( page );
        goldScar.draw( page );
        shieldPotion.draw( page );
    }

    public void keyReleased( KeyEvent event)
    {
        if( event.getKeyCode() == KeyEvent.VK_P )
        {
            player.randomPosition();
        }    
        else if( event.getKeyCode() == KeyEvent.VK_C )
        {
            player.changeColor();
            shoot.changeColor();
        }
        else if( event.getKeyCode() == KeyEvent.VK_B )
        {
            setBackground( new Color( (int)(Math.random()*256),(int)(Math.random()*256), (int)(Math.random()*256) ) );
        }
        else if( event.getKeyCode() == KeyEvent.VK_L )
        {
            player.changeSize();
        }
        else if( event.getKeyCode() == KeyEvent.VK_RIGHT )
        {
            player.setRight(false);
            d = 1;
        }
        else if( event.getKeyCode() == KeyEvent.VK_LEFT )
        {
            player.setLeft(false);
            d = 2;
        } 
        else if( event.getKeyCode() == KeyEvent.VK_UP )
        {
            player.setUp(false);
            d = 3;
        }
        else if( event.getKeyCode() == KeyEvent.VK_DOWN )
        {
            player.setDown(false);
            d = 4;
        }
        else if( event.getKeyCode() == KeyEvent.VK_SPACE  )
        {
            shoot = new Shoot(player.getXLocation() +12 ,player.getYLocation() +12 );
            if( s == 0 )
            {
                s = 1;
                Shoot.setFastRight( true );
                Shoot.setFastLeft( false );
                Shoot.setFastUp( false );
                Shoot.setFastDown( false );
            }
            else
            {
                if( player.getRight() )
                {
                    if( haveGoldScar )
                    {
                        Shoot.setFastRight( false );
                        Shoot.setFastLeft( false );
                        Shoot.setFastUp( false );
                        Shoot.setFastDown( false );
                        Shoot.setGoldRight( true );
                        Shoot.setGoldLeft( false );
                        Shoot.setGoldUp( false );
                        Shoot.setGoldDown( false );
                    }
                    else
                    {
                        Shoot.setFastRight( true );
                        Shoot.setFastLeft( false );
                        Shoot.setFastUp( false );
                        Shoot.setFastDown( false );   
                    }
                }
                else if( player.getLeft() )
                {
                    if( haveGoldScar )
                    {
                        Shoot.setFastRight( false );
                        Shoot.setFastLeft( false );
                        Shoot.setFastUp( false );
                        Shoot.setFastDown( false );
                        Shoot.setGoldRight( false );
                        Shoot.setGoldLeft( true );
                        Shoot.setGoldUp( false );
                        Shoot.setGoldDown( false );
                    }
                    else
                    {
                        Shoot.setFastRight( false );
                        Shoot.setFastLeft( true );
                        Shoot.setFastUp( false );
                        Shoot.setFastDown( false );   
                    }
                }
                else if( player.getUp() )
                {
                    if( haveGoldScar )
                    {
                        Shoot.setFastRight( false );
                        Shoot.setFastLeft( false );
                        Shoot.setFastUp( false );
                        Shoot.setFastDown( false );
                        Shoot.setGoldRight( false );
                        Shoot.setGoldLeft( false );
                        Shoot.setGoldUp( true );
                        Shoot.setGoldDown( false );
                    }
                    else
                    {
                        Shoot.setFastRight( false );
                        Shoot.setFastLeft( false );
                        Shoot.setFastUp( true );
                        Shoot.setFastDown( false );   
                    }
                }else if( player.getDown() )
                {
                    if( haveGoldScar )
                    {
                        Shoot.setFastRight( false );
                        Shoot.setFastLeft( false );
                        Shoot.setFastUp( false );
                        Shoot.setFastDown( false );
                        Shoot.setGoldRight( false );
                        Shoot.setGoldLeft( false );
                        Shoot.setGoldUp( false );
                        Shoot.setGoldDown( true );
                    }
                    else
                    {
                        Shoot.setFastRight( false );
                        Shoot.setFastLeft( false );
                        Shoot.setFastUp( false );
                        Shoot.setFastDown( true );   
                    }
                }else 
                {
                    if ( d == 1 )
                    {
                        if( haveGoldScar )
                        {
                            Shoot.setFastRight( false );
                            Shoot.setFastLeft( false );
                            Shoot.setFastUp( false );
                            Shoot.setFastDown( false );
                            Shoot.setGoldRight( true );
                            Shoot.setGoldLeft( false );
                            Shoot.setGoldUp( false );
                            Shoot.setGoldDown( false );
                        }
                        else
                        {
                            Shoot.setFastRight( true );
                            Shoot.setFastLeft( false );
                            Shoot.setFastUp( false );
                            Shoot.setFastDown( false );   
                        }
                    } 
                    else if ( d == 2 )
                    {
                        if( haveGoldScar )
                        {
                            Shoot.setFastRight( false );
                            Shoot.setFastLeft( false );
                            Shoot.setFastUp( false );
                            Shoot.setFastDown( false );
                            Shoot.setGoldRight( false );
                            Shoot.setGoldLeft( true );
                            Shoot.setGoldUp( false );
                            Shoot.setGoldDown( false );
                        }
                        else
                        {
                            Shoot.setFastRight( false );
                            Shoot.setFastLeft( true );
                            Shoot.setFastUp( false );
                            Shoot.setFastDown( false );   
                        }
                    }
                    else if ( d == 3 )
                    {
                        if( haveGoldScar )
                        {
                            Shoot.setFastRight( false );
                            Shoot.setFastLeft( false );
                            Shoot.setFastUp( false );
                            Shoot.setFastDown( false );
                            Shoot.setGoldRight( false );
                            Shoot.setGoldLeft( false );
                            Shoot.setGoldUp( true );
                            Shoot.setGoldDown( false );
                        }
                        else
                        {
                            Shoot.setFastRight( false );
                            Shoot.setFastLeft( false );
                            Shoot.setFastUp( true );
                            Shoot.setFastDown( false );   
                        }
                    }
                    else if ( d == 4 )
                    {
                        if( haveGoldScar )
                        {
                            Shoot.setFastRight( false );
                            Shoot.setFastLeft( false );
                            Shoot.setFastUp( false );
                            Shoot.setFastDown( false );
                            Shoot.setGoldRight( false );
                            Shoot.setGoldLeft( false );
                            Shoot.setGoldUp( false );
                            Shoot.setGoldDown( true );
                        }
                        else
                        {
                            Shoot.setFastRight( false );
                            Shoot.setFastLeft( false );
                            Shoot.setFastUp( false );
                            Shoot.setFastDown( true );   
                        }
                    }
                }
            }
        }
        this.repaint();

    }

    public void keyPressed( KeyEvent event)
    {
        if( event.getKeyCode() == KeyEvent.VK_RIGHT )
        {
            player.setRight(true);
            player.setLeft(false);

        }
        else if( event.getKeyCode() == KeyEvent.VK_LEFT )
        {
            player.setLeft(true);
            player.setRight(false);

        } 
        else if( event.getKeyCode() == KeyEvent.VK_UP )
        {
            player.setUp(true);
            player.setDown(false);

        }
        else if( event.getKeyCode() == KeyEvent.VK_DOWN )
        {
            player.setDown(true);
            player.setUp(false);

        }
        else if( event.getKeyCode() == KeyEvent.VK_Q )
        {
            setBackground( new Color( (int)(Math.random()*256),(int)(Math.random()*256), (int)(Math.random()*256) ) );
            player.changeSize();
            player.changeColor();
            player.randomPosition();
            this.repaint();
        }

        this.repaint();

    }

    public void keyTyped( KeyEvent event)
    {}

}

