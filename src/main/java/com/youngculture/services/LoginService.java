package com.youngculture.services;

import com.youngculture.dao.impl.UserDAO;
import com.youngculture.entities.UserEntity;

import java.util.List;

public class LoginService {

    private static final String NOT_FOUND = "User was not found!";
    private static final String WRONG_PASSWRD = "Password is incorrect!";
    private static final String EMPTY = "";
    private static final String GUEST = "guest";

    public String validateCredentials( String username, String passwrd ){

        if( username.toLowerCase().equals(GUEST) )
        {
            return NOT_FOUND;
        }

        List<UserEntity> usersList = new UserDAO( ).get( username );

        if( usersList.isEmpty() )
        {
            return NOT_FOUND;
        }

        if( !usersList.get( 0 ).getPasswrd().equals(passwrd) )
        {
            return WRONG_PASSWRD;
        }

        return EMPTY;
    }

}
