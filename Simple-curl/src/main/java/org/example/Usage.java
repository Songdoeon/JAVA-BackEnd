package org.example;

import org.apache.commons.cli.*;

import java.io.*;
import java.net.URL;
import java.util.Arrays;

public class Usage {
    public static String[] lastElementRemove(String[] srcArray) {
        return Arrays.copyOfRange(srcArray, 0, srcArray.length - 1);
    }
    public static void main(String[] args) throws Exception{
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();

        options.addOption("h","help",false,"print this message");
        options.addOption("v", false, "verbose, 요청, 응답 헤더를 출력합니다.");
        options.addOption("L", false, "서버의 응답이 30x 계열이면 다음 응답을 따라 갑니다.");
        options.addOption(Option.builder("H")
                .hasArg()
                .argName("line")
                .desc("임의의 헤더를 서버로 전송합니다.")
                .build());
        options.addOption(Option.builder("d")
                .hasArg()
                .argName("data")
                .desc("POST, PUT 등에 데이타를 전송합니다.")
                .build());
        options.addOption(Option.builder("X")
                .hasArg()
                .argName("command")
                .desc("사용할 method를 지정합니다. 지정되지 않은 경우 기본값을 GET 입니다.")
                .build());
        options.addOption(Option.builder("F")
                .hasArgs()
                .argName("name=content")
                .desc("multipart/form-data 를 구성하여 전송합니다. content 부분에 @filename 을 사용할 수 있습니다.")
                .valueSeparator('=')
                .build());

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter terminal = new BufferedWriter(new OutputStreamWriter(System.out));

        try {
            CommandLine cmd = parser.parse(options, args);
            URL url = new URL(args[args.length-1]);
            address add = new address(url);
            args = lastElementRemove(args);
            add.setMethod("GET");
            if(args[0].equals("scurl")){
                for(String arg : args){
                    System.out.println(arg);
                }
//                args = Arrays.stream(args)
//                        .filter(item -> !item.equals("scurl"))
//                        .toArray(String[]::new);
                for (Option option : cmd.getOptions()) {
                    if (option.getOpt().equals("h")) {
                        HelpFormatter formatter = new HelpFormatter();
                        formatter.printHelp("scurl [options] url", options);
                    }
                    if(option.getOpt().equals("H")){
                        String[] str = cmd.getOptionValue("H").split(" ");
                        str[0]=str[0].replaceAll(":","");
                        add.addRequest(str[0],str[1]);
                    }
                    if(option.getOpt().equals("X")){
                        add.setMethod(cmd.getOptionValue("X"));
                    }
                    if(option.getOpt().equals("L")){
                        add.redirect();
                    }
                    if(option.getOpt().equals("F")){
                        String file = cmd.getOptionValues("F")[1];
                        add.file(file);
                    }
                }
                if(cmd.hasOption("d")){
                    add.addBody(cmd.getOptionValue("d"));
                }
                if(cmd.hasOption("v")){
                    add.verbose();
                }
            }
            add.print();
        } catch (Exception e) {
            System.err.println("명령어 잘못됨");
        }
    }
}