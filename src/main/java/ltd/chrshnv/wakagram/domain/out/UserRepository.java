package ltd.chrshnv.wakagram.domain.out;

import ltd.chrshnv.wakagram.domain.model.User;

import java.util.Optional;

public interface UserRepository {
	Optional<User> findById(Long id);

	void save(User user, boolean isNew);
}
