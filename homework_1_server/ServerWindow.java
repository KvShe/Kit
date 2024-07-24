package homework_1_server;

import javax.swing.*;
import java.awt.*;

public class ServerWindow extends JFrame {
    private static final int WIGHT = 400;
    private static final int HEIGHT = 300;
    private final JButton btnStartServer = new JButton("Start");
    private final JButton btnStopServer = new JButton("Stop");
    private static boolean isServerWorking;


    public ServerWindow() {
        initServer();

        handlerBtnStartServer();
        handlerBtnStopServer();
    }

    private void initServer() {
        setSize(WIGHT, HEIGHT);
        setLocationRelativeTo(null);

        setTitle("Chat server");
        setAlwaysOnTop(true);
        setResizable(false);
        JPanel panelBtm = new JPanel(new GridLayout(1, 2));
        panelBtm.add(btnStartServer);
        panelBtm.add(btnStopServer);
        add(panelBtm);

        setVisible(true);
    }

    private void handlerBtnStartServer() {
        btnStartServer.addActionListener(e -> {
            if (isServerWorking()) {
                System.out.println("Сервер уже запущен");
                return;
            }

            isServerWorking = true;

            Chat.log.setText(Chat.isFileReader());
            System.out.println("Server started. Status: " + isServerWorking());
        });
    }

    private void handlerBtnStopServer() {
        btnStopServer.addActionListener(e -> {
            if (!isServerWorking()) {
                System.out.println("Сервер уже остановлен");
                return;
            }

            isServerWorking = false;
            System.out.println("Server stopped. Status: " + isServerWorking());
        });
    }

    public static boolean isServerWorking() {
        return isServerWorking;
    }
}
