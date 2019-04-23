package com.sugonedu.service;

import com.blade.ioc.annotation.Bean;
import com.sugonedu.model.SysUser;
import com.sugonedu.utils.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Bean
public class UserService {
    /**
     * 登录
     * @param user
     * @return
     * @throws SQLException
     */
    public SysUser login(SysUser user) throws SQLException {
        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "select * from sys_user where username='%s'and password='%s'";
        sql = String.format(sql,user.getUsername(),user.getPassword());
        ResultSet rs = stmt.executeQuery(sql);
        SysUser sysUser = new SysUser();
        while (rs.next()) {
            sysUser.setId(rs.getString("id"));
            sysUser.setUsername(rs.getString("username"));
            sysUser.setPassword(rs.getString("password"));
            sysUser.setEmail(rs.getString("email"));
            sysUser.setImage(rs.getString("image"));
            sysUser.setCreateTime(rs.getString("create_time"));
        }
        return sysUser;
    }

    /**
     * 注册
     * @param sysUser
     * @throws SQLException
     */
    public void reg(SysUser sysUser) throws SQLException {
        Connection conn = DB.getConnection();
        Statement stmt = conn.createStatement();

        String sql = " INSERT INTO sys_user (id,username,password,email,image,create_time,delete_stutus) VALUES('%s','%s','%s','%s','%s','%s','%s' ) ";
        sql = String.format(sql,sysUser.getId(),sysUser.getUsername(),sysUser.getPassword(),sysUser.getEmail(),sysUser.getImage(),sysUser.getCreateTime(),sysUser.getDeleteStutus());
        stmt.execute(sql);
    }

}
