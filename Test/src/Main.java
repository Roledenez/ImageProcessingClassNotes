import database.DBType;
import database.DBUtil;

import java.sql.Connection;

/**
 * Created by Roledene on 3/7/2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {


//        System.out.println(FileManager.DEFAULT_PATH);
//        FileManager fileManager = new FileManager();
//        ArrayList<String> paths = (ArrayList<String>) fileManager.getDirAndFilePaths(FileManager.DEFAULT_PATH);
//        for(String s : paths){
//            System.out.println(s);
//        }


//        System.out.println(Processor.DEFAULT_PATH);
//
//        app.Processor p = new app.Processor();
//        System.out.println(p.getComparedValues(p.compate()));
//        String s[] = p.getComparedValues2(p.compate());
//        System.out.println(s[0]);
//        System.out.println(s[1]);

//////////////////////////////////////////////////////////////////////
        Connection conn = DBUtil.getConnection(DBType.MYSQL);
        if(conn == null){
            System.out.println("connection null");
        }else{
            System.out.println("connection established");
        }

///////////////////////////////////////////////////////////

//        Patient patient = new Patient();
//        patient.setPid(2);
//        patient.setNic("myNIC");
//        patient.setName("Hello updated Patient");
//        patient.setAge(344);
//
//        PatientTable.insert(patient);
////////////////////////////////////////////////////////


//        patient = PatientTable.getRow(1);
//        System.out.println(patient.toString());
///////////////////////////////////////////////////////////

//        PatientTable.delete(1);

//////////////////////////////////////////////

//        PatientTable.update(patient);

    }
}
