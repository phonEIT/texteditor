package Controller;

import Model.Model;
import View.GUI;

public class Controller {
    private Model model;
    private GUI gui;

    public Controller(Model model, GUI gui) {
        this.model = model;
        this.gui = gui;

        setupEventHandlers();
    }

    private void setupEventHandlers() {
        gui.display();
    }
}
