package com.sugonedu.service;

import com.blade.ioc.annotation.Bean;
import com.sugonedu.model.Note;
import com.sugonedu.utils.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xucong
 * @date 2019/4/23
 */
@Bean
public class NoteService {

    public int saveNote(Note note) throws SQLException {
        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();

        String sql = " INSERT INTO note(id,title,content) VALUES('%s','%s','%s')";
        sql = String.format(sql,note.getId(),note.getTitle(),note.getContent());
        int i = stmt.executeUpdate(sql);
        return i;
    }

    public Note id(String id) throws SQLException {
        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();

        String sql = " select * from note where id='%s'";
        sql = String.format(sql,id);
        ResultSet resultSet = stmt.executeQuery(sql);
        Note note = null;
        while (resultSet.next()){
            String ida = resultSet.getString("id");
            String title = resultSet.getString("title");
            String content = resultSet.getString("content");
            Integer favor = resultSet.getInt("favor");
            note = new Note(ida,title,content,favor);
        }
        return note;
    }

    public int favor(String id) throws SQLException {
        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();

        Note id1 = id(id);
        String sql = "";
        if (id1.getFavor() > 0){
            sql = " update note set favor = 0 where id ='%s'";
        }else{
            sql = " update note set favor = 1 where id ='%s'";
        }
        sql = String.format(sql,id);
        int i = stmt.executeUpdate(sql);
        return i;
    }



    public int delete(String id) throws SQLException {
        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();

        String sql = " delete from note where id='%s'";
        sql = String.format(sql,id);
        int i = stmt.executeUpdate(sql);
        return i;
    }

    public List<Note> all() throws SQLException {
        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();

        String sql = " select * from note";

        List<Note> notes = new ArrayList<>();
        ResultSet resultSet = stmt.executeQuery(sql);
        while (resultSet.next()){
            String id = resultSet.getString("id");
            String title = resultSet.getString("title");
            String content = resultSet.getString("content");
            Integer favor = resultSet.getInt("favor");
            notes.add(new Note(id,title,content,favor));

        }
        return notes;
    }
}
