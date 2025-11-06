import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean[][] directions = new boolean[4][4];
    public boolean[] attacks = new boolean[4];
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Player 1 - WASD
        if (code == KeyEvent.VK_W) directions[0][0] = true;
        if (code == KeyEvent.VK_S) directions[0][1] = true;
        if (code == KeyEvent.VK_A) directions[0][2] = true;
        if (code == KeyEvent.VK_D) directions[0][3] = true;
        if (code == KeyEvent.VK_X) attacks[0] = true;

        // Player 2 - Arrow Keys
        if (code == KeyEvent.VK_UP) directions[1][0] = true;
        if (code == KeyEvent.VK_DOWN) directions[1][1] = true;
        if (code == KeyEvent.VK_LEFT) directions[1][2] = true;
        if (code == KeyEvent.VK_RIGHT) directions[1][3] = true;
        if (code == KeyEvent.VK_CONTROL) attacks[1] = true;

        // Player 3 - IJKL
        if (code == KeyEvent.VK_I) directions[2][0] = true;
        if (code == KeyEvent.VK_K) directions[2][1] = true;
        if (code == KeyEvent.VK_J) directions[2][2] = true;
        if (code == KeyEvent.VK_L) directions[2][3] = true;
        if (code == KeyEvent.VK_M) attacks[2] = true;

        // Player 4 - TFGH
        if (code == KeyEvent.VK_T) directions[3][0] = true;
        if (code == KeyEvent.VK_G) directions[3][1] = true;
        if (code == KeyEvent.VK_F) directions[3][2] = true;
        if (code == KeyEvent.VK_H) directions[3][3] = true;
        if (code == KeyEvent.VK_B) attacks[3] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) directions[0][0] = false;
        if (code == KeyEvent.VK_S) directions[0][1] = false;
        if (code == KeyEvent.VK_A) directions[0][2] = false;
        if (code == KeyEvent.VK_D) directions[0][3] = false;
        if (code == KeyEvent.VK_X) attacks[0] = false;

        if (code == KeyEvent.VK_UP) directions[1][0] = false;
        if (code == KeyEvent.VK_DOWN) directions[1][1] = false;
        if (code == KeyEvent.VK_LEFT) directions[1][2] = false;
        if (code == KeyEvent.VK_RIGHT) directions[1][3] = false;
        if (code == KeyEvent.VK_CONTROL) attacks[1] = false;

        if (code == KeyEvent.VK_I) directions[2][0] = false;
        if (code == KeyEvent.VK_K) directions[2][1] = false;
        if (code == KeyEvent.VK_J) directions[2][2] = false;
        if (code == KeyEvent.VK_L) directions[2][3] = false;
        if (code == KeyEvent.VK_M) attacks[2] = false;

        if (code == KeyEvent.VK_T) directions[3][0] = false;
        if (code == KeyEvent.VK_G) directions[3][1] = false;
        if (code == KeyEvent.VK_F) directions[3][2] = false;
        if (code == KeyEvent.VK_H) directions[3][3] = false;
        if (code == KeyEvent.VK_X) attacks[3] = false;
    }
}
