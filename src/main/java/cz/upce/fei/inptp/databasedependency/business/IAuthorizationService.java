package cz.upce.fei.inptp.databasedependency.business;

import cz.upce.fei.inptp.databasedependency.entity.Person;

public interface IAuthorizationService {
    // TODO: add tests
    // TODO: "/section/subsection/subsubsection" -> "/section/subsection"
    // TODO: "/section/subsection" -> "/section"
    // TODO: "/section" -> "/"
    // TODO: "/" -> ""
    static String getUpperLever(String section) {
        if (section.equals("/")) {
            return "";
        }

        String ret = section.substring(0, section.lastIndexOf("/") + 1);
        if (ret.equals("/")) {
            return ret;
        }
        return ret.substring(0, ret.length() - 1);
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
    boolean Authorize(Person person, String section, AccessOperationType operationType);
}
