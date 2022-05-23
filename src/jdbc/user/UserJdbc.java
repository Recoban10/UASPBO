package jdbc.user;

import java.util.List;

/**
 *
 * @author Khanza
 */
public interface UserJdbc {

//    MENAMPILKAN SEMUA USER
    public List<SaveUserResponse> selectAll(String request);

//    MENAMPILKAN USER YANG BISA DI CHAT
    public List<SaveUserResponse> selectAllUser(String request, String username);

//    MENGIRIM PESAN
    public void insert(SaveUserRequest request);

//    MERUBAH USER
    public void update(SaveUserRequest request);
    
//    MENGHAPUS USER
    public void delete(Long request);
    
//    CEK PASSWORD
    public String checkChangePassword(ChangePasswordRequest requestLogin);
    
//    MENGGANTI PASSWORD
    public void changePassword(SaveUserRequest request);
    
//    CEK USER YANG AKTIF
    public Boolean selectUserExist(String userName);
    
//    SELECT USERNAME
    public UserResponse selectUsername(String username);

}
