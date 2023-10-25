package itis.meucci;

import java.io.*;
import java.net.*;

public class ServerClass{

    int port;
    ServerSocket server;
    Socket client;
    BufferedReader in;
    PrintWriter out;

    public ServerClass(int port) {

        this.port = port;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void connect() {

        try {
            System.out.println("Waiting for connection...");
            client = server.accept();

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);

            System.out.println("Client connesso: " + client.getInetAddress() + ":" + port);

        } catch (IOException e) {

        }
    }
    
    public void communicate() {

        try {
            double n1 = 0, n2 = 0;
            String operator = null, str = null;
               
            do{

                while (true) {
                    
                    out.println("Digitare il primo numero: "); 
                    try {
                        str = in.readLine();
                        n1 = Double.parseDouble(str);
                        break;
                    } catch (Exception e) {
                        out.println("Errore, digitare un numero valido;");
                    }
                }

                out.println("[ " + n1 + " ]");
                    
                while (true) {
                    
                    out.println("Digitare l'operatore: ");  
                    try {
                        str = in.readLine();

                        operator = str;
                        if(operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equalsIgnoreCase("x") || operator.equals(":") || operator.equals("/"))
                            break;
                        else
                            out.println("Errore, digitare un operatore valido");
                    } catch (Exception e) {
                        out.println("Errore, riprova;");
                    }
                }

                out.println("[ " + n1 + " " + operator + " ]");
                
                while (true) {
                    
                    out.println("Digitare il secondo numero: ");
                    try {
                        str = in.readLine();
                        n2 = Double.parseDouble(str);

                        if(operator.equals(":") || operator.equals("/") && n2 == 0)
                            out.println("Errore, impossibile dividere per 0;");
                        else
                            break;
                    } catch (Exception e) {
                        out.println("Errore, digitare un numero valido;");
                    }
                }

                out.println("[ " + n1 + " " + operator + " " + n2 + " ]=");

                if(operator.equals("+"))
                    out.println(n1 + n2);
                else if(operator.equals("-"))
                    out.println(n1 - n2);
                else if(operator.equals("*") || operator.equalsIgnoreCase("*"))
                    out.println(n1 * n2);
                else
                    if(operator.equals(":") || operator.equals("/"))
                        out.println(n1 / n2);
                
                    
            }while(!((str).equalsIgnoreCase("BYE")));

            closeConnection();

        } catch (Exception e) {

            System.out.println("Qualcosa e' andato storto!");
        }
    }

    public void closeConnection() {

        System.out.println("Closing connection...");
        try {
            in.close();
            out.close();
            client.close();
            server.close();
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }
       
}
