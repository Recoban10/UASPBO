package jdbc.chat;

import java.util.List;

/**
 *
 * @author Khanza
 */
public interface ChatJdbc {

    //    MENAMPILKAN ISI CHAT
    public List<UsernameResponse> selectAll(UsernameRequest request);
    
    //    MENGIRIM CHAT
    public void insert(SaveChatRequest request);

}
