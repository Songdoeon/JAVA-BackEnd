package org.example;
import org.apache.commons.cli.*;

import java.io.*;
import java.net.Socket;

public class ClientUsage {
    public static void main(String[] args) {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter terminal = new BufferedWriter(new OutputStreamWriter(System.out));
        String host = null;
        String port = null;
        Client client = new Client();
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();

        options.addOption("h", false, "도움말");

        options.addOption(Option.builder("H")
                .hasArg()
                .argName("host")
                .desc("접속할 서버의 호스트 네임 또는 IP를 지정합니다.")
                .build());

        options.addOption(Option.builder("I")
                .hasArg()
                .argName("user id")
                .desc("채팅에서 사용할 사용자 아이디를 지정합니다.")
                .build());

        options.addOption(Option.builder("p")
                .hasArg()
                .argName("port")
                .desc("접속할 서버의 서비스 포트를 지정합니다.")
                .build());

        String command;
        String[] argu;
        try {
            command = console.readLine();
            argu = command.split(" ");

            CommandLine cmd = parser.parse(options, argu);

            if (argu[0].equals("client")) {
                for (Option option : cmd.getOptions()) {
                    HelpFormatter formatter;
                    if (option.getOpt().equals("h")) {
                        formatter = new HelpFormatter();
                        formatter.printHelp("MCClient", options);
                    }
                    if (option.getOpt().equals("H")) {
                        client.setIp(cmd.getOptionValue("H"));
                    }
                    if (option.getOpt().equals("I")) {
                        client.setUserId(cmd.getOptionValue("I"));
                    }
                    if (option.getOpt().equals("p")) {
                        client.setPort(Integer.parseInt(cmd.getOptionValue("p")));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (Socket socket = new Socket(client.getIp(), client.getPort())) {
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("접속 완료");
            Thread inputThread = new Thread(() -> {
                try {
                    while(!Thread.interrupted()) {
                        terminal.write(input.readLine());
                        terminal.newLine();
                        terminal.flush();
                    }
                } catch (IOException e) {
                    System.err.println("연결이 끊겼습니다.");
                }
            });
            inputThread.start();

            while(!Thread.interrupted()) {
                String line = console.readLine();
                output.write(line);
                output.newLine();
                output.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}