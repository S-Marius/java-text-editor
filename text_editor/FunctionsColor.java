import java.awt.Color;

public class FunctionsColor {
    GUI gui;
    
    public FunctionsColor(GUI gui) {
        this.gui = gui;
    }

    public void changeColor(String color) {
        switch (color) {
            case "White":
                gui.textArea.setBackground(Color.white);
                gui.textArea.setForeground(Color.black);
                break;
            case "Black":
                gui.textArea.setBackground(Color.black);
                gui.textArea.setForeground(Color.white);
                break;
            case "Blue":
                gui.textArea.setBackground(Color.blue);
                gui.textArea.setForeground(Color.white);
                break;
            
        }
    }
}
