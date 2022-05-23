/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc.chat;

/**
 *
 * @author Khanza
 */
public class UsernameRequest {

//    OBJEK PENGIRIM DAN PENERIMA
    private String username;
    private String usernameTarget;

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

    @Override
    public String toString() {
        return "UsernameRequest{" + "username=" + username + ", usernameTarget=" + usernameTarget + '}';
    }
}
