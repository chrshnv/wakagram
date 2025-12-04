package ltd.chrshnv.wakagram.adapter.out;

import ltd.chrshnv.wakagram.adapter.mapper.UserMapper;
import ltd.chrshnv.wakagram.domain.model.User;
import ltd.chrshnv.wakagram.domain.out.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserPersistenceGateway implements UserRepository {

	private final UserEntityRepository userEntityRepository;

	private final UserMapper userMapper;

	public UserPersistenceGateway(UserEntityRepository userEntityRepository,
								  UserMapper userMapper) {
		this.userEntityRepository = userEntityRepository;
		this.userMapper = userMapper;
	}

	@Override
	public Optional<User> findById(Long id) {
		return userEntityRepository
			.findById(id)
			.map(userMapper::toDomain);
	}

	@Override
	public void save(User user, boolean isNew) {
		UserEntity entity = userMapper.toUserEntity(user, isNew);

		userEntityRepository.save(entity);
	}
}
