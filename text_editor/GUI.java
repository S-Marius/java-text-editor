import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import java.awt.event.*;

/**
 * GUI
 */
public class GUI implements ActionListener {
    boolean wordWrap = false;

    // text area
    JFrame window;
    JTextArea textArea;
    JScrollPane scrollPane;
    // top menu bar
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuColor;
    // file menu
    JMenuItem itemNew, itemOpen, itemSave, itemSaveAs, itemExit;
    // edit menu
    JMenuItem itemUndo, itemRedo;
    // format menu
    JMenuItem itemWrap, itemFontArial, itemFontCSMS, itemFontTNR, itemFontSize8, itemFontSize12, itemFontSize16;
    JMenu menuFont, menuFontSize;
    // color menu
    JMenuItem itemColor1, itemColor2, itemColor3;
    
    FunctionsFile file = new FunctionsFile(this);
    FunctionsFormat format = new FunctionsFormat(this);
    FunctionsColor color = new FunctionsColor(this);
    FunctionsEdit edit = new FunctionsEdit(this);
    KeyHandler keyHandler = new KeyHandler(this);
    
    UndoManager undoManager = new UndoManager();
    

    public static void main(String[] args) {
        new GUI();   
    }

    public GUI() {
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createFormatMenu();
        createColorMenu();

        format.selectedFont = "Arial";
        format.createFont(12);
        format.wordWrap();
        color.changeColor("White");
        window.setVisible(true);
    }

    public void createWindow() {
        window = new JFrame("text editor");
        window.setSize(1000, 700);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createTextArea() {
        textArea = new JTextArea();
        
        textArea.addKeyListener(keyHandler);
    
        textArea.getDocument().addUndoableEditListener(
            new UndoableEditListener() {
                public void undoableEditHappened(UndoableEditEvent e) {
                    undoManager.addEdit(e.getEdit());
                }
            }
        );
    
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
        LineNumberingTextArea lineNumberingTextArea = new LineNumberingTextArea(textArea);
        scrollPane.setRowHeaderView(lineNumberingTextArea);
    
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);
    }
    

    public void createMenuBar() {
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);

        menuColor = new JMenu("Color");
        menuBar.add(menuColor);
    }

    public void createFileMenu() {
        itemNew = new JMenuItem("New");
        itemNew.addActionListener(this);
        itemNew.setActionCommand("New");
        menuFile.add(itemNew);

        itemOpen = new JMenuItem("Open");
        itemOpen.addActionListener(this);
        itemOpen.setActionCommand("Open");
        menuFile.add(itemOpen);

        itemSave = new JMenuItem("Save");
        itemSave.addActionListener(this);
        itemSave.setActionCommand("Save");
        menuFile.add(itemSave);

        itemSaveAs = new JMenuItem("Save As");
        itemSaveAs.addActionListener(this);
        itemSaveAs.setActionCommand("SaveAs");
        menuFile.add(itemSaveAs);

        itemExit = new JMenuItem("Exit");
        itemExit.addActionListener(this);
        itemExit.setActionCommand("Exit");
        menuFile.add(itemExit);
    }

    public void createEditMenu() {
        itemUndo = new JMenuItem("Undo");
        itemUndo.addActionListener(this);
        itemUndo.setActionCommand("Undo");
        menuEdit.add(itemUndo);

        itemRedo = new JMenuItem("Redo");
        itemRedo.addActionListener(this);
        itemRedo.setActionCommand("Redo");
        menuEdit.add(itemRedo);
    }

    public void createFormatMenu() {
        itemWrap = new JMenuItem("Word Wrap: Off");
        itemWrap.addActionListener(this);
        itemWrap.setActionCommand("Word Wrap");
        menuFormat.add(itemWrap);

        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);

        itemFontArial = new JMenuItem("Arial");
        itemFontArial.addActionListener(this);
        itemFontArial.setActionCommand("Arial");
        menuFont.add(itemFontArial);

        itemFontCSMS = new JMenuItem("Comic Sans MS");
        itemFontCSMS.addActionListener(this);
        itemFontCSMS.setActionCommand("Comic Sans MS");
        menuFont.add(itemFontCSMS);

        itemFontTNR = new JMenuItem("Times New Roman");
        itemFontTNR.addActionListener(this);
        itemFontTNR.setActionCommand("Times New Roman");
        menuFont.add(itemFontTNR);

        menuFontSize = new JMenu("Font Size");
        menuFormat.add(menuFontSize);

        itemFontSize8 = new JMenuItem("8");
        itemFontSize8.addActionListener(this);
        itemFontSize8.setActionCommand("Size 8");
        menuFont.add(itemFontSize8);
    }

    public void createColorMenu() {
        itemColor1 = new JMenuItem("White");
        itemColor1.addActionListener(this);
        itemColor1.setActionCommand("White");
        menuColor.add(itemColor1);

        itemColor2 = new JMenuItem("Black");
        itemColor2.addActionListener(this);
        itemColor2.setActionCommand("Black");
        menuColor.add(itemColor2);

        itemColor3 = new JMenuItem("Blue");
        itemColor3.addActionListener(this);
        itemColor3.setActionCommand("Blue");
        menuColor.add(itemColor3);
    }

  @Override
public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    switch (command) {
        case "New":
            file.newFile();
            break;
        case "Open":
            file.openFile();
            break;
        case "Save":
            file.saveFile();
            break;
        case "SaveAs":
            file.saveFileAs();
            break;
        case "Exit":
            file.exit();
            break;
        case "Undo":
            edit.undo();
            break;
        case "Redo":
            edit.redo();
            break;
        case "Word Wrap":
            format.wordWrap();
            break;
        case "Arial":
        case "Comic Sans MS":
        case "Times New Roman":
            format.setFont(command);
            break;
        case "Size 8":
            format.createFont(8);
            break;
        case "White":
        case "Black":
        case "Blue":
            color.changeColor(command);
            break;
        default:
            // Handle unknown command
            System.out.println("Unknown command: " + command);
            break;
    }
}

}