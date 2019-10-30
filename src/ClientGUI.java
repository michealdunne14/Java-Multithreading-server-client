/*
 * @author Micheal Dunne
 * */

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
    private JLabel searchLabel = new JLabel("Search By Surname");


    private JButton mExecute,mNext,mPrevious,mClear,mSearch;

    public ClientGUI() {
//       Sets the Frame
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      Sets the user id label
        userIDLabel.setBounds(50,20, 200,30);
//      Sets the user id button
        userID.setBounds(50, 50, 200, 30);
//      Sets the search button for the userid
        mExecute = new JButton("Search");
        mExecute.setBounds(250, 50, 100, 30);
//      Next button
        mNext = new JButton("Next");
        mNext.setBounds(50, 100, 100, 30);
//      Clear Button
        mClear = new JButton("Clear");
        mClear.setBounds(50, 150, 100, 30);
//      Previous button
        mPrevious = new JButton("Previous");
        mPrevious.setBounds(50, 200, 100, 30);
//      Search for a student
        mSearch = new JButton("Search");
        mSearch.setBounds(50, 250, 100, 30);
//      Search Label
        searchLabel.setBounds(170, 220, 200, 30);
//      Search user
        searchUser.setBounds(170, 250, 200, 30);
//      Sets up the text area
        jta.setBounds(400, 100, 200, 300);

//      Labels
        sidLabel.setBounds(200, 100, 200, 30);
        studLabel.setBounds(200, 120, 200, 30);
        firstLabel.setBounds(200, 140, 200, 30);
        surLabel.setBounds(200, 160, 200, 30);

//      Sets Visible to false
        sidLabel.setVisible(false);
        surLabel.setVisible(false);
        firstLabel.setVisible(false);
        studLabel.setVisible(false);
        searchLabel.setVisible(false);

        mClear.setVisible(false);
        mNext.setVisible(false);
        mPrevious.setVisible(false);
        mSearch.setVisible(false);
        searchUser.setVisible(false);

//      Adds a frame
        frame.add(searchLabel);
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

    public JLabel getSearchLabel() {
        return searchLabel;
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

