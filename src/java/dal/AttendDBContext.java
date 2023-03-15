/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attend;
import model.Student;

/**
 *
 * @author Asus
 */
public class AttendDBContext extends DBContext<Attend> {

    @Override
    public void insert(Attend model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attend model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attend model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attend get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Attend> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void updateAtts(ArrayList<Attend> atts, int sessionid) {
        ArrayList<PreparedStatement> stms = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            //UPDATE Session Record
            String sql_update_session = "UPDATE [Session] SET sessionName ='Attend' WHERE sessionId = ?";
            PreparedStatement stm_update_session = connection.prepareStatement(sql_update_session);
            stm_update_session.setInt(1, sessionid);
            stm_update_session.executeUpdate();
            stms.add(stm_update_session);

            //PROCESS Attendace records
            for (Attend att : atts) {
                System.out.println("Prboelm:");
                System.out.println(att.getId());
                if (att.getId() == 0) //INSERT
                {
                    String sql_insert_att = "INSERT INTO [Attend]([studentId],[sessionId],[status],[comment],[recordTime])\n"
                            + "VALUES\n"
                            + "(?,?,?,?,?) ";
                    PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_att);
                    stm_insert_att.setString(1, att.getStudent().getId());
                    stm_insert_att.setInt(2, sessionid);
                    stm_insert_att.setBoolean(3, att.isStatus());
                    stm_insert_att.setString(4, att.getComment());
                    stm_insert_att.setTimestamp(5, att.getRecordTime());
                    stm_insert_att.executeUpdate();
                    System.out.println("INSERT");
                    stms.add(stm_insert_att);

                } else //UPDATE
                {
                    String sql_update_att = "UPDATE Attend SET status = ?,comment = ? WHERE aid = ?";
                    PreparedStatement stm_update_att = connection.prepareStatement(sql_update_att);
                    stm_update_att.setBoolean(1, att.isStatus());
                    stm_update_att.setString(2, att.getComment());
                    stm_update_att.setInt(3, att.getId());
                    stm_update_att.executeUpdate();
                    System.out.println("UPDATE");
                    stms.add(stm_update_att);
                }
            }

            connection.commit();
        } catch (SQLException ex) {

            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (PreparedStatement stm : stms) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Attend> getAttsBySessionID(int sessionid) {
        ArrayList<Attend> atts = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.studentId,s.studentName,ISNULL(a.status,0) as [status], ISNULL(a.comment,'') as [comment],a.aId\n"
                    + "FROM Student s inner join Participate p on s.studentId = p.studentId\n"
                    + "				inner join [Group] g on g.groupId = p.groupId\n"
                    + "				inner join [Session] ses on ses.groupId = g.groupId\n"
                    + "				left join [Attend] a on a.sessionId = ses.sessionId and s.studentId = a.studentId\n"
                    + "WHERE ses.sessionId = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sessionid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attend a = new Attend();
                a.setId(rs.getInt("aId"));
                a.setStatus(rs.getBoolean("status"));
                a.setComment(rs.getString("comment"));
                Student s = new Student();
                s.setId(rs.getString("studentId"));
                s.setName(rs.getString("studentName"));
                a.setStudent(s);
                System.out.println(a.getId());
                atts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return atts;
    }

}
