package com.sugonedu.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.RouteContext;
import com.blade.mvc.annotation.*;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.mvc.http.Session;
import com.sugonedu.model.Note;
import com.sugonedu.model.NoteGroup;
import com.sugonedu.model.SysUser;
import com.sugonedu.service.NoteGroupService;
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

    @Inject
    private NoteGroupService noteGroupService;

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
            e.printStackTrace();
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
    public String noteAll(Request request,@Param String groupId){
        SysUser user = request.session().attribute("user");
        try {
            List<NoteGroup> groupList = noteGroupService.all(user.getId());
            for(NoteGroup noteGroup: groupList){
                if(noteGroup.getId().equals(groupId)){
                    noteGroup.setSelected(true);
                }
            }
            request.attribute("noteGroup",groupList);
            List<Note> all = noteService.all(user.getId(),groupId);
            request.attribute("notes",all);
            request.attribute("groupId",groupId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  "note_favor.html";
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
    public String noteAdd(Request request){
        SysUser user = request.session().attribute("user");
        List<NoteGroup> groupList = null;
        try {
            groupList = noteGroupService.all(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.attribute("noteGroup",groupList);
        return "note_add.html";
    }

    @Route("/noteSave")
    @JSON
    public Map<String,Object> noteSave(Request request ,@Param() String id,@Param String title,@Param String content,@Param String groupId){
        SysUser user = request.session().attribute("user");
        Note note = new Note();
        note.setId(id);
        note.setTitle(title);
        note.setContent(content);
        note.setGroupId(groupId);
        note.setUserId(user.getId());
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

    @Route("/addGroup")
    public Map<String, Object> addGroup(@Param String name,Request request){
        Map map = new HashMap();
        try {
            SysUser user = request.session().attribute("user");
            NoteGroup noteGroup = new NoteGroup();
            noteGroup.setId(UUID.randomUUID().toString());
            noteGroup.setName(name);
            noteGroup.setUserId(user.getId());
            int i = 0;
            i = noteGroupService.saveNoteGroup(noteGroup);

            map.put("success",i>0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Route("/deleteGroup")
    public Map<String, Object> deleteGroup(@Param String groupId,Request request){
        Map map = new HashMap();
        try {
            SysUser user = request.session().attribute("user");
            int i = 0;
            i = noteGroupService.deleteGroup(user.getId(),groupId);
            map.put("success",i>0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

}
