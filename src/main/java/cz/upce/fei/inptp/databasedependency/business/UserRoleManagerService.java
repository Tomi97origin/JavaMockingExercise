package cz.upce.fei.inptp.databasedependency.business;

import cz.upce.fei.inptp.databasedependency.dao.PersonRolesDAO;

public class UserRoleManagerService {

    PersonRolesDAO personRolesDAO;

    public UserRoleManagerService(PersonRolesDAO personRolesDAO) {
        this.personRolesDAO = personRolesDAO;
    }
}
