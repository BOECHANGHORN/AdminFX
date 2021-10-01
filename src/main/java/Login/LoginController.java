package Login;

import Admin.AdminDatabase;
import Role.Role;

public class LoginController {

    public Role login(String userName, String password) throws UserNotExistExcpt, WrongPwdExcpt {

        Role user = AdminDatabase.getInstance().searchUser(userName); //try get from AdminDB

        if (user == null)
            throw new UserNotExistExcpt("User does not exist");
        else if (!user.getPassword().equals(password))
            throw new WrongPwdExcpt("Wrong password exception");

        return user;
    }
}
