/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankrotationexample;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


import static javax.imageio.ImageIO.read;

/**
 * Main driver class of Tank Example.
 * Class is responsible for loading resources and
 * initializing game objects. Once completed, control will
 * be given to infinite loop which will act as our game loop.
 * A very simple game loop.
 * @author anthony-pc
 */
public class TankRotationExample extends JPanel  {

    public static final int WORLD_WIDTH = 2010;
    public static final int WORLD_HEIGHT = 2010;
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 700;
    private BufferedImage world;
    public static BufferedImage bulletImage;
    private Graphics2D buffer;
    private JFrame jFrame;
    private tankrotationexample.Tank tankOne;
    private tankrotationexample.Tank tankTwo;
    static long tickCount = 0;
    //Object[][] walls;
    //Wall[][] walls;
    ArrayList<Wall> walls;
    ArrayList<Health> tankOneHearts;
    ArrayList<Health> tankTwoHearts;
    ArrayList<Health> tankOneHealth;
    ArrayList<Health> tankTwoHealth;
    private tankrotationexample.Tank tankOneLife;
    private tankrotationexample.Tank tankTwoLife;



    public static void main(String[] args) {
        TankRotationExample tankExample = new TankRotationExample();
        tankExample.init();
        try {

            while (true) {
                tankExample.tankOne.update();
                tankExample.tankTwo.update();

                tankExample.repaint();
                tickCount++;
                if(tankExample.tankOne.getHitBox().intersects(tankExample.tankTwo.getHitBox())) {
                    //if(object instance Tank)
                    /*if(tankExample.tankOne.isDownPressed()) {
                        tankExample.tankOne.setX(tankExample.tankOne.getX() - tankExample.tankOne.getVx());
                        tankExample.tankOne.setY(tankExample.tankOne.getY() - tankExample.tankOne.getVy());
                    }
                    if(tankExample.tankTwo.isDownPressed()) {
                        tankExample.tankTwo.setX(tankExample.tankTwo.getX() - tankExample.tankTwo.getVx());
                        tankExample.tankTwo.setY(tankExample.tankTwo.getY() - tankExample.tankTwo.getVy());
                    }*/
                    //tankExample.tankOne.hasCollided();

                    System.out.println("Tanks have collided");
                }
              //  System.out.println(tankExample.t1);
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored);
        }

    }

    private BufferedImage checkLeft() {
        BufferedImage leftHalf;
        int x = tankOne.getX();
        int y = tankOne.getY();
        int xcoord = 0;
        int ycoord = 0;
        int midX = SCREEN_WIDTH/4 - 25; //300
        int midY = SCREEN_HEIGHT/2 - 25; //325


        if (x < midX) {
            xcoord = 0;
        }
        else if (x > WORLD_WIDTH-(midX+50)) {
            xcoord = WORLD_WIDTH - (midX+350);
        }
        else {
            xcoord = x - midX;
        }
        if (y < (midY-25)) {
            ycoord = 0;
        }
        else if (y > WORLD_HEIGHT-(midY+75)) {
            ycoord = WORLD_HEIGHT - (midY+375);
        }
        else {
            ycoord = y - (midY-25);
        }
        leftHalf = world.getSubimage(xcoord, ycoord, (TankRotationExample.SCREEN_WIDTH / 2), TankRotationExample.SCREEN_HEIGHT);

        return leftHalf;
    }

    private BufferedImage checkRight() {
        BufferedImage rightHalf;
        int x = tankTwo.getX();
        int y = tankTwo.getY();
        int xcoord = 0;
        int ycoord = 0;
        int midX = SCREEN_WIDTH/4 - 25; //300
        int midY = SCREEN_HEIGHT/2 - 25; //325


        if (x < midX) {
            xcoord = 0;
        }
        else if (x > WORLD_WIDTH-(midX+50)) {
            xcoord = WORLD_WIDTH - (midX+350);
        }
        else {
            xcoord = x - midX;
        }
        if (y < (midY-25)) {
            ycoord = 0;
        }
        else if (y > WORLD_HEIGHT-(midY+75)) {
            ycoord = WORLD_HEIGHT - (midY+375);
        }
        else {
            ycoord = y - (midY-25);
        }
        rightHalf = world.getSubimage(xcoord, ycoord, (TankRotationExample.SCREEN_WIDTH / 2), TankRotationExample.SCREEN_HEIGHT);

        return rightHalf;
    }


