import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm {
    private JPanel registerPanel;
    private JTextField txtName;
    private JTextField txtPrenume;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnRegister;
    private JButton btnBack;

    public RegisterForm(JFrame owner) {
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Application.getInstance().register(txtName.getText(), txtPrenume.getText(), txtUsername.getText(), new String(txtPassword.getPassword()));
                    JOptionPane.showMessageDialog(null, "Register successfully!");
                    registerPanel.setVisible(false);
                    owner.setContentPane(new LoginForm(owner).getMainPanel());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    registerPanel.setVisible(false);
                    owner.setContentPane(new LoginForm(owner).getMainPanel());
                }

            }
        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerPanel.setVisible(false);
                owner.setContentPane(new LoginForm(owner).getMainPanel());
            }
        });
    }

    public JPanel getRegisterPanel() {
        return registerPanel;
    }
}
