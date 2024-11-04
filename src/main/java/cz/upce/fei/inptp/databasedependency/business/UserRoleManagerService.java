package cz.upce.fei.inptp.databasedependency.business;

import cz.upce.fei.inptp.databasedependency.dao.PersonRolesDAO;
import cz.upce.fei.inptp.databasedependency.entity.Person;

public class UserRoleManagerService {

    PersonRolesDAO personRolesDAO;

    public UserRoleManagerService(PersonRolesDAO personRolesDAO) {
        this.personRolesDAO = personRolesDAO;
    }

    public Person createUser(String name, String password){
        return null;
    }

    public boolean deleteUser(Person p){
        return false;
    }

    public boolean changePassword(Person p, String newPassword){
        return false;
    }
}
