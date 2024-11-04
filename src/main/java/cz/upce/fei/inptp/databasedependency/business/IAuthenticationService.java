package cz.upce.fei.inptp.databasedependency.business;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface IAuthenticationService {
    // TODO: (low priority) - change to safe password hash function (PBKDF2, bcrypt, scrypt)
    static String encryptPassword(String password) {
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));

            return new BigInteger(1, crypt.digest()).toString(16);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // TODO: add tests
    // TODO: Authenticate("user", "pass") - Person("user", encryptPwd("pass")) - pass
    // TODO: Authenticate("user", "invalid") - Person("user", encryptPwd("pass")) - fail
    // TODO: Authenticate("user", "pass") - nonexistent person - fail
    boolean Authenticate(String login, String password);
}
