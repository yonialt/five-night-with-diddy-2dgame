import java.awt.*;

public class Bullet {
    public int x, y;
    public int speed = 6;
    public int directionX, directionY;
    public boolean active = true;
    public Players owner;
    public final int size = 12;

    public Bullet(int x, int y, int dx, int dy, Players owner) {
        this.x = x;
        this.y = y;
        this.directionX = dx;
        this.directionY = dy;
        this.owner = owner;
    }

    public void update() {
        x += directionX * speed;
        y += directionY * speed;

        // Remove bullet if off-screen
        if (x < 0 || y < 0 || x > 800 || y > 600) {
            active = false;
        }
    }

    public Rectangle getHitbox() {
        return new Rectangle(x, y, size, size);
    }

    public void draw(Graphics2D g2) {
        if (active) {
            g2.setColor(Color.red);
            g2.fillOval(x, y, size, size);
        }
    }
}
