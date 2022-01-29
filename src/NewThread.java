import javax.swing.*;
import java.util.Scanner;

public class NewThread extends Thread{

    private String executionName;
    NewThread(String executionName){
        this.executionName = executionName;
    }

    public void run(){
        if(executionName == "GUI"){
            JFrame frame = new JFrame("Graphic user interface");
            LoginForm loginForm = new LoginForm(frame);
            frame.setContentPane(loginForm.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocation(500,100);
            frame.setSize(500, 400);
            frame.setVisible(true);
        }else if(executionName == "Consola"){
            Scanner sc = new Scanner(System.in);
            int index = 0;
            while(index!=3) {
                System.out.println("Introduceti optiunea dorita:\n1. Login\n2. Register\n3. Close");
                index = sc.nextInt();
                if (index == 1) {
                    System.out.println("Username = ");
                    String  username = sc.nextLine();
                    username = sc.nextLine();
                    System.out.println("Password = ");
                    String  password = sc.next();

                    try {
                        Application.getInstance().login(new User(username, password));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    if (Application.getInstance().currentUser.menuStrategy.getAccountType() == UserAccountType.STUDENT) {
                        while (index != 5) {
                            System.out.print("1. Vizualizati cursurile\n2. Vizualizati notele de la cursuri\n3. Media dintr-un an universitar\n4. Restante\n5. Back \nIntroduceti optiunea dorita: ");
                            index = sc.nextInt();
                            if(index == 5){
                                break;
                            }
                            try {
                                Application.getInstance().login(new User(username, password));
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println(Application.getInstance().currentUser.menuStrategy.getAccountMenuOptionsConsola(index));


                        }
                    } else {
                        //owner.setContentPane(new StudentForm(owner).getStudentPanel());
                        while(index != 4){
                            System.out.print("1. Listeaza cursuri\n2. Studentii de la curs\n3. Noteaza student:\n4. Back\nIntroduceti optiunea dorita:");
                            index = sc.nextInt();
                            if(index == 4){
                                break;
                            }
                            try {
                                Application.getInstance().login(new User(username, password));
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }

                            System.out.println(Application.getInstance().currentUser.menuStrategy.getAccountMenuOptionsConsola(index));
                        }
                    }
                }else if(index == 2){
                    //implementeaza register
                    System.out.println("Nume:");
                    String name = sc.next();
                    System.out.println("Prenume:");
                    String prenume = sc.next();
                    System.out.println("Username:");
                    String userName = sc.nextLine();
                    userName = sc.nextLine();
                    System.out.println("Password:");
                    String password = sc.next();
                    try{
                        Application.getInstance().register(name, prenume, userName, password);
                        System.out.println("Inregistrare reusita!");
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }else{
                    System.exit(0);
                }
            }
        }
    }
}
