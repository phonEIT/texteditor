package Main;

import Controller.Controller;
import Model.Model;
import View.GUI;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        GUI gui = new GUI(model);
        Controller controller = new Controller(model, gui);
    }
}
