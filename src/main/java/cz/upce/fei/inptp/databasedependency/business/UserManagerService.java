package cz.upce.fei.inptp.databasedependency.business;

import cz.upce.fei.inptp.databasedependency.dao.PersonDAO;
import cz.upce.fei.inptp.databasedependency.entity.Person;

public class UserManagerService {
    PersonDAO personDao;

    public UserManagerService(PersonDAO personDao) {
        this.personDao = personDao;
    }

    public Person CreateUser(String name, String password) {
        Person person = null;
        try {
            person = new Person(1, name, IAuthenticationService.encryptPassword(password));
            personDao.save(person);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    public boolean DeleteUser(Person p){
        //TODO implement personDAO delete
        return false;
    }

    public boolean ChangePassword(Person p, String newPassword){
        try {
            p.setPassword(IAuthenticationService.encryptPassword(newPassword));
            personDao.save(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
