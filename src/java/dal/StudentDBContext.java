/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attend;
import model.Course;
import model.Group;
import model.Instructor;
import model.Room;
import model.Session;
import model.Student;
import model.TimeSlot;

/**
 *
 * @author Asus
 */
public class StudentDBContext extends DBContext<Student> {

    public ArrayList<Student> reportAttend(int gid) {
        ArrayList<Student> students = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select s.studentId,s.studentName, SUM(CASE WHEN a.status = 0 THEN 1 ELSE 0 END) as absent\n"
                    + "	from Student s \n"
                    + "	inner join Participate p on p.studentId = s.studentId\n"
                    + "	inner join [Group] g on g.groupId = p.groupId\n"
                    + "	left join Attend a on s.studentId = a.studentId\n"
                    + "	left join [Session] ses on a.sessionId = ses.sessionId\n"
                    + "	where p.groupId = ?\n"
                    + "	group by s.studentId,s.studentName";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, gid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getString("studentId"));
                s.setName(rs.getString("studentName"));
                s.setAbsent(rs.getInt("absent"));
                students.add(s);
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
        return students;
    }

    @Override
    public void insert(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Student> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Student getTimeTable(String sid, Date start, Date end) {
        Student student = null;
        try {
            String sql = "SELECT s.studentId,s.studentName,g.groupId,g.groupName,r.roomId,i.instructorId,i.instructorName,t.slotId,t.startTime,t.endTime,ses.sessionId,ses.[date],c.courseId,c.courseName,a.status\n"
                    + "FROM [Student] s \n"
                    + "inner join [Participate] p on p.studentId = s.studentId\n"
                    + "inner join [Group] g on g.groupId = p.groupId\n"
                    + "inner join [Course] c on c.courseId = g.courseId\n"
                    + "inner join [Instructor] i on i.instructorId = g.instructorId\n"
                    + "\n"
                    + "inner join [Session] ses on ses.groupId = p.groupId\n"
                    + "inner join [Room] r on r.roomId = ses.roomId\n"
                    + "inner join [TimeSlot] t on t.slotId = ses.slotId\n"
                    + "left join [Attend]  a on a.sessionId = ses.sessionId and a.studentId = s.studentId\n"
                    + "Where s.studentId= ? and ses.[date] >= ? and ses.[date] <= ? \n"
                    + "group by s.studentId,s.studentName,g.groupId,g.groupName,r.roomId,i.instructorId,i.instructorName,t.slotId,t.startTime,t.endTime,ses.sessionId,ses.[date],c.courseId,c.courseName,a.status,ses.[date]"
                    + "order by s.studentId,g.groupId";
            PreparedStatement stm = null;
            ResultSet rs = null;
            stm = connection.prepareStatement(sql);
            stm.setString(1, sid);
            stm.setDate(2, start);
            stm.setDate(3, end);
            rs = stm.executeQuery();
            Group currentGroup = new Group();
            currentGroup.setId(-1);
            while (rs.next()) {
                if (student == null) {
                    Student s = new Student();
                    s.setId(rs.getString("studentId"));
                    s.setName(rs.getString("studentName"));
                    student = s;
                }
                int gid = rs.getInt("groupId");
                if (gid != currentGroup.getId()) {
                    currentGroup = new Group();
                    currentGroup.setId(rs.getInt("groupId"));
                    currentGroup.setName(rs.getString("groupName"));
                    Course c = new Course();
                    c.setId(rs.getString("courseId"));
                    c.setName(rs.getString("courseName"));
                    currentGroup.setCourses(c);
                    student.getGroups().add(currentGroup);
                }
                Session ses = new Session();
                ses.setId(rs.getInt("sessionId"));
                ses.setDate(rs.getDate("date"));

                ses.setStatus(rs.getBoolean("status"));
                if (rs.wasNull()) {
                    ses.setNotyet(true);
                }
                ses.setGroup(currentGroup);

                Instructor l = new Instructor();
                l.setId(rs.getString("instructorId"));
                l.setName(rs.getString("instructorName"));
                ses.setLecturer(l);

                Room r = new Room();
                r.setId(rs.getString("roomId"));
                ses.setRoom(r);

                TimeSlot t = new TimeSlot();
                t.setId(rs.getInt("slotId"));
                t.setStart(rs.getTime("startTime"));
                t.setEnd(rs.getTime("endTime"));
                ses.setSlot(t);
                currentGroup.getSessions().add(ses);
//                student.getGroups().add(currentGroup);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }

    public ArrayList<Group> getAllGroups(String sid) {
        ArrayList<Group> groups = new ArrayList<>();
        try {
            String sql = "SELECT g.groupId,g.groupName,c.courseName,c.courseId\n"
                    + "  FROM [Student] s\n"
                    + "  inner join [Participate] p on p.studentId = s.studentId\n"
                    + "  inner join [Group] g on g.groupId = p.groupId\n"
                    + "  inner join [Course] c on c.courseId = g.courseId\n"
                    + "  WHERE s.studentId= ?";
            PreparedStatement stm = null;
            ResultSet rs = null;
            stm = connection.prepareStatement(sql);
            stm.setString(1, sid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                g.setId(rs.getInt("groupId"));
                g.setName(rs.getString("groupName"));
                Course c = new Course();
                c.setId(rs.getString("courseId"));
                c.setName(rs.getString("courseName"));
                g.setCourses(c);
                groups.add(g);
            }
            return groups;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Attend> getSesbyId(String sid, int gid) {
        ArrayList<Attend> attends = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT  ses.[date],ses.slotId,t.startTime,t.endTime,ses.roomId\n"
                    + "  ,ses.lecturerId,g.groupName,a.[status],a.comment\n"
                    + "  FROM [Student] s\n"
                    + "  inner join [Participate] p on p.studentId = s.studentId\n"
                    + "  inner join [Group] g on g.groupId = p.groupId\n"
                    + "  inner join [Session] ses on ses.groupId = g.groupId\n"
                    + "  inner join [Instructor] i on i.instructorId = ses.lecturerId\n"
                    + "  inner join [TimeSlot] t on t.slotId = ses.slotId\n"
                    + "  left join [Attend] a on a.sessionId = ses.sessionId and a.studentId = s.studentId\n"
                    + "  WHERE s.studentId= ? and g.groupId = ? \n"
                    + "  group by a.aId,ses.[date],ses.slotId,t.startTime,t.endTime,ses.roomId\n"
                    + "  ,ses.lecturerId,g.groupName,a.[status],a.comment\n"
                    + "  order by ses.[date]";
            stm = connection.prepareStatement(sql);
            stm.setString(1, sid);
            stm.setInt(2, gid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attend a = new Attend();
                Session s = new Session();
                s.setDate(rs.getDate("date"));

                TimeSlot t = new TimeSlot();
                t.setId(rs.getInt("slotId"));
                t.setStart(rs.getTime("startTime"));
                t.setEnd(rs.getTime("endTime"));
                s.setSlot(t);

                Room r = new Room(rs.getString("roomId"));
                s.setRoom(r);

                Instructor i = new Instructor();
                i.setId(rs.getString("lecturerId"));
                s.setLecturer(i);

                Group g = new Group();
                g.setId(gid);
                g.setName(rs.getString("groupName"));
                s.setGroup(g);

                s.setStatus(rs.getBoolean("status"));
                if (rs.wasNull()) {
                    s.setNotyet(true);
                }
                a.setSession(s);
                a.setComment(rs.getString("comment"));
                attends.add(a);
            }
            return attends;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
