package tankrotationexample;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class Hearts extends Health {
    int x, y;
    int state = 1;
    BufferedImage heart;

    public Hearts(int x, int y, BufferedImage heart) {
        this.x = x;
        this.y = y;
        this.heart = heart;
    }

    @Override
    public void init() {
        try {
            heart = read(TankRotationExample.class.getClassLoader().getResource("heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.heart, x, y, null);
    }

    @Override
    public void update() {
        if (state == 1) {
            try {
                heart = read(TankRotationExample.class.getClassLoader().getResource("heart.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            //disappear
        }
    }
}
