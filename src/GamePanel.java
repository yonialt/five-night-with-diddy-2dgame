import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    final int tileSize = 16 * 3;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int fps = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    Players[] player = new Players[4];
    ArrayList<Bullet> bullets = new ArrayList<>();
    Image backgound;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        player[0] = new Players("P1", 700, 500, "/Image/bob.png");
        player[1] = new Players("P2", 20, 100, "/Image/bob5.png");
        player[2] = new Players("P3", 700, 100, "/Image/bob3.png");
        player[3] = new Players("P4", 10, 500, "/Image/bob4.png");
        backgound = new ImageIcon(getClass().getResource("/Image/background.png")).getImage();

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000.0 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = Math.max(0, remainingTime / 1_000_000);
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        for (int i = 0; i < player.length; i++) {
            if (!player[i].alive) continue;

            player[i].up = keyH.directions[i][0];
            player[i].down = keyH.directions[i][1];
            player[i].left = keyH.directions[i][2];
            player[i].right = keyH.directions[i][3];
            player[i].update();

            if (keyH.attacks[i]) {
                keyH.attacks[i] = false;

                int dx = 0, dy = 0;
                if (player[i].up) dy = -1;
                else if (player[i].down) dy = 1;
                else if (player[i].left) dx = -1;
                else if (player[i].right) dx = 1;
                else dy = -1; // default up

                bullets.add(new Bullet(player[i].x + 24, player[i].y + 24, dx, dy, player[i]));
            }
        }

        ArrayList<Bullet> toRemove = new ArrayList<>();

        for (Bullet bullet : bullets) {
            bullet.update();

            for (Players p : player) {
                if (p == bullet.owner || !p.alive) continue;

                if (bullet.getHitbox().intersects(p.hitbox)) {
                    p.takeDamage(20);
                    bullet.active = false;
                }
            }

            if (!bullet.active) toRemove.add(bullet);
        }

        bullets.removeAll(toRemove);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(backgound, 0, 0, screenWidth, screenHeight, this);

        for (Players p : player) {
            p.draw(g2, tileSize, this);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(g2);
        }

        g2.dispose();
    }
}
