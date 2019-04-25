package com.sugonedu.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.RouteContext;
import com.blade.mvc.annotation.*;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.mvc.http.Session;
import com.sugonedu.model.Note;
import com.sugonedu.model.SysUser;
import com.sugonedu.service.NoteService;
import com.sugonedu.service.UserService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Path
public class UserController {

    @Inject
    private UserService userService;

    @Inject
    NoteService noteService;

    /**
     * 首页跳转
     * @return
     */
//    @Route("")
//    public void index(Response response){
//        response.redirect("static/note_login.html");//此方法会找不到js,默认js找的同级目录
//    }

    @Route("/")
    public String index(RouteContext ctx,Request request){
        request.attribute("aa","bb");
        return "note_login.html";
    }

    @Route("/login")
    public void login(@Param String username,@Param String password,Response response,Request request){
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        try {
            sysUser = userService.login(sysUser);
        } catch (SQLException e) {
            response.html("<alert>登录失败</alert>");
            response.redirect("/");
            return;
        }
        if(null == sysUser.getId()){
            response.redirect("/");
            return;
        }
        Session session = request.session();
        session.attribute("user",sysUser);
        response.redirect("/noteAll");
    }
    @Route("/noteAll")
    public String noteAll(Request request){
        try {
            List<Note> all = noteService.all();
            request.attribute("notes",all);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "note_favor.html";
    }

    @Route("/note/id")
    @JSON
    public Note noteId(@Param String id,Request request){
        Note note = null;
        try {
            note = noteService.id(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return note;
    }

    @Route("/noteAdd")
    public String noteAdd(){
        return "note_add.html";
    }

    @Route("/noteSave")
    @JSON
    public Map<String,Object> noteSave(@Param() String id,@Param String title,@Param String content){

        Note note = new Note();
        if(!(null == id || "".equals(id))){
            note.setId(UUID.randomUUID().toString());
        }
        note.setTitle(title);
        note.setContent(content);
        int i = 0;
        try {
            i = noteService.saveNote(note);
        } catch (SQLException e) {
        }
        Map<String,Object> map = new HashMap<>();
        map.put("success",i > 0);
        return map;
    }

    @Route("/noteFavor")
    public void noteFavor(@Param String id,Response response){
        int i = 0;
        try {
            i = noteService.favor(id);
        } catch (SQLException e) {
        }
        response.redirect("/noteAll");
    }

    @Route("/noteDelete")
    public void noteDelete(@Param String id,Response response){


        int i = 0;
        try {
            i = noteService.delete(id);
        } catch (SQLException e) {
        }
        response.redirect("/noteAll");
    }

    @PostRoute("/reg")
    public void reg(@Param String username,@Param String password,@Param String email,Response response){
        SysUser sysUser = new SysUser();
        sysUser.setId(UUID.randomUUID().toString());
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        sysUser.setEmail(email);
        sysUser.setImage("");
        sysUser.setCreateTime("2019-04-23");
        sysUser.setDeleteStutus(0);
        try {
            userService.reg(sysUser);
        } catch (SQLException e) {
            e.printStackTrace();
            response.html("<alert>注册失败</alert>");
        }
    }

}
