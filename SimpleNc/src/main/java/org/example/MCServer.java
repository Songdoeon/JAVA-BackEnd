package org.example;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;

public class MCServer {
    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();

        options.addOption("h",false,"도움말");
        options.addOption(Option.builder("b")
                .argName("arg")
                .hasArg()
                .desc("블랙리스트")
                .build());
        options.addOption(Option.builder("p")
                .hasArg()
                .argName("arg")
                .desc("서비스 포트")
                .build());

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter terminal = new BufferedWriter(new OutputStreamWriter(System.out));

        try {
            String command = console.readLine();
            String[] argu = command.split(" ");

            CommandLine cmd = parser.parse(options, argu);

            for (Option option : cmd.getOptions()) {
                if (option.getOpt().equals("h")) {
                    HelpFormatter formatter = new HelpFormatter();
                    formatter.printHelp("MCServer", options);
                }
                else if(argu[0].equals("java")) {
                    if (option.getOpt().equals("p")) {
                        String port2 = cmd.getOptionValue("p");
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