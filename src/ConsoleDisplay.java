public class ConsoleDisplay implements IDisplayManager {
    @Override
    public void displayStudents(Student[] students) {
        for (Student s :
                students) {
            System.out.println(s);
        }
    }

    @Override
    public void displayTeachers(Profesor[] profesors) {
        for(Profesor p:profesors) {
            System.out.println(p);
        }
    }

    @Override
    public void displayCourses(Curs[] cursuri) {
        for(Curs c:cursuri) {
            System.out.println(c);
        }
    }
}
