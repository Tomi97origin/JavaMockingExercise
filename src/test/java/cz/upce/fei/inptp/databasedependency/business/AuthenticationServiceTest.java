package cz.upce.fei.inptp.databasedependency.business;

import cz.upce.fei.inptp.databasedependency.dao.PersonDAO;
import cz.upce.fei.inptp.databasedependency.entity.Person;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static cz.upce.fei.inptp.databasedependency.business.IAuthenticationService.encryptPassword;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @org.junit.jupiter.api.Test
    void authenticateUserWithCorrectPassword() {
        PersonDAO personDAO = mock(PersonDAO.class);
        String password = encryptPassword("pass");
        Person expectedPerson = new Person(1,"user", password);
        when(personDAO.load("name = 'user'")).thenReturn(expectedPerson);
        IAuthenticationService IAuthenticationService = new AuthenticationService(personDAO);


        boolean result = IAuthenticationService.Authenticate("user", "pass");

        // Assert
        assertTrue(result);
        verify(personDAO, times(1)).load("name = 'user'");
    }

    @org.junit.jupiter.api.Test
    void authenticateUserWithWrongPassword() {
        PersonDAO personDAO = mock(PersonDAO.class);
        String password = encryptPassword("pass");
        Person expectedPerson = new Person(1,"user", password);
        when(personDAO.load("name = 'user'")).thenReturn(expectedPerson);
        IAuthenticationService IAuthenticationService = new AuthenticationService(personDAO);


        boolean result = IAuthenticationService.Authenticate("user", "invalid");

        // Assert
        assertFalse(result);
        verify(personDAO, times(1)).load("name = 'user'");
    }

    @org.junit.jupiter.api.Test
    void authenticateUserNotFound() {
        PersonDAO personDAO = mock(PersonDAO.class);
        String password = encryptPassword("pass");
        Person expectedPerson = null;
        when(personDAO.load("name = 'user'")).thenReturn(expectedPerson);
        IAuthenticationService IAuthenticationService = new AuthenticationService(personDAO);


        boolean result = IAuthenticationService.Authenticate("user", "pass");

        // Assert
        assertFalse(result);
        verify(personDAO, times(1)).load("name = 'user'");
    }

}