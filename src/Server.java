/*
* @author Micheal Dunne
* */

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server extends JFrame {

	private JTextArea jta = new JTextArea();

	private ServerSocket serverSocket;

	public static void main(String[] args) {
		new Server();
	}
	
	public Server() {
//		Sets the frame Layout and adds the text area
		setLayout(new BorderLayout());
		add(new JScrollPane(jta),BorderLayout.CENTER);
		
		setTitle("Server");
		setSize(500,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		try {
			serverSocket = new ServerSocket(8000);
			
			jta.append("Server started at " + new Date() + '\n');


			while(true) {
				Socket socket = serverSocket.accept();
				ClientAssist clientAssist = new ClientAssist(socket,jta);
				clientAssist.start();
			}
		}catch(IOException | SQLException ex) {
			System.err.println(ex);
		}
	}
}


class ClientAssist extends Thread {

	private Connections connections = new Connections();
	private DataOutputStream outputToClient;
	private DataInputStream inputFromClient;
	private JTextArea jTextArea;
	private Socket socket;

	public ClientAssist(Socket socket, JTextArea jta) throws SQLException, IOException {
		this.jTextArea = jta;
		this.socket = socket;
		this.outputToClient = new DataOutputStream(socket.getOutputStream());
		this.inputFromClient = new DataInputStream(socket.getInputStream());
	}

	public void run() {
		while(true){
			try {
//			    Reads from the client
				String inputStream = inputFromClient.readUTF();
				jTextArea.append(new Date() + " Reading From Client \n");

				String[] items = inputStream.split("\\s*,\\s*");
//              Checks is item is valid
				if (items[0].equals("validuser")){
					jTextArea.append(new Date() + " Checking Valid User \n");
//					Prepared Statement
					PreparedStatement preparedStatement = connections.getConnection().prepareStatement("SELECT * FROM users WHERE UID=?");
					preparedStatement.setInt(1,Integer.parseInt(items[1]));
//                  Result Set
					ResultSet resultsetUser = preparedStatement.executeQuery();
					String combineduser = "";
					while (resultsetUser.next()) {
						User user = new User(resultsetUser.getInt("UID"), resultsetUser.getString("UNAME"));
						combineduser += "validuser," + user.getUid() + "," + user.getUname();
						jTextArea.append(new Date() + " Valid User: " + user.getUname() + "\n");
					}
					if (combineduser.equals("")){
						jTextArea.append(new Date() + "No User with ID " + items[1] + "\n");
					}
					outputToClient.writeUTF(combineduser);

				}else if (items[0].equals("nextstudent")){
					jTextArea.append(new Date() + " Getting Students \n");
//					Prepared Statement
					PreparedStatement preparedStmt = connections.getConnection().prepareStatement("SELECT * FROM students");
					ResultSet studentResult = preparedStmt.executeQuery();
					combiningString(studentResult);
					jTextArea.append(new Date() + " Got Students \n");

				}else if (items[0].equals("search")){
					jTextArea.append(new Date() + " Searching for Student \n");
//					Prepared statement
					PreparedStatement preparedStmtStudent = connections.getConnection().prepareStatement("SELECT * FROM students WHERE SNAME=?");
					preparedStmtStudent.setString(1,items[1]);
					ResultSet resultSet = preparedStmtStudent.executeQuery();
					combiningString(resultSet);
				}else {
                    jTextArea.append(new Date() + " 404 error \n");
                }
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void combiningString(ResultSet resultSet) throws SQLException, IOException {
		String combinedString = "";
//		Adds students to pass back to client.
		while (resultSet.next()){
			Students students = new Students(resultSet.getInt("SID"),resultSet.getInt("STUD_ID"),resultSet.getString("FNAME"),resultSet.getString("SNAME"));
			combinedString += students.getSid() + "," + students.getStudid() + "," + students.getFirstname() + "," + students.getSurname()  + ",";
		}
		if(combinedString.equals("")){
			jTextArea.append(new Date() + " No Students Found \n");
		}else {
			jTextArea.append(new Date() + " Students Found \n");
		}
		outputToClient.writeUTF(combinedString);
	}
}

