package app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static app.FileManager.DEFAULT_PATH;

/**
 * Created by Roledene on 3/17/2017.
 */
public class Processor {
    String command;
    String pattern = "/([\\d.+])/";

//    public static String DEFAULT_PATH = System.getProperty("user.dir")+ File.separator+"images"+ File.separator;

    public String compate(){
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
//        System.out.println(output);
        return output;
    }

    public String getComparedValues(String value){
//        String pattern = "\\((\\d+\\.?\\d*)\\)?";// extract the string values by regular expression, this is the pattern
        String pattern = "\\((\\d+\\.?\\d*)\\)?";// extract the string values by regular expression, this is the pattern
        String result = "";
        try {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(value);
            if (m.find()) {
                result = m.group(1);
            } else {
                System.out.println("Image comparison values are not match with regex \n Error: "+value);
            }
        }catch (Exception e){
            e.fillInStackTrace();
            return e.getMessage();
        }
        return result;
    }

    public String[] getComparedValues2(String value){
//        String pattern = "\\((\\d+\\.?\\d*)\\)?";// extract the string values by regular expression, this is the pattern
        String pattern = "\\d+,\\d+";// extract the string values by regular expression, this is the pattern
        String result = "";
        String strings[] = new String[2];
        try {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(value);
            if (m.find()) {
                result = m.group(0);
                strings = result.split(",");
            } else {
                System.out.println("Image comparison values are not match with regex \n Error: "+value);
            }
        }catch (Exception e){
            e.fillInStackTrace();
            System.err.println(e.getMessage());
        }
        return strings;
    }



}
