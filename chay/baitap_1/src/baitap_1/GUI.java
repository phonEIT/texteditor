package baitap_1;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

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

public class GUI implements ActionListener {
	// text Area
	JFrame window;
	JTextArea textArea;
	boolean wordWrapOn = false;
	// TOP MENU BAR
	JScrollPane scrollPane;
	JMenuBar menuBar;
	// FILE MENU
	JMenu menuFile, menuEdit, menuFormat, menuColor;
	JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
	// MENU COLOR
	JMenuItem icolor1, icolor2, icolor3;

	// EDIT MENU
	JMenuItem iUndo, iRedo;
	// FORMAT MENU
	JMenuItem iwarp, iFontArial, iFontSCMS, iFontTNR, iFontSize8, iFontSize12, iFontSize16, iFontSize20, iFontSize24,
			iFontSize28;
	JMenu menuFont, menuFontSize;
	Function_File file = new Function_File(this);
	Function_Format format = new Function_Format(this);
	Function_Color color = new Function_Color(this);
	Function_Edit edit = new Function_Edit(this);

	UndoManager um = new UndoManager();

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

		format.selectedFont = "Aroial";
		format.createFont(30);
		format.wordWrap();
		color.changeColor("White");
		window.setVisible(true);

	}

	public void createWindow() {
		window = new JFrame("nopad");
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void createTextArea() {
		textArea = new JTextArea();

		textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {

			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				um.addEdit(e.getEdit());

			}
		});
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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

		menuColor = new JMenu("Colors");
		menuBar.add(menuColor);
	}

	public void createFileMenu() {
		iNew = new JMenuItem("New");

		iNew.addActionListener(this);
		iNew.setActionCommand("New");
		menuFile.add(iNew);

		iOpen = new JMenuItem("Open");

		iOpen.addActionListener(this);
		iOpen.setActionCommand("Open");
		menuFile.add(iOpen);

		iSave = new JMenuItem("Save");
		iSave.addActionListener(this);
		iSave.setActionCommand("Save");
		menuFile.add(iSave);

		iSaveAs = new JMenuItem("SaveAs");

		iSaveAs.addActionListener(this);
		iSaveAs.setActionCommand("SaveAs");
		menuFile.add(iSaveAs);

		iExit = new JMenuItem("Exit");

		iExit.addActionListener(this);
		iExit.setActionCommand("Exit");
		menuFile.add(iExit);
	}

	public void createEditMenu() {

		iUndo = new JMenuItem("Undo");
		iUndo.addActionListener(this);
		iUndo.setActionCommand("Undo");
		menuEdit.add(iUndo);

		iRedo = new JMenuItem("Redo");
		iRedo.addActionListener(this);
		iRedo.setActionCommand("Redo");
		menuEdit.add(iRedo);

	}

	public void createFormatMenu() {
		iwarp = new JMenuItem("Word Wrap: Off");
		iwarp.addActionListener(this);
		iwarp.setActionCommand("Word Wrap");
		menuFormat.add(iwarp);

		menuFont = new JMenu("Font");
		menuFormat.add(menuFont);

		// iFontArial
		iFontArial = new JMenuItem("Arial");
		iFontArial.addActionListener(this);
		iFontArial.setActionCommand("Arial");
		menuFont.add(iFontArial);
		// iFontTNR
		iFontTNR = new JMenuItem("Times New Roman");
		iFontTNR.addActionListener(this);
		iFontTNR.setActionCommand("Times New Roman");
		menuFont.add(iFontTNR);

		// iFontSCMS

		iFontSCMS = new JMenuItem("Comic Sans MS");
		iFontSCMS.addActionListener(this);
		iFontSCMS.setActionCommand("Comic Sans MS");
		menuFont.add(iFontSCMS);

		// FontSize
		menuFontSize = new JMenu("Font Size");
		menuFormat.add(menuFontSize);

		// iFontSize8
		iFontSize8 = new JMenuItem("8");
		iFontSize8.addActionListener(this);
		iFontSize8.setActionCommand("Size8");
		menuFontSize.add(iFontSize8);
		// iFozntSize12
		iFontSize12 = new JMenuItem("12");
		iFontSize12.addActionListener(this);
		iFontSize12.setActionCommand("Size12");
		menuFontSize.add(iFontSize12);
		// iFontSize16
		iFontSize16 = new JMenuItem("16");
		iFontSize16.addActionListener(this);
		iFontSize16.setActionCommand("Size16");
		menuFontSize.add(iFontSize16);
		// iFontSize20
		iFontSize20 = new JMenuItem("20");
		iFontSize20.addActionListener(this);
		iFontSize20.setActionCommand("Size20");
		menuFontSize.add(iFontSize20);
		// iFontSize24
		iFontSize24 = new JMenuItem("24");
		iFontSize24.addActionListener(this);
		iFontSize24.setActionCommand("Size24");
		menuFontSize.add(iFontSize24);
		// iFontSize28
		iFontSize28 = new JMenuItem("28");
		iFontSize28.addActionListener(this);
		iFontSize28.setActionCommand("Size28");
		menuFontSize.add(iFontSize28);

	}

	public void createColorMenu() {
		icolor1 = new JMenuItem("White");
		icolor1.addActionListener(this);
		icolor1.setActionCommand("White");
		menuColor.add(icolor1);

		icolor2 = new JMenuItem("Black");
		icolor2.addActionListener(this);
		icolor2.setActionCommand("Black");
		menuColor.add(icolor2);

		icolor3 = new JMenuItem("Blue");
		icolor3.addActionListener(this);
		icolor3.setActionCommand("Blue");
		menuColor.add(icolor3);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		switch (command) {
		case "New":
			file.newFile();
			break;
		case "Open":
			file.open();
			break;
		case "Save":
			file.save();
			break;
		case "SaveAs":
			file.saveAs();
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
			format.setfont(command);
			;
			break;
		case "Times New Roman":
			format.setfont(command);
			;
			break;
		case "Comic Sans MS":
			format.setfont(command);
			;
			break;

		case "Size8":
			format.createFont(8);
			break;
		case "Size12":
			format.createFont(12);
			break;
		case "Size16":
			format.createFont(16);
			break;
		case "Size20":
			format.createFont(20);
			break;
		case "Size24":
			format.createFont(24);
			break;
		case "Size28":
			format.createFont(28);
			break;

		case "White":
			color.changeColor(command);
			break;
		case "Black":
			color.changeColor(command);
			break;
		case "Blue":
			color.changeColor(command);
			break;

		}

	}

}
