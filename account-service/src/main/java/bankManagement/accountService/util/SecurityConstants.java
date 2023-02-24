package bankManagement.accountService.util;

public class SecurityConstants {

    /*
     * Headers attribute name for Authentication.
     * basic authentication -> Authorization: Basic [username:password]
     * jwt authentication -> Authorization: Bearer [token]
     */
    public static final String AUTHORIZATION_HEADER = "Authorization";
}