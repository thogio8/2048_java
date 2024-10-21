import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game2048GUI extends JPanel {
    private static final int TILE_SIZE = 100;
    private static final int TILE_MARGIN = 16;
    private Game2048 game;

    public Game2048GUI(Game2048 game) {
        this.game = game;
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        game.moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        game.moveDown();
                        break;
                    case KeyEvent.VK_LEFT:
                        game.moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.moveRight();
                        break;
                }
                repaint();
                if (game.isGameOver()) {
                    JOptionPane.showMessageDialog(null, "Game Over!");
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize(); j++) {
                drawTile(g, game.getBoard()[i][j], i, j);
            }
        }
    }

    private void drawTile(Graphics g, int value, int row, int col) {
        int xOffset = offsetCoors(col);
        int yOffset = offsetCoors(row);
        g.setColor(getBackground(value));
        g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 14, 14);
        g.setColor(getForeground(value));
        final int size = value < 100 ? 36 : value < 1000 ? 32 : 24;
        final Font font = new Font("Arial", Font.BOLD, size);
        g.setFont(font);
        String s = String.valueOf(value);
        final FontMetrics fm = getFontMetrics(font);
        final int w = fm.stringWidth(s);
        final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];
        if (value != 0) {
            g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);
        }
    }

    private static int offsetCoors(int arg) {
        return arg * (TILE_MARGIN + TILE_SIZE) + TILE_MARGIN;
    }

    private static Color getBackground(int value) {
        switch (value) {
            case 2: return new Color(0xeee4da);
            case 4: return new Color(0xede0c8);
            case 8: return new Color(0xf2b179);
            case 16: return new Color(0xf59563);
            case 32: return new Color(0xf67c5f);
            case 64: return new Color(0xf65e3b);
            case 128: return new Color(0xedcf72);
            case 256: return new Color(0xedcc61);
            case 512: return new Color(0xedc850);
            case 1024: return new Color(0xedc53f);
            case 2048: return new Color(0xedc22e);
        }
        return new Color(0xcdc1b4);
    }

    private static Color getForeground(int value) {
        return value < 16 ? new Color(0x776e65) : new Color(0xf9f6f2);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Game2048 game = new Game2048();
        Game2048GUI gui = new Game2048GUI(game);
        frame.setTitle("2048 Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.add(gui);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}