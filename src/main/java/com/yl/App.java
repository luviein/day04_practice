package com.yl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws NumberFormatException, IOException
    {
        String fullFilePath = args[0];
        String port = args[1];
        

        //check if full Path Exists
        File cookieFile = new File(fullFilePath);

        if(!cookieFile.exists()){
            System.out.println("Cookie File dont exist.");
            System.exit(0);
        }
        Cookie cookie = new Cookie();

        //establish server
        ServerSocket server = new ServerSocket(Integer.parseInt(port));
        Socket socket = server.accept();

        String msgReceived = ""; //store data sent from client

        try(InputStream is = socket.getInputStream()){
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);
            

            try(OutputStream os = socket.getOutputStream()){
                BufferedOutputStream bos = new BufferedOutputStream(os);
                DataOutputStream dos = new DataOutputStream(bos);


                while(!msgReceived.equals("close")){
                    msgReceived = dis.readUTF();
                    if(msgReceived.equalsIgnoreCase("get-cookie")){
                        String randomCookie = cookie.getRandomCookie();
                        dos.writeUTF(randomCookie);
                        dos.flush();

                    }else{
                        dos.writeUTF("");
                        dos.flush();
                    }


                }
                dos.close();
                bos.close();
                os.close();    

            }catch(EOFException e){
                e.printStackTrace();
            }

            dis.close();
            bis.close();
            is.close();
        }catch(EOFException e){
            socket.close();
            server.close();
        }


    }
}
