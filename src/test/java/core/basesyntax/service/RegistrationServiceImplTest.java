package core.basesyntax.service;

import core.basesyntax.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceImplTest {
    private RegistrationService registrationService = new RegistrationServiceImpl();
    private static User user;

    @Test
    void validUser_Ok() {
        user = new User();
        user.setAge(23);
        user.setLogin("ddkfdk");
        user.setPassword("ddkskdsd");

        User registratedUser = registrationService.register(user);

        assertNotNull(registratedUser);
        assertEquals("ddkfdk",registratedUser.getLogin());
    }

    @Test
    void noUser_Ok() {
        user = new User();
        user.setLogin("dkoksw");
        user.setPassword("sldslmdlsmdlm");
        user.setAge(20);
        registrationService.register(user);

        User user2 = new User();
        user2.setLogin("dkoksw");
        user2.setPassword("dfdfdf");
        user2.setAge(33);
        assertThrows(RegistrationException.class,
                () -> registrationService.register(user2));
    }

    @Test
    void isTooShortLofin_notOk() {
        user = new User();
        user.setLogin("dkok");
        user.setPassword("sldslmdlsmdlm");
        user.setAge(20);

        assertThrows(RegistrationException.class,
                () -> registrationService.register(user));
    }

    @Test
    void isTooShortPassword_notOk() {
        user = new User();
        user.setLogin("dkok");
        user.setPassword("sld");
        user.setAge(20);

        assertThrows(RegistrationException.class,
                () -> registrationService.register(user));
    }

    @Test
    void isAgeMoreThan18_Ok() {
        user = new User();
        user.setLogin("dkok");
        user.setPassword("sldslmdlsmdlm");
        user.setAge(17);

        assertThrows(RegistrationException.class,
                () -> registrationService.register(user));
    }
}