import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CollapseForm
{
    private JPanel mainPanel;
    private JLabel surName;
    private JTextField surNameField;
    private JLabel firstName;
    private JLabel middleName;
    private JTextField firstNameField;
    private JTextField middleNameField;
    private JButton collapseButton;
    private JLabel note;
    private JTextField newText;
    private JFrame jFrame;

    public CollapseForm(JFrame frame)
    {
        collapseButton.addActionListener(e->
        {
            if (surNameField.getText().isEmpty() ||
            firstNameField.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(
                        mainPanel,
                        "Not all fields are filled",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                mainPanel.setVisible(false);
                ExpandForm expandForm = new ExpandForm(frame);
                expandForm.setFullName(surNameField.getText() + " "
                        + firstNameField.getText() + " "
                + middleNameField.getText());
                jFrame.remove(mainPanel);
                jFrame.add(expandForm.getExpandPanel());
            }
        });
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }
}