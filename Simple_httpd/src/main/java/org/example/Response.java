package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

@SuppressWarnings({"checkstyle:Indentation", "checkstyle:LeftCurly", "checkstyle:EmptyLineSeparator", "checkstyle:WhitespaceAround"})
public class Response extends Handler {
    String code;

    public Response(Socket socket) {
        super(socket);
    }

    BufferedWriter output;
    {
        try{
        output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    } catch(IOException e) {
        throw new IllegalArgumentException(e);
    }

}
    public void res200(StringBuilder filelist) {
        this.code = "HTTP/1.1 200 OK";
        String responseData = "HTTP/1.1 200 OK\r\n" +
                "Server: \r\n" +
                "Content-type: text/html\r\n\r\n" +
                "<HTML>" +
                "<HEAD><TITLE>200</TITLE></HEAD>" +
                "<BODY>Filelist" +
                "<br>"+filelist+
                "</BODY></HTML>";
        try {
            output.write(responseData);
            output.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    @SuppressWarnings({"checkstyle:OperatorWrap", "checkstyle:WhitespaceAround"})
    public void fileSuccess() {
        this.code = "HTTP/1.1 200 OK";
        String responseData = "HTTP/1.1 200 OK\r\n" +
                "Server: \r\n" +
                "Content-type: text/html\r\n\r\n" +
                "<HTML>" +
                "<HEAD><TITLE>200</TITLE></HEAD>" +
                "<BODY>file uploading Success" +
                "<br>"+
                "</BODY></HTML>";
        try {
            output.write(responseData);
            output.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    @SuppressWarnings("checkstyle:OperatorWrap")
    public void empty() {
        this.code = "HTTP/1.1 404 Not Found";
        String responseData = "HTTP/1.1 404 Not Found\r\n" +
                "Server: \r\n" +
                "Content-type: text/html\r\n\r\n" +
                "<HTML>" +
                "<HEAD><TITLE>404 Not Found</TITLE></HEAD>" +
                "<BODY>404 Not Found" +
                "</BODY></HTML>";
        try {
            output.write(responseData);
            output.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    @SuppressWarnings("checkstyle:OperatorWrap")
    public void conflict() {
        this.code = "HTTP/1.1 409 Conflict";
        String responseData = "HTTP/1.1 409 Conflict\r\n" +
                "Server: \r\n" +
                "Content-type: text/html\r\n\r\n" +
                "<HTML>" +
                "<HEAD><TITLE>409 Conflict</TITLE></HEAD>" +
                "<BODY>409 Conflict" +
                "</BODY></HTML>";
        try {
            output.write(responseData);
            output.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    @SuppressWarnings("checkstyle:OperatorWrap")
    public void forbidden() {
        this.code = "HTTP/1.1 403 Forbidden";
        String responseData = "HTTP/1.1 403 Forbidden\r\n" +
                "Server: \r\n" +
                "Content-type: text/html\r\n\r\n" +
                "<HTML>" +
                "<HEAD><TITLE>403 Forbidden</TITLE></HEAD>" +
                "<BODY>403 Forbidden" +
                "</BODY></HTML>";
        try {
            output.write(responseData);
            output.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    @SuppressWarnings("checkstyle:OperatorWrap")
    public void notAllowed() {
        this.code = "HTTP/1.1 405 Method Not Allowed";
        String responseData = "HTTP/1.1 405 Method Not Allowed\r\n" +
                "Server: \r\n" +
                "Content-type: text/html\r\n\r\n" +
                "<HTML>" +
                "<HEAD><TITLE>405 Method Not Allowed</TITLE></HEAD>" +
                "<BODY>405 Method Not Allowed" +
                "</BODY></HTML>";
        try {
            output.write(responseData);
            output.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    @SuppressWarnings("checkstyle:OperatorWrap")
    public void delete() {
        this.code = "HTTP/1.1 204 No content";
        String responseData = "HTTP/1.1 204 No content\r\n" +
                "Server: \r\n" +
                "Content-type: text/html\r\n\r\n" +
                "<HTML>" +
                "<HEAD><TITLE>204 No content</TITLE></HEAD>" +
                "<BODY>204 No content" +
                "</BODY></HTML>";
        try {
            output.write(responseData);
            output.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

