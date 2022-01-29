import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TeacherForm {
    public TeacherForm(JFrame owner,User user) {

        lblStudentName.setVisible(false);
        txtStudentName.setVisible(false);
        lblStudentGrade.setVisible(false);
        txtStudentGrade.setVisible(false);
        btnGrade.setVisible(false);
        btnListCourses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btnListCourses){
                    userUpdate(user);
                    JOptionPane.showMessageDialog(null, Application.getInstance().currentUser.menuStrategy.getAccountMenuOptions(1));
                }
            }
        });
        btnListStudentsOfCourses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btnListStudentsOfCourses){
                    userUpdate(user);
                    JOptionPane.showMessageDialog(null, Application.getInstance().currentUser.menuStrategy.getAccountMenuOptions(2));
                }
            }
        });
        btnGradeStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btnGradeStudent){
                    userUpdate(user);
                    lblStudentName.setVisible(true);
                    txtStudentName.setVisible(true);
                    lblStudentGrade.setVisible(true);
                    txtStudentGrade.setVisible(true);
                    btnGrade.setVisible(true);
                }
            }
        });
        btnGrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btnGrade){
                    try {
                        userUpdate(user);
                        name = txtStudentName.getText();
                        nota = Integer.parseInt(txtStudentGrade.getText());
                        JOptionPane.showMessageDialog(null, Application.getInstance().currentUser.menuStrategy.getAccountMenuOptions(3));
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null,"Nu ati introdus numele sau nota studentului.");
                    }

                }
            }
        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teacherPanel.setVisible(false);
                owner.setContentPane(new LoginForm(owner).getMainPanel());
            }
        });
    }

    public JPanel getTeacherPanel() {
        return teacherPanel;
    }

    public static String getName() {
        return name;
    }

    public static int getNota() {
        return nota;
    }

    public void userUpdate(User user){
        try {
            Application.getInstance().login(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private JPanel teacherPanel;
    private JButton btnListCourses;
    private JButton btnListStudentsOfCourses;
    private JButton btnGradeStudent;
    private JTextField txtStudentName;
    private JTextField txtStudentGrade;
    private JLabel lblStudentName;
    private JLabel lblStudentGrade;
    private JButton btnGrade;
    private JButton btnBack;
    private static String name;
    private static int nota;
}
