import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Settings {
    static String STUDENTS_PATH = "studenti.csv";
    static String TEACHERS_PATH = "profesori.csv";
    static String COURSES_PATH = "cursuri.csv";
    static LOAD_TYPE loadType = LOAD_TYPE.HARDCODAT;
    static DISPLAY_TYPE displayType = DISPLAY_TYPE.GUI;
    static HashMap<LOAD_TYPE, IDataLoader> dataLoaderHashMap = new HashMap<>() {
        {
            put(LOAD_TYPE.HARDCODAT, new HardcodatDataManager());
            put(LOAD_TYPE.FILE, new FileDataManager());
            put(LOAD_TYPE.KEYBOARD, new KeyboardDataManager());
        }  };
    static HashMap<DISPLAY_TYPE, IDisplayManager> displayHashMap = new HashMap<>() {
        {
            put(DISPLAY_TYPE.CONSOLA, new ConsoleDisplay());
            put(DISPLAY_TYPE.FILE, new FileDisplay());
            put(DISPLAY_TYPE.GUI, new GraphicUserInterfaceDisplay());
        }  };


    public static void initApplication() {
        try {
            File settings = new File("settings.txt");
            BufferedReader br = new BufferedReader(new FileReader(settings));
            String line = br.readLine();
            int i = 0;
            while (line != null && i < 3) {
                line = br.readLine();
                i++;
            }
            String[] split = line.split("\"");
            loadType = LOAD_TYPE.valueOf(split[1]);
            line = br.readLine();
            split = line.split("\"");
            displayType = DISPLAY_TYPE.valueOf(split[1]);
            br.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
