package ltd.chrshnv.wakagram.adapter.out;

import ltd.chrshnv.wakagram.domain.model.Message;
import org.jspecify.annotations.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * DTO for {@link Message}
 */
@Table("messages")
public class MessageEntity implements Persistable<@NonNull String> {
	@Id
	@Column("id")
	private String id;

	@Column("user_id")
	private Long userId;

	@Transient
	private Boolean isNew = false;

	public MessageEntity(String id, Long userId) {
		this.id = id;
		this.userId = userId;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public boolean isNew() {
		return isNew;
	}

	public void setNew(Boolean aNew) {
		isNew = aNew;
	}
}
