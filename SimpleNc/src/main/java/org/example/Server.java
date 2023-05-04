package org.example;

import org.apache.commons.cli.*;

import java.io.*;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();

        options.addOption("h","help",false,"print this message");
        Option listOption = Option.builder("l")
                .argName("port")
                .hasArg()
                .desc("서버 모드로 동작, 입력 받은 포트로 Listen")
                .build();
        options.addOption(listOption);

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter terminal = new BufferedWriter(new OutputStreamWriter(System.out));

        try {
            String command = console.readLine();
            String[] argu = command.split(" ");

            CommandLine cmd = parser.parse(options, argu);

            for (Option option : cmd.getOptions()) {
                if (option.getOpt().equals("h")) {
                    System.out.println("snc");
                    HelpFormatter formatter = new HelpFormatter();
                    formatter.printHelp("snc [option] [hostname] [port]", options);
                }
                else if(argu[0].equals("snc")) {
                    if (option.getOpt().equals("l")) {
                        String port2 = cmd.getOptionValue("l");
                        int port1 = Integer.parseInt(port2);
                        try (ServerSocket serverSocket = new ServerSocket(port1)) {
                            while(!Thread.interrupted()){
                                Handler handler = new Handler(serverSocket.accept());
                                System.out.println("연결 됨");
                                handler.start();
                            }
                        }catch(Exception e){}
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("명령어 잘못됨");
        }
    }
}