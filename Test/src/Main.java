import database.Patient;
import database.PatientTable;

/**
 * Created by Roledene on 3/7/2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(Processor.DEFAULT_PATH);

//        Processor p = new Processor();
//        p.compate();


//        Connection conn = DBUtil.getConnection(DBType.MYSQL);
//        if(conn == null){
//            System.out.println("connection null");
//        }else{
//            System.out.println("connection established");
//        }


        Patient patient = new Patient();
        patient.setPid(2);
        patient.setNic("myNIC");
        patient.setName("Hello Patient");
        patient.setAge(34);

        PatientTable.insert(patient);

        patient = PatientTable.getRow(1);
        System.out.println(patient.toString());
    }
}
