/*
 * @author Micheal Dunne
 * */

import com.mysql.cj.util.StringUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


public class Client {
    private DataOutputStream toServer;

    private DataInputStream fromServer;
    private ClientGUI clientGUI;
    private Students students;
    private ArrayList<Students> studentsArrayList = new ArrayList<>();
    private int globalcount = 0;

    public static void main(String[] args){
        new Client();
    }

    private Client() {
//      Initalizes Client GUI
        clientGUI = new ClientGUI();
        try{
//            Opens Socket
            Socket socket = new Socket("localhost",8000);
//            Allows to receive information from server
            fromServer = new DataInputStream(socket.getInputStream());
//          Allows to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());

//          When the Execute button is pressed
            clientGUI.getmExecute().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    validUser();
                }
            });
//          Reset the Data and clears teh Text Area
            clientGUI.getmClear().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearArea();
                }
            });
//          Gets the next Student
            clientGUI.getmNext().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nextStudent();
                }
            });
//          Gets the previous student
            clientGUI.getmPrevious().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    previousStudent();
                }
            });
//          Search for a student
            clientGUI.getmSearch().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchStudents();
                }
            });

        }catch (Exception e){
            clientGUI.getJta().append(e.toString() + '\n');
        }

    }

    private void clearArea() {
        try {
//          Clear the students and enters the default entries.
            studentsArrayList.clear();
            toServer.writeUTF("nextstudent");
            String read = fromServer.readUTF();
            String[] items = read.split("\\s*,\\s*");
            for(int i = 0;i < items.length;i+=4) {
                students = new Students(Integer.parseInt(items[i]), Integer.parseInt(items[i+1]), items[i+2], items[i+3]);
                studentsArrayList.add(students);
            }
//          Resets counter to 0
            globalcount = 0;
            Students nextvalue = studentsArrayList.get(globalcount);
            globalcount++;
            readStudents(nextvalue);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        clientGUI.getJta().setText("");
        clientGUI.getSearchUser().setText("");
    }

    private void searchStudents(){
        try {
//           Check to see if there is text in teh search user box.
            if (!StringUtils.isNullOrEmpty(clientGUI.getSearchUser().getText().trim())) {
                toServer.writeUTF("search," + clientGUI.getSearchUser().getText().trim());
                String read = fromServer.readUTF();
                if (!read.equals("")) {
//                   Clears students and adds in the searched students
                    studentsArrayList.clear();
                    String[] items = read.split("\\s*,\\s*");
                    for (int i = 0; i < items.length; i += 4) {
                        students = new Students(Integer.parseInt(items[i]), Integer.parseInt(items[i + 1]), items[i + 2], items[i + 3]);
                        studentsArrayList.add(students);
                    }
//                  Resets the global count sets the value to the searched value.
                    globalcount = 0;
                    Students nextvalue = studentsArrayList.get(globalcount);
                    globalcount++;
                    readStudents(nextvalue);
                } else {
                    clientGUI.getJta().append("No Student Found \n");
                }
            }else {
                clientGUI.getJta().append("Search cannot be blank  \n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readStudents(Students nextvalue){
            clientGUI.getStudLabel().setVisible(true);
            clientGUI.getSidLabel().setVisible(true);
            clientGUI.getFirstLabel().setVisible(true);
            clientGUI.getSurLabel().setVisible(true);
            clientGUI.getSidLabel().setText("SID: " + nextvalue.getSid());
            clientGUI.getSurLabel().setText("Surname " + nextvalue.getSurname());
            clientGUI.getFirstLabel().setText("Firstname " + nextvalue.getFirstname());
            clientGUI.getStudLabel().setText("Student ID " + nextvalue.getStudid());
            clientGUI.getJta().append("Found User " + nextvalue.getFirstname() + "\n");
        }

    private void previousStudent(){
        try {
            if (globalcount < 0){
                globalcount = 0;
            }
//          Goes back to a previous value count.
            globalcount--;
            Students previousvalue = studentsArrayList.get(globalcount);
            readStudents(previousvalue);
        }catch (Exception e){
            if (e.getMessage().contains("out of bounds for length")){
                clientGUI.getJta().append("No more Students Available \n");
            }else {
                clientGUI.getJta().append("404 error");
            }
        }

    }

    private void nextStudent(){
        try {
            if (globalcount < 0){
                globalcount = 0;
            }
//          Gets the next value in the list
            Students nextvalue = studentsArrayList.get(globalcount);
            globalcount++;
            readStudents(nextvalue);
        }catch (Exception e){
            if (e.getMessage().contains("out of bounds for length")){
                clientGUI.getJta().append("No more Students Available \n");
            }else {
                clientGUI.getJta().append("404 error");
            }
        }
    }

    private void validUser(){
        try {
//          Checks to see if text is a numerical value
            if (clientGUI.getUserID().getText().trim().matches("[0-9]+")) {
                String checkuser = "validuser," + clientGUI.getUserID().getText().trim();
                User user = new User();
//              Writes to the server
                toServer.writeUTF(checkuser);
                toServer.flush();

                clientGUI.getJta().append("Server Processing...\n");
//              Read from the server
                String readutf = fromServer.readUTF();
//              Makes sure that the request back is not blank
                if (!readutf.equals("")) {
                    String[] items = readutf.split("\\s*,\\s*");

                    user.setUid(Integer.parseInt(items[1]));
                    user.setUname(items[2]);
                    clientGUI.getJta().append("User is Valid. Welcome " + user.getUname() + "\n");
                    clientGUI.getmNext().setVisible(true);
                    clientGUI.getmClear().setVisible(true);
                    clientGUI.getmPrevious().setVisible(true);
                    clientGUI.getmExecute().setVisible(false);
                    clientGUI.getmSearch().setVisible(true);
                    clientGUI.getSearchUser().setVisible(true);
                    clientGUI.getSearchLabel().setVisible(true);
                    clientGUI.getUserID().setVisible(false);
                    clientGUI.getUserIDLabel().setText("Welcome " + user.getUname());

                    toServer.writeUTF("nextstudent");

                    String read = fromServer.readUTF();

                    String[] splitStrings = read.split("\\s*,\\s*");
                    for (int i = 0; i < splitStrings.length; i += 4) {
                        students = new Students(Integer.parseInt(splitStrings[i]), Integer.parseInt(splitStrings[i + 1]), splitStrings[i + 2], splitStrings[i + 3]);
                        studentsArrayList.add(students);
                    }
                }else {
//                    If no results set it to be invalid userid
                    clientGUI.getJta().append("Invalid user ID \n");
                }
            }else{
                clientGUI.getJta().append("UID must be number\n");
            }
        }
        catch (IOException ex) {
            if (ex.getMessage().contains("For input string")){
                clientGUI.getJta().append("Must be number\n");
            }
            System.err.println(ex);
        }
    }
}