    private void init() {
        this.jFrame = new JFrame("Tank Rotation");
        this.world = new BufferedImage(TankRotationExample.WORLD_WIDTH, TankRotationExample.WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage tankImage = null;
        BufferedImage tank2Image = null;
        BufferedImage breakableWall1 = null;
        BufferedImage breakableWall2 = null;
        BufferedImage unBreakableWall = null;
        BufferedImage heart = null;
        BufferedImage slice = null;
        walls = new ArrayList<>();
        tankOneHearts = new ArrayList<>();
        tankTwoHearts = new ArrayList<>();
        tankOneHealth = new ArrayList<>();
        tankTwoHealth = new ArrayList<>();
        try {

            /*
             * There is a subtle difference between using class
             * loaders and File objects to read in resources for your
             * tank games. First, File objects when given to input readers
             * will use your project's working directory as the base path for
             * finding the file. Class loaders will use the class path of the project
             * as the base path for finding files. For Intellij, this will be in the out
             * folder. if you expand the out folder, the expand the production folder, you
             * will find a folder that has the same name as your project. This is the folder
             * where your class path points to by default. Resources, will need to be stored
             * in here for class loaders to load resources correctly.
             *
             * Also one important thing to keep in mind, Java input readers given File objects
             * cannot be used to access file in jar files. So when building your jar, if you want
             * all files to be stored inside the jar, you'll need to class loaders and not File
             * objects.
             *
             */
            //Using class loaders to read in resources
            tankImage = read(TankRotationExample.class.getClassLoader().getResource("tank1.png"));
            tank2Image = read(TankRotationExample.class.getClassLoader().getResource("tank2.png"));
            TankRotationExample.bulletImage = read(TankRotationExample.class.getClassLoader().getResource("bullet1.png"));
            breakableWall1 = read(TankRotationExample.class.getClassLoader().getResource("breakableWall1.png"));
            breakableWall2 = read(TankRotationExample.class.getClassLoader().getResource("breakableWall2.png"));
            unBreakableWall = read(TankRotationExample.class.getClassLoader().getResource("unbreakableWall.png"));
            heart = read(TankRotationExample.class.getClassLoader().getResource("heart.png"));
            slice = read(TankRotationExample.class.getClassLoader().getResource("healthBar.png"));
            //TankRotationExample.class.getClassLoader().getResourceAsStream();
            //Using file objects to read in resources.
            //tankImage = read(new File("resources/tank1.png"));
            InputStreamReader isr = new InputStreamReader(TankRotationExample.class.getClassLoader().getResourceAsStream("maps/map1"));
            BufferedReader mapReader = new BufferedReader(isr);

            String row = mapReader.readLine();
            if(row == null) {
                throw new IOException("no data in file");
            }
            String[] mapInfo = row.split("\t");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);

            for (int curRow = 0; curRow < numRows; curRow++) {
                row = mapReader.readLine();
                mapInfo = row.split("\t");
                for (int curCol = 0; curCol < numCols; curCol++) {
                    switch (mapInfo[curCol]) {
                        case "1":
                            BreakableWall br1 = new BreakableWall(curCol*30, curRow*30, breakableWall1);
                            this.walls.add(br1);
                            break;
                        case "2":
                            BreakableWall br = new BreakableWall(curCol*30, curRow*30, breakableWall2);
                            this.walls.add(br);
                            break;
                        case "3":
                        case "9":
                            UnbreakableWall unbr = new UnbreakableWall(curCol*30, curRow*30, unBreakableWall);
                            this.walls.add(unbr);

                    }
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        tankOne = new tankrotationexample.Tank(100, 500, 0, 0, 270, tankImage);
        tankTwo = new tankrotationexample.Tank(1860, 500, 0, 0, 270, tank2Image);
        for (int i = 0; i < 3; i++) {
            Hearts h1 = new Hearts(135+(45*i), 1820, heart);
            this.tankOneHearts.add(h1);
            Hearts h2 = new Hearts(1755+(45*i), 1820, heart);
            this.tankTwoHearts.add(h2);
        }
        for (int i = 0; i < 25; i++) {
            HealthBar s1 = new HealthBar(135+(5*i), 1880, slice);
            this.tankOneHealth.add(s1);
            HealthBar s2 = new HealthBar(1755+(5*i), 1880, slice);
            this.tankOneHealth.add(s2);
        }


        TankControl tankOneControl = new TankControl(tankOne, KeyEvent.VK_W,
                KeyEvent.VK_S,
                KeyEvent.VK_A,
                KeyEvent.VK_D,
                KeyEvent.VK_SPACE);
        TankControl tankTwoControl = new TankControl(tankTwo, KeyEvent.VK_UP,
                KeyEvent.VK_DOWN,
                KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT,
                KeyEvent.VK_ENTER);

        this.jFrame.setLayout(new BorderLayout());
        this.jFrame.add(this);
        this.jFrame.addKeyListener(tankOneControl);
        this.jFrame.addKeyListener(tankTwoControl);
        this.jFrame.setSize(TankRotationExample.SCREEN_WIDTH, TankRotationExample.SCREEN_HEIGHT + 30);
        this.jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        buffer = world.createGraphics();
        buffer.setColor(Color.BLACK);
        buffer.fillRect(0, 0, TankRotationExample.WORLD_WIDTH, TankRotationExample.WORLD_HEIGHT);
        this.walls.forEach(wall -> wall.drawImage(buffer));
        this.tankOneHearts.forEach(heart -> heart.drawImage(buffer));
        this.tankTwoHearts.forEach(heart -> heart.drawImage(buffer));
        this.tankOneHealth.forEach(s -> s.drawImage(buffer));
        this.tankTwoHealth.forEach(s -> s.drawImage(buffer));
        this.tankOne.drawImage(buffer);
        this.tankTwo.drawImage(buffer);
        BufferedImage leftHalf = checkLeft();
        BufferedImage rightHalf = checkRight();
        //BufferedImage leftHalf = world.getSubimage(tankOne.getX()-200, tankOne.getY(), TankRotationExample.SCREEN_WIDTH/2, TankRotationExample.SCREEN_HEIGHT);
        //BufferedImage rightHalf = world.getSubimage(tankTwo.getX(), tankTwo.getY(), TankRotationExample.SCREEN_WIDTH/2, TankRotationExample.SCREEN_HEIGHT);
        g2.drawImage(leftHalf,0,0,null);
        g2.drawImage(rightHalf,TankRotationExample.SCREEN_WIDTH/2,0,null);
        Rectangle splitLine = new Rectangle((TankRotationExample.SCREEN_WIDTH/2)-4, 0, 4, TankRotationExample.SCREEN_HEIGHT);
        g2.setColor(Color.DARK_GRAY);
        g2.fill(splitLine);
        //g2.drawRect((TankRotationExample.SCREEN_WIDTH/2)-2, 0, 2, TankRotationExample.SCREEN_HEIGHT);
        BufferedImage t1Life = world.getSubimage(125, 1805, 145, 145);
        g2.drawImage(t1Life, (SCREEN_WIDTH/2)-265, SCREEN_HEIGHT-205, null);
        BufferedImage t2Life = world.getSubimage(1745, 1805, 145, 145);
        g2.drawImage(t2Life, (SCREEN_WIDTH/2)+125, SCREEN_HEIGHT-205, null);
        BufferedImage minimap = world.getSubimage(0, 0, TankRotationExample.WORLD_WIDTH, TankRotationExample.WORLD_HEIGHT);
        g2.scale(.1, .1);
        g2.drawImage(minimap, (SCREEN_WIDTH*5)-(minimap.getWidth()/2), (SCREEN_HEIGHT*10)-(minimap.getHeight()+100), null);



    }
}
