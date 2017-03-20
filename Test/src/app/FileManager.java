package app;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Roledene on 3/20/2017.
 */
public class FileManager {
    public static String DEFAULT_PATH = System.getProperty("user.dir")+ File.separator+"images"+File.separator;
    public FileManager(){

    }

    public List<String> getDirAndFilePaths(){
        return getDirAndFilePaths(DEFAULT_PATH);
    }

    public List<String> getDirAndFilePaths(String dirPath){
//        File folder = new File(System.getProperty("user.dir")+ File.separator+"images");

        List<String> fileList = new ArrayList<String>();
        File folder = new File(dirPath);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            fileList.add(listOfFiles[i].getName());
        }
        return fileList;
    }

    public List<File> getFiles(){
        return getFiles(DEFAULT_PATH);
    }

    public List<File> getFiles(String dirPath){
//        File folder = new File(System.getProperty("user.dir")+ File.separator+"images");

        List<File> fileList = new ArrayList<File>();
        File folder = new File(dirPath);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            fileList.add(listOfFiles[i]);
        }
        return fileList;
    }

    public List<String> getFilePaths(){
        return getFilePaths(DEFAULT_PATH);
    }

    public List<String> getFilePaths(String dirPath){
//        File folder = new File(System.getProperty("user.dir")+ File.separator+"images");

        List<String> fileList = new ArrayList<String>();
        File folder = new File(dirPath);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if(listOfFiles[i].isFile())
                fileList.add(dirPath+File.separator+listOfFiles[i].getName());
        }
        return fileList;
    }

    public List<String> getDirPaths(){
        return getDirPaths(DEFAULT_PATH);
    }

    public List<String> getDirPaths(String dirPath){
//        File folder = new File(System.getProperty("user.dir")+ File.separator+"images");

        List<String> fileList = new ArrayList<String>();
        File folder = new File(dirPath);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if(listOfFiles[i].isDirectory())
                fileList.add(listOfFiles[i].getName());
        }
        return fileList;
    }


    public void copyDirectory(String sourceLocation , String targetLocation){

        try {
            copyDirectory(new File(sourceLocation),new File(targetLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // If targetLocation does not exist, it will be created.
    public void copyDirectory(File sourceLocation , File targetLocation)
            throws IOException {

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }

            String[] children = sourceLocation.list();
            for (int i=0; i<children.length; i++) {
                copyDirectory(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {

            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }

    public void createDirectory(String targetLocation){
        try {
            createDirectory(new File(targetLocation));
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void createDirectory(File targetLocation)
            throws IOException {
        if (!targetLocation.exists())
            targetLocation.mkdir();

    }

    public void deleteFiles(String dirPath){
        File folder = new File(dirPath);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if(listOfFiles[i].isFile())
                listOfFiles[i].delete();
        }
    }

    public static void dirDelete(String file){
        try {
            dirDelete(new File(file));
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void dirDelete(File file)
            throws IOException{

        if(file.isDirectory()){

            //directory is empty, then delete it
            if(file.list().length==0){

                file.delete();
                System.out.println("Directory is deleted : "
                        + file.getAbsolutePath());

            }else{

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    dirDelete(fileDelete);
                }

                //check the directory again, if empty then delete it
                if(file.list().length==0){
                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());
                }
            }

        }else{
            //if file, then delete it
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }
    }
}
