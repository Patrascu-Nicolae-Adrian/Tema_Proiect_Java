import javax.swing.*;
import java.util.HashMap;
import java.util.Scanner;

public class TeacherStrategy implements MenuStrategy {
    public Profesor profesor;
    public TeacherStrategy() { }

    TeacherStrategy(Profesor p) {
        this.profesor = p;
    }
    @Override
    public UserAccountType getAccountType() {
        return UserAccountType.TEACHER;
    }

    @Override
    public HashMap<String, String> getAccountHolderInformation() {
        return new HashMap<>() {{
            put(profesor.nume, profesor.prenume);
        }};
    }

    @Override
    public String getAccountMenuOptions(int index) {
        IDataLoader dataManager = Settings.dataLoaderHashMap.get(Settings.loadType);
        ManagerCursuri dataManagerCursuri = new ManagerCursuri(dataManager.createCoursesData());
        JFrame owner = new JFrame();
        HashMap<String,String> getacc = (HashMap<String, String>) Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().clone();
        String nume = this.profesor.nume;
        Curs[] cursuri =dataManager.createCoursesData();
        for(Curs c: cursuri){
            if(c.profu.prenume.compareTo(getacc.get(profesor.nume)) == 0){
                this.profesor = c.profu;
            }
        }
        switch (index)
        {
            case 1:
                return nume +dataManagerCursuri.listTeacherCourses(profesor);
            case 2:
                return dataManagerCursuri.listStudentsOfCourses(profesor);
            case 3:
                return dataManagerCursuri.gradeStudent(profesor,TeacherForm.getName(),TeacherForm.getNota());
        }
        return "";
    }

    public String getAccountMenuOptionsConsola(int index){
        IDataLoader dataManager = Settings.dataLoaderHashMap.get(Settings.loadType);
        ManagerCursuri dataManagerCursuri = new ManagerCursuri(dataManager.createCoursesData());
        Scanner sc = new Scanner(System.in);
        HashMap<String,String> getacc = (HashMap<String, String>) Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().clone();
        Curs[] cursuri =dataManager.createCoursesData();
        for(Curs c: cursuri){
            if(c.profu.prenume.compareTo(getacc.get(profesor.nume)) == 0){
                this.profesor = c.profu;
            }
        }
        switch (index)
        {
            case 1:
                return dataManagerCursuri.listTeacherCourses(profesor);
            case 2:
                return dataManagerCursuri.listStudentsOfCourses(profesor);
            case 3:
                System.out.println("Introduceti numele si prenumele studentului:");
                String name = sc.nextLine();
                System.out.println("Introduceti nota studentului:");
                int nota = sc.nextInt();
                return dataManagerCursuri.gradeStudent(profesor,name,nota);
        }
        return "";
    }

    @Override
    public void nextMenuOption(int index) {
    }

    @Override
    public void previousMenuOption(int index) {

    }
}
