import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

public class BankAccountFrame extends JFrame {
    public static void main(String[] args) {
        new BankAccountFrame("NR");
    }

    private String theCurrentUser;
    private String userMoney;
    private JLabel errorLabel;
    private JTextField inputField;
    private JTextArea currentMoney;

    BankAccountFrame(String theCurrentUser) {//Frame initialisation, layout and functionality
        this.theCurrentUser = theCurrentUser;
        try {
            readPersonalPortfolio();
            setCurrentMoney(userMoney);
        } catch (IOException e) {
            errorLabel.setText("Unable to Read Current Money File, File May Not Exist.");
        }
        setSize(550, 330);

        Font myFieldFont = new Font("Century Gothic", Font.BOLD, 14);
        Font myTextFont = new Font("Century Gothic", Font.BOLD, 16);
        Font myNextFont = new Font("HelveticaNeue-Light", Font.ITALIC, 18);

        Font myButtonFont = new Font("Tahoma", Font.BOLD, 16);
        Color myBlueColor = new Color(59, 69, 182);
        Color priceColor = new Color(38, 200, 191);

        JLabel userLabel = new JLabel("Current User: " + theCurrentUser);
        userLabel.setFont(myNextFont);
        userLabel.setForeground(Color.BLACK);

        JLabel userMoneyLabel = new JLabel("Current Money: $");
        userMoneyLabel.setFont(myNextFont);
        userMoneyLabel.setForeground(Color.BLACK);

        JLabel inputLabel = new JLabel("Deposit/Withdraw Amount: $");
        inputLabel.setFont(myNextFont);
        inputLabel.setForeground(myBlueColor);

        inputField = new JTextField(20);
        inputField.setFont(myFieldFont);

        currentMoney = new JTextArea();
        currentMoney.append(String.valueOf(userMoney));
        currentMoney.setColumns(5);
        currentMoney.setFont(myNextFont);
        currentMoney.setEditable(false);

        JLabel helpLabel = new JLabel("<html><font color = purple>Bank Account</font><br/>Please Enter An Amount to Deposit/Withdraw<br/>" +
                "You Can Only Deposit/Withdraw 1 - 10,000 at once<br/>" +
                "Ensure Input is Between 1-10,000<br> Your Current Money is Not Higher Than 100,000</html>");
        helpLabel.setForeground(myBlueColor);
        helpLabel.setFont(myTextFont);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(myFieldFont);

        JButton depositBtn = new JButton("Deposit");
        depositBtn.setBackground(myBlueColor);
        depositBtn.setForeground(priceColor);
        depositBtn.setFont(myButtonFont);

        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBackground(myBlueColor);
        withdrawBtn.setForeground(Color.white);
        withdrawBtn.setFont(myButtonFont);

        JPanel userPanel = new JPanel();
        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();
        JPanel panelThree = new JPanel();
        JPanel panelFour = new JPanel();
        JPanel panelFive = new JPanel();
        JPanel panelSix = new JPanel();

        userPanel.add(userLabel);
        userPanel.add(userMoneyLabel);
        userPanel.add(currentMoney);
        panelOne.add(inputLabel);
        panelOne.add(inputField);
        panelFour.add(depositBtn);
        panelFour.add(withdrawBtn);
        panelFive.add(helpLabel);
        panelSix.add(errorLabel);

        add(userPanel);
        add(panelOne);
        add(panelTwo);
        add(panelThree);
        add(panelFour);
        add(panelFive);
        add(panelSix);

        setLayout(new FlowLayout());
        setTitle("Financial Portfolio Manager Bank Account");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        //Individual action listeners for each button
        depositBtn.addActionListener(new BankAccountFrame.ButtonHandler(this, 1));
        withdrawBtn.addActionListener(new BankAccountFrame.ButtonHandler(this, 2));
    }

    public class ButtonHandler implements ActionListener { //Implements the action listener
        BankAccountFrame theApp;
        int action;

