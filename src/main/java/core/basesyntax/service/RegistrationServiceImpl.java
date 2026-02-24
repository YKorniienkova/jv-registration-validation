package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user == null){
            throw new RegistrationException("User cant be null");
        }
        if (storageDao.get(user.getLogin()) != null) {
            throw new RegistrationException("User already exists");
        }
        if (user.getLogin() == null || user.getLogin().length() < 6) {
            throw new RegistrationException("Too short login");
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new RegistrationException("Too short password");
        }
        if (user.getAge() < 18) {
            throw new RegistrationException("Less than 17 yo");
        }

        return storageDao.add(user);
    }
}
