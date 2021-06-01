package Client;
import java.io.*;
import java.net.Socket;

/**
 * Создаю класс в котором будет производится создания и возовы функций;
 */
public class Client {
    /**
     * Сокет для общения
     */
    protected static Socket clientSocket;
    /**
     * поток чтения из сокета
     */
    protected static BufferedReader in;
    /**
     * поток записи в сокет
     */
    protected static BufferedWriter out;

    public static void main(String[] args) throws Exception {
        SimpleGUI app = new SimpleGUI();
        app.setVisible(true);
        app.setResizable(false);
        app.setLocationRelativeTo(null);

    }
}
