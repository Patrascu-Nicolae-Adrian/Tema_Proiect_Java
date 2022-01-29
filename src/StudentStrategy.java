import javax.swing.*;
import java.util.*;

public class StudentStrategy implements MenuStrategy{
    public Student student;
    public StudentStrategy() { }
    StudentStrategy(Student student) {
        this.student = student;
    }

    @Override
    public UserAccountType getAccountType() {
        return UserAccountType.STUDENT;
    }

    @Override
    public HashMap<String, String> getAccountHolderInformation() {
        return new HashMap<>() {{
            put(student.nume, student.prenume);
        }};
    }

    @Override
    public String getAccountMenuOptions(int index) {
        IDataLoader dataManager = Settings.dataLoaderHashMap.get(Settings.loadType);
        ManagerCursuri dataManagerCursuri = new ManagerCursuri(dataManager.createCoursesData());
        JFrame owner = new JFrame();
        int year = StudentForm.getYear();
        HashMap<String,String> getacc = (HashMap<String, String>) Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().clone();
        Curs[] cursuri =dataManager.createCoursesData();
        for(Curs c: cursuri){
            for(Student s: c.studenti) {
                if (s.prenume.compareTo(getacc.get(student.nume)) == 0) {
                    this.student = s;
                }
            }
        }
        switch (index)
        {
            case 1:
                return dataManagerCursuri.viewStudentCurses(student,year);
            case 2:
                return dataManagerCursuri.viewGradesOfCourses(student,year);
            case 3:
                return dataManagerCursuri.viewAverage(student,year);
            case 4:
                return dataManagerCursuri.viewRestanta(student, year);
        }
        return "";
    }

    public String getAccountMenuOptionsConsola(int index){
        IDataLoader dataManager = Settings.dataLoaderHashMap.get(Settings.loadType);
        ManagerCursuri dataManagerCursuri = new ManagerCursuri(dataManager.createCoursesData());
        System.out.print("Introduceti anul: ");
        Scanner sc = new Scanner(System.in);
        int year = sc.nextInt();
        HashMap<String,String> getacc = (HashMap<String, String>) Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().clone();
        Curs[] cursuri =dataManager.createCoursesData();
        for(Curs c: cursuri){
            for(Student s: c.studenti) {
                if (s.prenume.compareTo(getacc.get(student.nume)) == 0) {
                    this.student = s;
                }
            }
        }
        switch (index)
        {
            case 1:
                return dataManagerCursuri.viewStudentCurses(student,year);
            case 2:
                return dataManagerCursuri.viewGradesOfCourses(student,year);
            case 3:
                return dataManagerCursuri.viewAverage(student,year);
            case 4:
                return dataManagerCursuri.viewRestanta(student, year);
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
