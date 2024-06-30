public class FunctionsEdit {
    GUI gui;

    public FunctionsEdit(GUI gui) {
        this.gui = gui;
    }

    public void undo() {
        gui.undoManager.undo();
    }

    public void redo() {
        gui.undoManager.redo();
    }
}
