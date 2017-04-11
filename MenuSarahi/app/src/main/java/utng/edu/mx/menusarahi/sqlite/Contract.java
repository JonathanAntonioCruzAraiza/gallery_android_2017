package utng.edu.mx.menusarahi.sqlite;

import java.util.UUID;

/**
 * Created by jony on 16/2/2017.
 */

public class Contract {
    interface UserColumns {
        String ID = "id";
        String USER = "user";
        String PASSWORD = "password";
        String FIRSTNAME = "firsname";
        String LASTNAME = "lastname";

    }

    public static class Users implements UserColumns {
        public static String generateIdUser() {
            return "PR-" + UUID.randomUUID().toString();

        }
    }
}
