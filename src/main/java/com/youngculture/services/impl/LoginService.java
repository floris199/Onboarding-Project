package com.youngculture.services.impl;

import com.youngculture.dao.impl.UserDAO;
import com.youngculture.entities.UserEntity;
import com.youngculture.services.intrf.LoginServiceInterface;

import java.util.List;

import static com.youngculture.constants.Constants.*;

public class LoginService implements LoginServiceInterface {

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
