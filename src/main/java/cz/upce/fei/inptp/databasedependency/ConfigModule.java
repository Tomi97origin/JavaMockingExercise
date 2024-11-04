package cz.upce.fei.inptp.databasedependency;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import cz.upce.fei.inptp.databasedependency.business.AuthenticationService;
import cz.upce.fei.inptp.databasedependency.business.AuthorizationService;
import cz.upce.fei.inptp.databasedependency.business.IAuthenticationService;
import cz.upce.fei.inptp.databasedependency.business.IAuthorizationService;
import cz.upce.fei.inptp.databasedependency.dao.*;
import cz.upce.fei.inptp.databasedependency.entity.Person;
import cz.upce.fei.inptp.databasedependency.entity.PersonRole;

public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IDatabase.class).to(Database.class).asEagerSingleton();
        bind(new TypeLiteral<DAO<Person>>(){}).to(PersonDAO.class);
        bind(new TypeLiteral<DAO<PersonRole>>(){}).to(PersonRolesDAO.class);
        bind(IAuthenticationService.class).to(AuthenticationService.class);
        bind(IAuthorizationService.class).to(AuthorizationService.class);;
    }
}
