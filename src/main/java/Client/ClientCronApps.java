package Client;

public class ClientCronApps {
    public void run(){
        /**
         * Создаю оконное приложение, создавая обект SimpleGUI и обект TranslateServes для передачи обекта Cron на сервер
         */
        SimpleGUI app = new SimpleGUI();
        app.setVisible(true);
        app.setResizable(false);
        app.setLocationRelativeTo(null);
    }
}
