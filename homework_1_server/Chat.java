package homework_1_server;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Chat extends JFrame {
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;

    // Top panel
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField loginField = new JTextField("Login");
    private final JPasswordField passwordField = new JPasswordField("qwerty");
    //    private final JTextField passwordField = new JTextField("qwerty");
    private final JTextField ipField = new JTextField("127.0.0.1");
    private final JTextField portField = new JTextField("8081");
    private final JButton btnLogin = new JButton("Login");

    // Button panel
    private final JPanel panelButton = new JPanel(new BorderLayout());
    private final JTextField messageField = new JTextField("Test");
    private final JButton btnSend = new JButton("Send");

    // Central panel
    public static JTextArea log = new JTextArea();

    public Chat() {
        initGui();
        handlerBtnSend();
        handlerBtnLogin();
    }

    private void handlerBtnSend() { // TODO: 23.07.2024 Оптимизировать String -> StringBuilder
        btnSend.addActionListener(e -> {
            String oldMessage = log.getText();
            String newMessage = messageField.getText();
            String login = loginField.getText();

            String text = oldMessage + login + ": " + newMessage + "\n";


            if (ServerWindow.isServerWorking()) {
                log.setText(text);
                isFileWriter();
            }
        });
    }

    private void handlerBtnLogin() {
        btnLogin.addActionListener(e -> {
            new ServerWindow();
        });
    }

    private void initGui() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Chat");

        addTopPanel();
        addCenterPanel();
        addButtonPanel();

        setVisible(true);
    }

    private void addTopPanel() {
        panelTop.add(loginField);
        panelTop.add(passwordField);
        panelTop.add(ipField);
        panelTop.add(portField);
        panelTop.add(btnLogin);

        centerTextOfTopPanel();

        add(panelTop, BorderLayout.NORTH);
    }

    private void centerTextOfTopPanel() {
        loginField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        ipField.setHorizontalAlignment(SwingConstants.CENTER);
        portField.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void addCenterPanel() {
        log.setEditable(true); // Разрешение на редактирование
        JScrollPane scrollPane = new JScrollPane(log);
        add(scrollPane);
    }

    private void addButtonPanel() {
        panelButton.add(messageField, BorderLayout.CENTER);
        panelButton.add(btnSend, BorderLayout.EAST);
        add(panelButton, BorderLayout.SOUTH);
    }

    public static String isFileReader() {
        try {
            return fileReader();
        } catch (FileNotFoundException e) {
            return "";
        }
    }

    private static String fileReader() throws FileNotFoundException {
        String separator = File.separator;
        String path = "src"
                + separator + "homework_1_server"
                + separator + "log";

        File file = new File(path);
        Scanner scanner = new Scanner(file);
        StringBuilder text = new StringBuilder();

        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine()).append("\n");
        }

        scanner.close();

        return text.toString();
    }

    private void isFileWriter() {
        try {
            fileWriter();
        } catch (FileNotFoundException ignored) {
        }
    }

    private void fileWriter() throws FileNotFoundException {
        String separator = File.separator;
        String path = "src"
                + separator + "homework_1_server"
                + separator + "log";

        File file = new File(path);
        PrintWriter printWriter = new PrintWriter(file);

        String text = log.getText();
        printWriter.println(text);

        printWriter.close();
    }
}
