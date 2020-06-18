package com.youngculture.services.intrf;

public interface RegisterServiceInterface
{
    public String registerUser( String username, String passwrd );

    public String validateCredentials( String username, String passwrd, String confirmpasswrd );
}
