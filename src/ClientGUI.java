import javax.swing.*;

public class ClientGUI {
    private JTextArea jta = new JTextArea();
    private JTextField userID = new JTextField();
    private JTextField searchUser = new JTextField();
    private JFrame frame = new JFrame("Client");
    private JLabel userIDLabel = new JLabel("User ID");
    private JLabel sidLabel = new JLabel("Test");
    private JLabel studLabel = new JLabel("Test");
    private JLabel firstLabel = new JLabel("Test");
    private JLabel surLabel = new JLabel("Test");


    private JButton mExecute,mNext,mPrevious,mClear,mSearch,mLogout;

    public ClientGUI() {
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        userIDLabel.setBounds(50,20, 200,30);
        userID.setBounds(50, 50, 200, 30);
        mExecute = new JButton("Search");
        mExecute.setBounds(250, 50, 100, 30);
        mNext = new JButton("Next");
        mNext.setBounds(50, 100, 100, 30);
        mClear = new JButton("Clear");
        mClear.setBounds(50, 150, 100, 30);
        mPrevious = new JButton("Previous");
        mPrevious.setBounds(50, 200, 100, 30);
        mSearch = new JButton("Search");
        mSearch.setBounds(50, 250, 100, 30);
        searchUser.setBounds(170, 250, 200, 30);
        mLogout = new JButton("Logout");
        mLogout.setBounds(50, 300, 100, 30);


        jta.setBounds(400, 100, 200, 300);

        sidLabel.setBounds(200, 100, 200, 30);
        studLabel.setBounds(200, 120, 200, 30);
        firstLabel.setBounds(200, 140, 200, 30);
        surLabel.setBounds(200, 160, 200, 30);

        sidLabel.setVisible(false);
        surLabel.setVisible(false);
        firstLabel.setVisible(false);
        studLabel.setVisible(false);

        mClear.setVisible(false);
        mNext.setVisible(false);
        mPrevious.setVisible(false);
        mSearch.setVisible(false);
        searchUser.setVisible(false);
        mLogout.setVisible(false);

        frame.add(mLogout);
        frame.add(sidLabel);
        frame.add(studLabel);
        frame.add(firstLabel);
        frame.add(surLabel);

        frame.add(searchUser);
        frame.add(mSearch);
        frame.add(mNext);
        frame.add(mClear);
        frame.add(mPrevious);
        frame.add(jta);
        frame.add(mExecute);
        frame.add(userIDLabel);
        frame.add(userID);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public JTextField getSearchUser() {
        return searchUser;
    }

    public JButton getmSearch() {
        return mSearch;
    }

    public JLabel getSidLabel() {
        return sidLabel;
    }

    public JLabel getStudLabel() {
        return studLabel;
    }

    public JLabel getFirstLabel() {
        return firstLabel;
    }

    public JLabel getSurLabel() {
        return surLabel;
    }

    public JLabel getUserIDLabel() {
        return userIDLabel;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getmNext() {
        return mNext;
    }

    public JButton getmPrevious() {
        return mPrevious;
    }

    public JButton getmClear() {
        return mClear;
    }


    public JTextArea getJta() {
        return jta;
    }

    public JTextField getUserID() {
        return userID;
    }

    public JButton getmExecute() {
        return mExecute;
    }

}

