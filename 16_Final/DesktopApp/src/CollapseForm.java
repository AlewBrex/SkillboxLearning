import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CollapseForm
{
    private ExpandForm expandForm = new ExpandForm();
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

    public CollapseForm()
    {
        collapseButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
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
                    expandForm.setFullName(surNameField.getText() + "\\s"
                            + firstNameField.getText() + "\\s"
                    + middleNameField.getText());
                }
            }
        });
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }
}