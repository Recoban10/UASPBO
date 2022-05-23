/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc.chat;

/**
 *
 * @author Khanza
 */
public class UsernameResponse {

//    RESPONSE PENGIRIM DAN PENERIMA
    private String sender;
    private String reciver;
    private String content;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "UsernameResponse{" + "sender=" + sender + ", reciver=" + reciver + ", content=" + content + '}';
    }

}
