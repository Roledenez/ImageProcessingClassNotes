package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Roledene on 3/17/2017.
 */
public class Processor {
    String command;
    public static String DEFAULT_PATH = System.getProperty("user.dir")+ File.separator+"images"+ File.separator;

    public void compate(){
        // TODO add your handling code here:

//        System.out.println(DEFAULT_PATH); //C:\apache\ImageMagick\
        String output = "";
        String image1 = DEFAULT_PATH+"40.jpg";
        String image2 = DEFAULT_PATH+"80.jpg";
        command = "compare -metric MAE -subimage-search "+image1+" "+image2+" null: ";
        System.out.println(command);
        try //
        {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(command);
            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ( (line = br.readLine()) != null)
                output += line;
            int exitVal = proc.waitFor();
            output += "Process exitValue: " + exitVal;
            System.out.println("output: " +output);
        } catch (Throwable t)
        {
            output += t.getMessage();
            t.printStackTrace();
        }
        System.out.println(output);
    }
}
