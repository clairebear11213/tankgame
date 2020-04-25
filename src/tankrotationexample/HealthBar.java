package tankrotationexample;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class HealthBar extends Health {
    int x, y;
    int state = 1;
    BufferedImage slice;

    public HealthBar(int x, int y, BufferedImage slice) {
        this.x = x;
        this.y = y;
        this.slice = slice;
    }

    @Override
    public void init() {
        try {
            slice = read(TankRotationExample.class.getClassLoader().getResource("healthBar.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.slice, x, y, null);
    }

    @Override
    public void update() {
        if (state == 1) {
            try {
                slice = read(TankRotationExample.class.getClassLoader().getResource("healthBar.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            //disappear
        }
    }
}
