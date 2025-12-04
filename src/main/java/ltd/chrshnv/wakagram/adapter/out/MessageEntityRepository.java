package ltd.chrshnv.wakagram.adapter.out;

import org.jspecify.annotations.NonNull;
import org.springframework.data.repository.CrudRepository;

public interface MessageEntityRepository extends CrudRepository<@NonNull MessageEntity, @NonNull String> {
}
