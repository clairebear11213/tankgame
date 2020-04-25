package tankrotationexample;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class UnbreakableWall extends Wall {
    int x, y;
    int state = 2;
    BufferedImage wallImage;
    Rectangle hitBox;

    public UnbreakableWall(int x, int y, BufferedImage wallImage) {
        this.x = x;
        this.y = y;
        this.wallImage = wallImage;
        this.hitBox = new Rectangle(x, y, this.wallImage.getWidth(), this.wallImage.getHeight());
    }

    public Rectangle getHitBox() {
        return hitBox.getBounds();
    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(this.wallImage, x, y, null);
        g2.setColor(Color.GRAY);
        g2.drawRect(x, y, this.wallImage.getWidth(), this.wallImage.getHeight());
    }
}
