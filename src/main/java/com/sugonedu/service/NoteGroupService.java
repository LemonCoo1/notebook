package com.sugonedu.service;

import com.blade.ioc.annotation.Bean;
import com.sugonedu.model.Note;
import com.sugonedu.model.NoteGroup;
import com.sugonedu.utils.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author
 * @date 2019/4/23
 */
@Bean
public class NoteGroupService {

    public int saveNoteGroup(NoteGroup note) throws SQLException {
        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();
        String sql;
        // add
            note.setId(UUID.randomUUID().toString());
            sql = " INSERT INTO note_group(id,name,user_id) VALUES('%s','%s','%s')";
            sql = String.format(sql,note.getId(),note.getName(),note.getUserId());

        int i = stmt.executeUpdate(sql);
        return i;
    }
    public int deleteGroup(String userId,String groupId) throws SQLException {
        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();
        String sql;
        sql = " delete from note_group where id = '%s' and user_id='%s'";
        sql = String.format(sql,groupId,userId);
        int i = stmt.executeUpdate(sql);
        return i;
    }



    public List<NoteGroup> all(String userId) throws SQLException {
        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();

        String sql = " select * from note_group where user_id='%s'";
        sql = String.format(sql,userId);

        List<NoteGroup> notes = new ArrayList<>();
        ResultSet resultSet = stmt.executeQuery(sql);
        while (resultSet.next()){
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String userId1 = resultSet.getString("user_id");
            notes.add(new NoteGroup(id,name,userId1));

        }
        return notes;
    }
}
