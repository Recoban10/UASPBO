/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc.user;

/**
 *
 * @author Khanza
 */
public class LoginRequest {
    
//    OBJEK LOGIN
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RequestLogin{");
        sb.append("username=").append(username);
        sb.append(", password=").append(password);
        sb.append('}');
        return sb.toString();
    }
    
}
