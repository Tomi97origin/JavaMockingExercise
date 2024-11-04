package cz.upce.fei.inptp.databasedependency.business;

import com.google.inject.Inject;
import cz.upce.fei.inptp.databasedependency.dao.PersonDAO;
import cz.upce.fei.inptp.databasedependency.entity.PersonRole;
import cz.upce.fei.inptp.databasedependency.entity.Person;
import cz.upce.fei.inptp.databasedependency.entity.Role;
import cz.upce.fei.inptp.databasedependency.dao.DAO;

/**
 * Authorization service. User is authorized to access specified part of system,
 * if he has required access directly to specified part, or to upper level part.
 */
public class AuthorizationService implements IAuthorizationService {

    private PersonDAO persondao;
    private DAO<PersonRole> personRolesDao;

    @Inject
    public AuthorizationService(DAO<Person> persondao, DAO<PersonRole> personRolesDao) {
        this.persondao = (PersonDAO) persondao;
        this.personRolesDao = personRolesDao;
    }

    // TODO: add tests
    // TODO: Authorize(person, "/section/subsection", rw) - PersonRole([Role("/section/subsection", rw)]) - pass
    // TODO: Authorize(person, "/section/subsection", rw) - PersonRole([Role("/section/subsection", ro)]) - fail
    // TODO: Authorize(person, "/section/subsection", rw) - PersonRole([Role("/section", rw)]) - pass
    // TODO: Authorize(person, "/section/subsection", rw) - PersonRole([Role("/section", ro)]) - fail
    // TODO: Authorize(person, "/section/subsection", rw) - PersonRole([Role("/", rw)]) - pass
    // TODO: Authorize(person, "/section/subsection", rw) - PersonRole([Role("/", ro)]) - fail
    // TODO: Authorize(person, "/section/subsection", rw) - PersonRole([Role("/section/subsection", admin)]) - pass
    // TODO: Authorize(person, "/section/subsection", rw) - PersonRole([Role("/section", admin)]) - pass
    // TODO: Authorize(person, "/section/subsection", rw) - PersonRole([Role("/", admin)]) - pass
    @Override
    public boolean Authorize(Person person, String section, AccessOperationType operationType) {
        String roleWhere = persondao.getRoleWhereStringFor(person);

        PersonRole roles = personRolesDao.load(roleWhere);
        if (roles == null) {
            return false;
        }

        do {
            for (Role role : roles.getRoles()) {
                if (role.getSection().equals(section)) {
                    if (role.getAccess().equals(operationType.getOp())) {
                        return true;
                    }

                    if (role.getAccess().equals("admin")) {
                        return true;
                    }

                    return false;
                }
            }

            section = IAuthorizationService.getUpperLever(section);
            //System.out.println("newsection " + section);
        } while (!section.equals(""));

        return false;
    }

}
