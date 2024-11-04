package cz.upce.fei.inptp.databasedependency.business;

import cz.upce.fei.inptp.databasedependency.dao.PersonDAO;
import cz.upce.fei.inptp.databasedependency.dao.PersonRolesDAO;
import cz.upce.fei.inptp.databasedependency.entity.Person;
import cz.upce.fei.inptp.databasedependency.entity.PersonRole;
import cz.upce.fei.inptp.databasedependency.entity.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceTest {

    @org.junit.jupiter.api.Test
    void authorizePersonSubsectionPass() {
        PersonDAO personDAO = mock(PersonDAO.class);
        PersonRolesDAO personRolesDao = mock(PersonRolesDAO.class);

        Person expectedPerson = new Person(1,"user", "pass");
        String section = "/section/subsection";
        AccessOperationType access = AccessOperationType.Write;
        Role expectedRole = new Role(section, access.getOp(),"");
        List<Role> roles = new ArrayList<>();
        roles.add(expectedRole);
        PersonRole personRoles = new PersonRole(expectedPerson,roles);


        when(personDAO.getRoleWhereStringFor(expectedPerson)).thenReturn("id = 1");
        when(personRolesDao.load("id = 1")).thenReturn(personRoles);


        IAuthorizationService IAuthorizationService = new AuthorizationService(personDAO, personRolesDao);

        boolean result = IAuthorizationService.Authorize(expectedPerson,section,access);

        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    void authorizePersonSubsectionFail() {
        PersonDAO personDAO = mock(PersonDAO.class);
        PersonRolesDAO personRolesDao = mock(PersonRolesDAO.class);

        Person expectedPerson = new Person(1,"user", "pass");
        String section = "/section/subsection";
        AccessOperationType access = AccessOperationType.Write;
        Role expectedRole = new Role(section, AccessOperationType.Read.getOp(),"");
        List<Role> roles = new ArrayList<>();
        roles.add(expectedRole);
        PersonRole personRoles = new PersonRole(expectedPerson,roles);


        when(personDAO.getRoleWhereStringFor(expectedPerson)).thenReturn("id = 1");
        when(personRolesDao.load("id = 1")).thenReturn(personRoles);


        IAuthorizationService IAuthorizationService = new AuthorizationService(personDAO, personRolesDao);

        boolean result = IAuthorizationService.Authorize(expectedPerson,section,access);

        assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    void authorizePersonSectionPass() {
        PersonDAO personDAO = mock(PersonDAO.class);
        PersonRolesDAO personRolesDao = mock(PersonRolesDAO.class);

        Person expectedPerson = new Person(1,"user", "pass");
        String section = "/section";
        AccessOperationType access = AccessOperationType.Write;
        Role expectedRole = new Role(section, access.getOp(),"");
        List<Role> roles = new ArrayList<>();
        roles.add(expectedRole);
        PersonRole personRoles = new PersonRole(expectedPerson,roles);


        when(personDAO.getRoleWhereStringFor(expectedPerson)).thenReturn("id = 1");
        when(personRolesDao.load("id = 1")).thenReturn(personRoles);


        IAuthorizationService IAuthorizationService = new AuthorizationService(personDAO, personRolesDao);

        boolean result = IAuthorizationService.Authorize(expectedPerson,"/section/subsection",access);

        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    void authorizePersonSectionFail() {
        PersonDAO personDAO = mock(PersonDAO.class);
        PersonRolesDAO personRolesDao = mock(PersonRolesDAO.class);
        Person expectedPerson = new Person(1,"user", "pass");

        String section = "/section";
        AccessOperationType access = AccessOperationType.Write;
        Role expectedRole = new Role(section, AccessOperationType.Read.getOp(),"");

        List<Role> roles = new ArrayList<>();
        roles.add(expectedRole);
        PersonRole personRoles = new PersonRole(expectedPerson,roles);


        when(personDAO.getRoleWhereStringFor(expectedPerson)).thenReturn("id = 1");
        when(personRolesDao.load("id = 1")).thenReturn(personRoles);


        IAuthorizationService IAuthorizationService = new AuthorizationService(personDAO, personRolesDao);

        boolean result = IAuthorizationService.Authorize(expectedPerson,"/section/subsection",access);

        assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    void authorizePersonRootPass() {
        PersonDAO personDAO = mock(PersonDAO.class);
        PersonRolesDAO personRolesDao = mock(PersonRolesDAO.class);

        Person expectedPerson = new Person(1,"user", "pass");
        String section = "/";
        AccessOperationType access = AccessOperationType.Write;
        Role expectedRole = new Role(section, access.getOp(),"");
        List<Role> roles = new ArrayList<>();
        roles.add(expectedRole);
        PersonRole personRoles = new PersonRole(expectedPerson,roles);


        when(personDAO.getRoleWhereStringFor(expectedPerson)).thenReturn("id = 1");
        when(personRolesDao.load("id = 1")).thenReturn(personRoles);


        IAuthorizationService IAuthorizationService = new AuthorizationService(personDAO, personRolesDao);

        boolean result = IAuthorizationService.Authorize(expectedPerson,"/section/subsection",access);

        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    void authorizePersonRootFail() {
        PersonDAO personDAO = mock(PersonDAO.class);
        PersonRolesDAO personRolesDao = mock(PersonRolesDAO.class);
        Person expectedPerson = new Person(1,"user", "pass");

        String section = "/";
        AccessOperationType access = AccessOperationType.Write;
        Role expectedRole = new Role(section, AccessOperationType.Read.getOp(),"");

        List<Role> roles = new ArrayList<>();
        roles.add(expectedRole);
        PersonRole personRoles = new PersonRole(expectedPerson,roles);


        when(personDAO.getRoleWhereStringFor(expectedPerson)).thenReturn("id = 1");
        when(personRolesDao.load("id = 1")).thenReturn(personRoles);


        IAuthorizationService IAuthorizationService = new AuthorizationService(personDAO, personRolesDao);

        boolean result = IAuthorizationService.Authorize(expectedPerson,"/section/subsection",access);

        assertFalse(result);
    }

    @org.junit.jupiter.api.Test
    void authorizePersonSubsectionAdminPass() {
        PersonDAO personDAO = mock(PersonDAO.class);
        PersonRolesDAO personRolesDao = mock(PersonRolesDAO.class);

        Person expectedPerson = new Person(1,"user", "pass");
        String section = "/section/subsection";
        AccessOperationType access = AccessOperationType.Write;
        Role expectedRole = new Role(section, "admin","");
        List<Role> roles = new ArrayList<>();
        roles.add(expectedRole);
        PersonRole personRoles = new PersonRole(expectedPerson,roles);


        when(personDAO.getRoleWhereStringFor(expectedPerson)).thenReturn("id = 1");
        when(personRolesDao.load("id = 1")).thenReturn(personRoles);


        IAuthorizationService IAuthorizationService = new AuthorizationService(personDAO, personRolesDao);

        boolean result = IAuthorizationService.Authorize(expectedPerson,"/section/subsection",access);

        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    void authorizePersonSectionAdminPass() {
        PersonDAO personDAO = mock(PersonDAO.class);
        PersonRolesDAO personRolesDao = mock(PersonRolesDAO.class);

        Person expectedPerson = new Person(1,"user", "pass");
        String section = "/section";
        AccessOperationType access = AccessOperationType.Write;
        Role expectedRole = new Role(section, "admin","");
        List<Role> roles = new ArrayList<>();
        roles.add(expectedRole);
        PersonRole personRoles = new PersonRole(expectedPerson,roles);


        when(personDAO.getRoleWhereStringFor(expectedPerson)).thenReturn("id = 1");
        when(personRolesDao.load("id = 1")).thenReturn(personRoles);


        IAuthorizationService IAuthorizationService = new AuthorizationService(personDAO, personRolesDao);

        boolean result = IAuthorizationService.Authorize(expectedPerson,"/section/subsection",access);

        assertTrue(result);
    }

    @org.junit.jupiter.api.Test
    void authorizePersonRootAdminPass() {
        PersonDAO personDAO = mock(PersonDAO.class);
        PersonRolesDAO personRolesDao = mock(PersonRolesDAO.class);

        Person expectedPerson = new Person(1,"user", "pass");
        String section = "/";
        AccessOperationType access = AccessOperationType.Write;
        Role expectedRole = new Role(section, "admin","");
        List<Role> roles = new ArrayList<>();
        roles.add(expectedRole);
        PersonRole personRoles = new PersonRole(expectedPerson,roles);


        when(personDAO.getRoleWhereStringFor(expectedPerson)).thenReturn("id = 1");
        when(personRolesDao.load("id = 1")).thenReturn(personRoles);


        IAuthorizationService IAuthorizationService = new AuthorizationService(personDAO, personRolesDao);

        boolean result = IAuthorizationService.Authorize(expectedPerson,"/section/subsection",access);

        assertTrue(result);
    }


    @Test
    void getUpperLeverSubsubsection() {
        String result = IAuthorizationService.getUpperLever("/section/subsection/subsubsection");
        assertEquals("/section/subsection", result);
    }

    @Test
    void getUpperLeverSubsection() {
        String result = IAuthorizationService.getUpperLever("/section/subsection");
        assertEquals("/section", result);
    }

    @Test
    void getUpperLeverSection() {
        String result = IAuthorizationService.getUpperLever("/section");
        assertEquals("/", result);
    }

    @Test
    void getUpperLeverRoot() {
        String result = IAuthorizationService.getUpperLever("/");
        assertEquals("", result);
    }
}