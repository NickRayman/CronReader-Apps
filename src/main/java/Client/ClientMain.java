package Client;

/**
 * Создаю класс в котором будет производится создания и вызовы функций;
 */
public class ClientMain {
    public static void main(String[] args) throws Exception {
        ClientCronApps nick = new ClientCronApps();
        nick.run();
    }
}
