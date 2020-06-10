package com.youngculture.services;

import com.youngculture.dao.impl.UserDAO;
import com.youngculture.entities.UserEntity;

import java.util.List;

public class RegisterService {

    private static final String EMPTY_PASS = "Password can't be empty.";
    private static final String PASS_NOT_MATCH = "Password does not match.";
    private static final String USERNAME_EXISTS = "Username already exists.";
    private static final String USERNAME_INVALID = "Username is invalid.";
    private static final String EMPTY = "";
    private static final String GUEST = "guest";

    public String registerUser( String username, String passwrd )
    {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPasswrd(passwrd );

        return new UserDAO().save( user );
    }

    public String validateCredentials( String username, String passwrd, String confirmpasswrd )
    {
        String errorMsg = validateUsername( username );

        if( errorMsg.isEmpty() )
        {
            errorMsg = validatePassword( passwrd, confirmpasswrd );
        }

        return errorMsg;
    }

    public String validatePassword( String passwrd, String confirmPasswrd )
    {
        if( passwrd.equals("") || confirmPasswrd.equals("") )
        {
            return EMPTY_PASS;
        }

        if( !passwrd.equals(confirmPasswrd) )
        {
            return PASS_NOT_MATCH;
        }

        return EMPTY;
    }

    public String validateUsername( String username )
    {
        if( username.toLowerCase().equals(GUEST) )
        {
            return USERNAME_INVALID;
        }

        List<UserEntity> usersList = new UserDAO().get( username );

        if( !usersList.isEmpty() )
        {
            return USERNAME_EXISTS;
        }

        return EMPTY;
    }
}