        ButtonHandler(BankAccountFrame app, int action) {
            this.theApp = app;
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputField.getText().trim();
            if (action == 1) {//Deposit money to bank
                try {
                    double inputDouble = Double.parseDouble(input);
                    String moneyArea = currentMoney.getText();
                    double thecurrentmoney = Double.parseDouble(moneyArea);
                    if (inputDouble >= 1 && inputDouble < 10000 && thecurrentmoney < 100000) {
                        depositMoney(inputDouble, thecurrentmoney);
                    } else {
                        errorLabel.setText("<html>Please ensure Amount is Between 1-10,000<br/>Please ensure Current Money is less than 100,000</html>");
                    }
                    readPersonalPortfolio();

                } catch (Exception el) {
                    errorLabel.setText("Please Ensure That Input is Number Between 1 - 10,000");
                }
            }
            if (action == 2) {//Withdraw from bank
                try {
                    double inputDouble = Double.parseDouble(input);
                    String moneyArea = currentMoney.getText();
                    double thecurrentmoney = Double.parseDouble(moneyArea);
                    if (inputDouble > 1 && inputDouble < 10000 && thecurrentmoney > 0) {
                        withdrawMoney(inputDouble, thecurrentmoney);
                    } else {
                        errorLabel.setText("<html>Please ensure Amount is Between 1-10,000<br/>Please ensure Current Money is more than 0</html>");
                    }
                } catch (Exception el) {
                    errorLabel.setText("<html>Please ensure Amount is Between 1-10,000<br/>Please ensure Current Money is more than 0</html>");
                }
            }
        }
    }

    private void readPersonalPortfolio() throws IOException {
        File file = new File(".//UserPortfolios/" + getTheCurrentUser() + "-BANK.csv");

        if (file.exists()) {
            CSVReader reader = new CSVReader(new FileReader(file));
            String[] line = reader.readNext();
            while (line != null) {
                System.out.println();
                userMoney = line[1].trim().replaceAll("\"", "");
                line = reader.readNext();
            }
            setCurrentMoney(userMoney);

        } else if (!file.exists()) {
            String startingcash = "10000";
            String[] uservalues = new String[]{getTheCurrentUser(), startingcash};

            try {
                writeToCsv(file, uservalues);
            } catch (Exception el) {
                errorLabel.setText("unable to write data to file");
            }
            readPersonalPortfolio();
        }
    }

    private void depositMoney(double deposit, double userMoney) {
        int choice = JOptionPane.showConfirmDialog(this,
                "Confirm, You Are Depositing: $" + deposit + " in to your Bank Account");
        if (choice == JOptionPane.YES_OPTION) {
            try {
                userMoney = userMoney + deposit;
                adjustUserMoney(userMoney);

                errorLabel.setText("<html>Thankyou For Your Transaction!<br></html");

            } catch (Exception el) {
                errorLabel.setText("You have cancelled the deposit");
            }
        } else {
            errorLabel.setText("You have cancelled the deposit");
        }
    }

    private void withdrawMoney(double withdraw, double userMoney) {
        int choice = JOptionPane.showConfirmDialog(this,
                "Confirm, You Are Withdrawing: $" + withdraw + " from your Bank Account to Your Vault");

        if (choice == JOptionPane.YES_OPTION) {
            try {
                userMoney = userMoney - withdraw;
                adjustUserMoney(userMoney);

            } catch (Exception el) {

                errorLabel.setText("Unable to withdraw funds");
            }
        }
    }

    private void adjustUserMoney(double userMoney) {
        try {
            String str = String.valueOf(String.format("%.2f", userMoney));
            setCurrentMoney(str);
            System.out.println();
            System.out.println();
            this.currentMoney.setText(str);
            String[] depositupdate = new String[]{theCurrentUser, str};
            System.out.println();
            System.out.println();
            File file = new File(".//UserPortfolios/" + getTheCurrentUser() + "-BANK.csv");
            writeToCsv(file, depositupdate);

        } catch (Exception el) {
            errorLabel.setText("Unable to Adjust User Money Error");
        }
    }

    private String getTheCurrentUser() {
        return theCurrentUser;

    }

    private void setCurrentMoney(String newUserMoney) {
        this.userMoney = newUserMoney;
    }


    private void writeToCsv(File file, String[] details) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        CSVWriter csvWriter = new CSVWriter(fileWriter);
        System.out.println();
        csvWriter.writeAll(Collections.singleton(details));
        System.out.println();
        fileWriter.flush();
        csvWriter.close();
    }
}

