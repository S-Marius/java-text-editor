import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GUI gui;

    public KeyHandler(GUI gui) {
        this.gui = gui;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S && e.isControlDown()) {
            gui.file.saveFile();
        }

        // if (e.isAltDown() && e.getKeyCode() == KeyEvent.VK_F) {
        //     gui.menuFile.doClick();
        // }
        
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }


    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

}
