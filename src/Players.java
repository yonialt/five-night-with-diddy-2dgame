import java.awt.*;
import javax.swing.*;
import java.net.URL;

public class Players {
    public int x, y;
    public int speed = 2;
    public boolean up, down, left, right;
    public int health = 100;
    public boolean alive = true;

    public Rectangle hitbox;
    public Image sprite;
    public String name;

    public Players(String name, int x, int y, String spritePath) {
        this.name = name;
        this.x = x;
        this.y = y;

        URL imgURL = getClass().getResource(spritePath);
        if (imgURL != null) {
            this.sprite = new ImageIcon(imgURL).getImage();
        } else {
            System.err.println("Sprite not found: " + spritePath);
        }

        hitbox = new Rectangle(x, y, 48, 48); // default size
    }

    public void update() {
        if (!alive) return;

        if (up) y -= speed;
        if (down) y += speed;
        if (left) x -= speed;
        if (right) x += speed;

        hitbox.setLocation(x, y);
    }

    public void takeDamage(int amount) {
        if (!alive) return;

        health -= amount;
        if (health <= 0) {
            alive = false;
            System.out.println(name + " has died!");
        }
    }

    public void draw(Graphics2D g2, int tileSize, JComponent comp) {
        if (!alive) return;

        if (sprite != null) {
            g2.drawImage(sprite, x, y, tileSize, tileSize, comp);
        } else {
            g2.setColor(Color.red);
            g2.fillRect(x, y, tileSize, tileSize);
        }

        // Health bar
        g2.setColor(Color.red);
        g2.fillRect(x, y - 10, tileSize, 5);
        g2.setColor(Color.green);
        g2.fillRect(x, y - 10, (int)(tileSize * (health / 100.0)), 5);
    }
}

