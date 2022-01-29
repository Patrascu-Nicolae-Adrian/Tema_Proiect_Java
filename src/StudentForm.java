import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentForm {
    public StudentForm(JFrame owner, User user) {
        btnViewCourses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btnViewCourses){
                    try {
                        userUpdate(user);
                        year = Integer.parseInt(txtYear.getText());
                    JOptionPane.showMessageDialog(null, Application.getInstance().currentUser.menuStrategy.getAccountMenuOptions(1));
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "Nu ati introdus anul.");
                    }
                }
            }
        });


        btnViewGradesOfCourses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btnViewGradesOfCourses){
                    try {
                        userUpdate(user);
                        year = Integer.parseInt(txtYear.getText());
                        JOptionPane.showMessageDialog(null, Application.getInstance().currentUser.menuStrategy.getAccountMenuOptions(2));
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "Nu ati introdus anul.");
                    }
                }
            }
        });
        btnAverage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btnAverage){
                    try {
                        userUpdate(user);
                        year = Integer.parseInt(txtYear.getText());
                        JOptionPane.showMessageDialog(null, Application.getInstance().currentUser.menuStrategy.getAccountMenuOptions(3));
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "Nu ati introdus anul.");
                    }
                }
            }
        });
        btnRestanta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btnRestanta){
                    try {
                        userUpdate(user);
                        year = Integer.parseInt(txtYear.getText());
                        JOptionPane.showMessageDialog(null, Application.getInstance().currentUser.menuStrategy.getAccountMenuOptions(4));
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "Nu ati introdus anul.");
                    }
                }
            }
        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentPanel.setVisible(false);
                owner.setContentPane(new LoginForm(owner).getMainPanel());
            }
        });
    }
    public JPanel getStudentPanel() {
        return studentPanel;
    }
    public static int getYear(){
        return year;
    }
    public void userUpdate(User user){
        try {
            Application.getInstance().login(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private JPanel studentPanel;
    private JButton btnViewCourses;
    private JButton btnViewGradesOfCourses;
    private JButton btnAverage;
    private JButton btnRestanta;
    private JTextField txtYear;
    private JButton btnBack;
    private static int year;
}
