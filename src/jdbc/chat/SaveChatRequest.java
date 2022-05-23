/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc.chat;

import java.sql.Timestamp;

/**
 *
 * @author Khanza
 */
public class SaveChatRequest {

//    OBJEK MENGIRIM CHAT
    private String username;
    private String usernameTarget;
    private Timestamp date;
    private String content;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameTarget() {
        return usernameTarget;
    }

    public void setUsernameTarget(String usernameTarget) {
        this.usernameTarget = usernameTarget;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SaveChatRequest{" + "username=" + username + ", usernameTarget=" + usernameTarget + ", date=" + date + ", content=" + content + '}';
    }
}
