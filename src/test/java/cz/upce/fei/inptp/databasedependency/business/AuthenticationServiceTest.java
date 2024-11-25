package cz.upce.fei.inptp.databasedependency.business;

import cz.upce.fei.inptp.databasedependency.dao.PersonDAO;
import cz.upce.fei.inptp.databasedependency.entity.Person;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static cz.upce.fei.inptp.databasedependency.business.AuthenticationService.encryptPassword;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    // Automatically mocks the PersonDAO
    @Mock
    private PersonDAO personDAO;

    // Injects the mock PersonDAO into the AuthenticationService
    @InjectMocks
    private AuthenticationService authenticationService;

    @org.junit.jupiter.api.Test
    void authenticateUserWithCorrectPassword() {
        String password = encryptPassword("pass");
        Person expectedPerson = new Person(1,"user", password);
        when(personDAO.load("name = 'user'")).thenReturn(expectedPerson);
        
        boolean result = authenticationService.Authenticate("user", "pass");

        // Assert
        assertTrue(result);
        verify(personDAO, times(1)).load("name = 'user'");
    }

    @org.junit.jupiter.api.Test
    void authenticateUserWithWrongPassword() {
        String password = encryptPassword("pass");
        Person expectedPerson = new Person(1,"user", password);
        when(personDAO.load("name = 'user'")).thenReturn(expectedPerson);

        boolean result = authenticationService.Authenticate("user", "invalid");

        // Assert
        assertFalse(result);
        verify(personDAO, times(1)).load("name = 'user'");
    }

    @org.junit.jupiter.api.Test
    void authenticateUserNotFound() {
        String password = encryptPassword("pass");
        Person expectedPerson = null;
        when(personDAO.load("name = 'user'")).thenReturn(expectedPerson);

        boolean result = authenticationService.Authenticate("user", "pass");

        // Assert
        assertFalse(result);
        verify(personDAO, times(1)).load("name = 'user'");
    }

}
