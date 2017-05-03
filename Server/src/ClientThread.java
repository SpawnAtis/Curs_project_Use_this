import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Timer;
import by.pokosenko.*;

public class ClientThread extends Thread {

    //private final static int DELAY = 30000;

    private Socket socket;
    private Message c;
    private String login;

    public ClientThread(Socket socket) {
        this.socket = socket;
        this.start();
    }

    public void run() {
        try {
            //потоки ввода, вывода для работы с сокетом
            final ObjectInputStream inputStream   = new ObjectInputStream(this.socket.getInputStream());
            final ObjectOutputStream outputStream = new ObjectOutputStream(this.socket.getOutputStream());

            this.c = (Message) inputStream.readObject();
            this.login = this.c.getLogin();
            System.out.println(login+this.c.getMessage());




        } catch (SocketException e) {
            //System.out.println(login + " disconnected!");
            //Server.getUserList().deleteUser(login);
            //broadcast(Server.getUserList().getClientsList(), new Message("Server-Bot", "The user " + login + " has been disconnect", getUserList().getUsers()));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




}
