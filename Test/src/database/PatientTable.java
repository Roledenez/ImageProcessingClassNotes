package database;

import com.mysql.jdbc.Statement;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Roledene on 3/20/2017.
 */
public class PatientTable {
    //get a row
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
                bean.setName(rs.getString("name"));
                bean.setNic(rs.getString("nic"));
                bean.setAge(rs.getInt("age"));
//                bean.setWeight(rs.getInt("weight"));
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

    //insert a row
    public static boolean insert(Patient bean) throws Exception{

        String sql= "INSERT INTO patient(pid,name,age,nic)"+
                    "values(?,?,?,?);";
        ResultSet keys=null;
        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ){
            stmt.setInt(1, bean.getPid());
            stmt.setString(2, bean.getName());
            stmt.setInt(3, bean.getAge());
            stmt.setString(4, bean.getNic());

            int affacted = stmt.executeUpdate();

            if(affacted==1){

            }else{
                JOptionPane.showMessageDialog(null, "Can't insert data", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally{
            if(keys!=null) keys.close();
        }
        return true;
    }

    //delete a row
    public static boolean delete(int pid) throws Exception{

        String sql ="DELETE FROM patient WHERE pid=?";
        try(    Connection conn = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            stmt.setInt(1, pid);

            int affacted = stmt.executeUpdate();

            if(affacted==1){
                return true;
            }else{
                return false;
            }

        }catch(Exception e){
            return false;
        }
    }

    //update a row
    public static boolean update(Patient bean) throws Exception{

        String sql = "UPDATE patient SET "+
                "nic=?, "+
                "name=?, "+
                "age=? "+
                "WHERE pid=?";
        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ){
            stmt.setString(1,bean.getNic());
            stmt.setString(2,bean.getName());
            stmt.setInt(3,bean.getAge());
            stmt.setInt(4,bean.getPid());

            int affacted = stmt.executeUpdate();

            if(affacted==1){
                return true;
            }else{
                System.err.println("No rows affacted");
                return false;
            }

        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally{

        }

    }
}
