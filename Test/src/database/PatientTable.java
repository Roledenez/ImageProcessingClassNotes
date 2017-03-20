package database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Roledene on 3/20/2017.
 */
public class PatientTable {
    //get a raw
    public static Patient getRow(int pid) throws SQLException {
        String sql = "SELECT * FROM patient WHERE pid=?";
        ResultSet rs = null;
        try(Connection conn = DBUtil.getConnection(DBType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1, pid);
            rs = stmt.executeQuery();

            if(rs.next())
            {
                Patient bean = new Patient();
                bean.setPid(pid);
                bean.setName(rs.getString("pname"));
                bean.setNic(rs.getString("nic"));
                bean.setAge(rs.getInt("page"));
                bean.setWeight(rs.getInt("weight"));
//                      bean.setAgentPrice_unit(rs.getFloat("agentPrice_Unit"));
//                      bean.setWsPrice_unit(rs.getFloat("wsPrice_Unit"));
//                      bean.setRetailPrice_unit(rs.getFloat("retailPrice_Unit"));
//                      bean.setUnitPerBox(rs.getInt("unitPerBox"));
//                      bean.setCurrentStock(rs.getInt("currentStock"));
                return bean;
            }else{
                JOptionPane.showMessageDialog(null, "There are no items with name : "+pid, "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally{
            if(rs!=null){
                rs.close();
            }

        }
    }

    //insert a raw

    //delete a raw

    //update a raw
}
