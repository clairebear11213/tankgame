package tankrotationexample;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet {
    int x, y, vx, vy, angle;
    int R = 7;
    BufferedImage bulletImage;
    Rectangle hitBox;

    public Bullet(int x, int y, int angle, BufferedImage bulletImage) {
        this.x = x+20;
        this.y = y+20;
        this.angle = angle;
        this.bulletImage = bulletImage;
        this.hitBox = new Rectangle(x, y, this.bulletImage.getWidth(), this.bulletImage.getHeight());
    }

    public Rectangle getHitBox() {
        return hitBox.getBounds();
    }

    public void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
        this.hitBox.setLocation(x, y);
    }

    public void checkBorder() {
        if (x < 20) {
            x = 20;
        }
        if (x >= TankRotationExample.WORLD_WIDTH - 40) {
            x = TankRotationExample.WORLD_WIDTH - 40;
        }
        if (y < 20) {
            y = 20;
        }
        if (y >= TankRotationExample.WORLD_HEIGHT - 40) {
            y = TankRotationExample.WORLD_HEIGHT - 40;
        }
    }

    public void update() {
        moveForwards();
        /*if(this.hitBox.intersects()) {

        }*/
    }

    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.bulletImage.getWidth() / 2.0, this.bulletImage.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.bulletImage, rotation, null);
        g2d.setColor(Color.BLUE);
        g2d.drawRect(x, y, this.bulletImage.getWidth(), this.bulletImage.getHeight());
    }


}