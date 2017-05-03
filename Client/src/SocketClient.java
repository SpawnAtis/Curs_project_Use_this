import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient {

    private final static String address = "127.0.0.1"; // это IP-адрес компьютера, где исполняется наша серверная программа
    private final static int serverPort = 1234; // здесь обязательно нужно указать порт к которому привязывается сервер

    private static String userName = "vlad";
    public static Socket socket = null;




    public static void main( String[] args ) {

        try {

            try {
                InetAddress ipAddress = InetAddress.getByName( address ); // создаем объект который отображает вышеописанный IP-адрес
                socket = new Socket( ipAddress, serverPort ); // создаем сокет используя IP-адрес и порт сервера

                // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения
                ObjectOutputStream objectOutputStream = new ObjectOutputStream( outputStream );
                ObjectInputStream objectInputStream = new ObjectInputStream( inputStream );


                while (true) { // Бесконечный цикл
                    objectOutputStream.writeObject( new Message( userName, "Hello" ) ); // отсылаем введенную строку текста серверу.
                }

            } catch ( Exception e ) { e.printStackTrace(); }
        }
        finally {
            try {
                if ( socket != null ) { socket.close(); }
            } catch ( IOException e ) { e.printStackTrace(); }
        }
    }
}
