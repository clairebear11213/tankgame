package tankrotationexample;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static javax.imageio.ImageIO.read;


public abstract class Health {
    public abstract void init();
    public abstract void drawImage(Graphics g);
    public abstract void update();
}
