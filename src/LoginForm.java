import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginForm {
    public LoginForm(JFrame owner) {
        this.owner = owner;
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( e.getSource() == btnLogin) {
                    try {
                        User user = new User(txtUsername.getText(), new String(txtPassword.getPassword()));
                        Application.getInstance().login(user);
                        JOptionPane.showMessageDialog(null, "Login successfully!");
                        mainPanel.setVisible(false);
                        if(Application.getInstance().currentUser.menuStrategy.getAccountType() == UserAccountType.TEACHER) {
                            owner.setContentPane(new TeacherForm(owner, user).getTeacherPanel());

                        }else{
                            owner.setContentPane(new StudentForm(owner, user).getStudentPanel());
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btnRegister){
                    mainPanel.setVisible(false);
                    owner.setContentPane(new RegisterForm(owner).getRegisterPanel());
                }
            }
        });
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }

    private JPanel mainPanel;
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegister;
    private JFrame owner;
}
