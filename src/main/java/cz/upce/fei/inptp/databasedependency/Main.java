package cz.upce.fei.inptp.databasedependency;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import cz.upce.fei.inptp.databasedependency.business.*;
import cz.upce.fei.inptp.databasedependency.dao.IDatabase;
import cz.upce.fei.inptp.databasedependency.dao.DAO;
import cz.upce.fei.inptp.databasedependency.dao.PersonDAO;
import cz.upce.fei.inptp.databasedependency.dao.PersonRolesDAO;
import cz.upce.fei.inptp.databasedependency.entity.PersonRole;
import cz.upce.fei.inptp.databasedependency.entity.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class Main {

    /*
    TODO: Tasks:
     - Create required unit tests for AuthenticationService
     - Create required unit tests for AuthorizationService
     - Create service UserManagerService with methods:
      - Service MUST depend only on DAO objects, no specific code for DB
      - CreateUser(String name, String password) : Person
      - DeleteUser(Person p) : boolean
      - ChangePassword(Person p, String newPassword) : boolean
     - Create service UserRoleManagerService
      - ...
    */
    public static void main(String[] args) throws SQLException {
        Injector injector = Guice.createInjector(new ConfigModule());

        // Get the Database instance
        IDatabase database = injector.getInstance(IDatabase.class);
        database.open();

        DAO<Person> personDao = injector.getInstance(Key.get(new TypeLiteral<DAO<Person>>(){}));
        
        // create person
        Person person = new Person(10, "Peter", IAuthenticationService.encryptPassword("rafanovsky"));
        personDao.save(person);

        // load person
        person = personDao.load("id = 10");
        System.out.println(person);

        // test authentication
        IAuthenticationService authentication = injector.getInstance(IAuthenticationService.class);
        System.out.println(authentication.Authenticate("Peter", "rafa"));
        System.out.println(authentication.Authenticate("Peter", "rafanovsky"));

        // check user roles
        PersonRole pr = injector.getInstance(Key.get(new TypeLiteral<DAO<PersonRole>>(){})).load("name = 'yui'");
        System.out.println(pr);

        // test authorization
        person = personDao.load("id = 2");
        IAuthorizationService authorization = injector.getInstance(IAuthorizationService.class);
        boolean authorizationResult = authorization.Authorize(person, "/finance/report", AccessOperationType.Read);
        System.out.println(authorizationResult);
        
        
        // load all persons from db
        try {
            Statement statement = database.createStatement();
            ResultSet rs = statement.executeQuery("select * from person");
            while (rs.next()) {
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        database.close();
    }
}
