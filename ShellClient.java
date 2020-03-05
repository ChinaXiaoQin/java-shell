/*
未完
java shell
client
*/
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ShellClient {
    public static void main(String[] args) {
        InputStream is = null;
        OutputStream os = null;
        boolean flag=true;
        System.out.println("cilent RUN");

        try {
            Socket socket = new Socket("127.0.0.1", 6666);
            is = socket.getInputStream();
            os = socket.getOutputStream();
            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(os);
            //Thread.sleep(1000);
            while(flag){
                Scanner sc = new Scanner(System.in); 
                System.out.print(">: "); 
                String code = sc.nextLine(); 
                dos.writeUTF(code);
                if(code=="exit"){
                    flag=false;
                }
                String redShellMassage=null;
                if((redShellMassage=dis.readUTF())!=null);
                    System.out.println(redShellMassage);
            }
            
            


            dis.close();
            dos.close();
            socket.close();
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
    }


}