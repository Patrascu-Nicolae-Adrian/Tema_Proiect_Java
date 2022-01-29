import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class Application {
    private static Application single_instance = null;
    private List<User> userList = new ArrayList<>();
    public User currentUser = null;

    static Application getInstance() {
        if ( single_instance == null) {
            single_instance = new Application();
        }
        return  single_instance;
    }

    private Application() {

        /*IDataLoader dataManager = Settings.dataLoaderHashMap.get(Settings.loadType);
        Random r = new Random();
        var studenti = dataManager.createStudentsData();
        var profesori = dataManager.createProfesorData();
        Student s = studenti[r.nextInt(studenti.length)];
        this.userList.add(new User(s.nume+" "+s.prenume, s.nume, new StudentStrategy(s)));
        s = studenti[r.nextInt(studenti.length)];
        this.userList.add(new User(s.nume+" "+s.prenume, s.nume, new StudentStrategy(s)));
        s = studenti[r.nextInt(studenti.length)];
        this.userList.add(new User(s.nume+" "+s.prenume, s.nume, new StudentStrategy(s)));
        Profesor p = profesori[r.nextInt(profesori.length)];
        this.userList.add(new User(p.nume+" "+p.prenume, p.nume, new TeacherStrategy(p)));
        p = profesori[r.nextInt(profesori.length)];
        this.userList.add(new User(p.nume+" "+p.prenume, p.nume, new TeacherStrategy(p)));
        try {
            FileOutputStream fos = new FileOutputStream("users.xml");
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.setExceptionListener(new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {
                    System.out.println("Exception:" + e.toString());
                }
            });
            encoder.writeObject(userList);
            encoder.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        this.initUsers();
    }

    private void initUsers() {
        try (FileInputStream fis = new FileInputStream("users.xml")) {
            XMLDecoder decoder = new XMLDecoder(fis);
            this.userList = (ArrayList<User>)decoder.readObject();
            decoder.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(User user) throws Exception {
        int index = userList.indexOf(user);
        if ( index != -1 ) {
            Application.getInstance().currentUser = userList.get(index);
        } else {
            throw new Exception("Username sau parola este gresita!");
        }
    }

    public void printUsersFile(){
        try {
            FileOutputStream fos = new FileOutputStream("users.xml");
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.setExceptionListener(new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {
                    System.out.println("Exception:" + e.toString());
                }
            });
            encoder.writeObject(userList);
            encoder.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void register(String name, String prenume, String username, String password) throws Exception {
        int ok=0;
        for(User u:userList){
            if(u.menuStrategy.getAccountHolderInformation().containsKey(name)){
                ok=1;
                break;
            }
        }
        if(ok==0){
            IDataLoader dataManager = Settings.dataLoaderHashMap.get(Settings.loadType);
            ManagerCursuri dataManager1 = new ManagerCursuri(dataManager.createCoursesData());
            Student stud = new Student();
            Profesor prof = new Profesor();
            int ok1=0, ok2=0;
            for(Curs c:dataManager1.cursuri){
                for(Student s : c.studenti){
                    if(s.nume.compareTo(name) == 0 && s.prenume.compareTo(prenume) == 0 ){
                        ok1=1;
                        stud = s;
                        break;
                    }
                }
                if(c.profu.nume.compareTo(name) == 0 && c.profu.prenume.compareTo(prenume) == 0){
                    ok2=1;
                    prof =c.profu;
                }
            }
            this.initUsers();
            if(ok1==1){
                this.userList.add(new User(name+" "+prenume,name,new StudentStrategy(stud)));
                printUsersFile();
                this.initUsers();
            }else if(ok2==1){
                this.userList.add(new User(name+" "+prenume,name,new TeacherStrategy(prof)));
                printUsersFile();
                this.initUsers();
            } else{
                throw new Exception("You are not registered for any course");

            }
        }else{
            throw new Exception("You already have an account");
        }
    }

    /*private static String generatePassword(int length) {
        String password = new String();
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] p = new char[length];

        p[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        p[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        p[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        p[3] = numbers.charAt(random.nextInt(numbers.length()));

        for (int i = 4; i < length; i++) {
            p[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        password = String.valueOf(p);
        return password;
    }*/
}
