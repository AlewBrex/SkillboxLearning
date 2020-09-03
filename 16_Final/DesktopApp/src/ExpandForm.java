import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class ExpandForm
{
    private JPanel expandPanel;
    private JTextField fullName;
    private JButton expandButton;

    public ExpandForm()
    {
        expandButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Pattern pattern = Pattern.compile("\\D+\\s\\D+");
                if (pattern.matcher(fullName.getText()).find())
                {
                    expandPanel.setVisible(false);
                    CollapseForm collapseForm = new CollapseForm();
                    collapseForm.getMainPanel();
                }
                else
                {
                    JOptionPane.showMessageDialog(
                            expandPanel,
                            "The field is not fully filled",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    public JPanel getExpandPanel()
    {
        return expandPanel;
    }

    public void setFullName(String strng)
    {
        fullName.setText(strng);
    }
}
