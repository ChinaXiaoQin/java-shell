import java.io.*;
import java.util.Scanner;

public class shell {


    public static void main(String[] args) {
        Runtime run = Runtime.getRuntime();
        try {
            // run.exec("cmd /k shutdown -s -t 3600");
            Process process = run.exec("cmd.exe /c dir");

            File file = new  File("test.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            
            InputStream input = process.getInputStream(); 
            DataInputStream a = new DataInputStream(input);
            BufferedReader reader = new BufferedReader(new InputStreamReader(a));
            String szline="";
            while (szline != null) {
                szline = reader.readLine();
                System.out.println(szline);
                bw.append(szline);
                bw.newLine();
            }
            bw.flush();
            bw.close();

            reader.close();
            process.waitFor();
            process.destroy();

            
            
        } catch (Exception e){
            e.printStackTrace();
        }
          
    }
}