package com.elis.user.adapter.out.persistence;

import com.elis.common.annotation.PersistenceAdapter;
import com.elis.user.adapter.out.persistence.model.UserJpaEntity;
import com.elis.user.business.domain.User;
import com.elis.user.business.port.out.CrudUserPort;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@Transactional
@RequiredArgsConstructor
public class CrudUserAdapter implements CrudUserPort {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User saveUser(final User user) {
        UserJpaEntity userJpaEntity = modelMapper.map(user, UserJpaEntity.class);
        userRepository.save(userJpaEntity);
        return modelMapper.map(userJpaEntity, User.class);
    }

    @Override
    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> findAllUser() {
        List<UserJpaEntity> UsersJpaEntity = userRepository.findAll();
        if (CollectionUtils.isEmpty(UsersJpaEntity)) {
            return null;
        }
        return UsersJpaEntity.stream()
                             .map(userJpaEntity -> modelMapper.map(userJpaEntity, User.class))
                             .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findUserById( final Long userId) {
        return userRepository.findById(userId).map(userJpaEntity -> modelMapper.map(userJpaEntity, User.class));
    }

    @Override
    public void updateUser(final Long userId, final User User) {
        Optional<UserJpaEntity> userJpaEntity = userRepository.findById(userId);
        modelMapper.map(User, userJpaEntity.get());
    }

    @Override
    public boolean existsUser(final Long userId) {
        return userRepository.existsById(userId);
    }
}
