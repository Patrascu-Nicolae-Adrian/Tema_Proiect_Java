import javax.swing.*;
import java.io.Console;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import java.io.File;

enum LOAD_TYPE {
	HARDCODAT, KEYBOARD, FILE
}

enum DISPLAY_TYPE  {
	CONSOLA, FILE, GUI
}

public class TestClass {
	public static void main(String[] args) {
		Settings.initApplication();
		NewThread guiThread = new NewThread("GUI");
		NewThread consolaThread = new NewThread("Consola");
		guiThread.start();
		consolaThread.start();
	}
}
