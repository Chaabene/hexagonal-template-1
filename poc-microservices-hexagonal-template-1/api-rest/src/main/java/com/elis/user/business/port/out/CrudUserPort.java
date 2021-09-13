package com.elis.user.business.port.out;

import com.elis.user.business.domain.User;

import java.util.List;
import java.util.Optional;


public interface CrudUserPort {

    User saveUser(final User user);

    void deleteUser(final Long userId);

    List<User> findAllUser();

    Optional<User> findUserById(final Long userId);

    void updateUser(final Long userId, final User User);

    boolean existsUser(final Long userId);

}
