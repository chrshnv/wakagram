package ltd.chrshnv.wakagram.adapter.out;

import ltd.chrshnv.wakagram.domain.model.User;
import org.jspecify.annotations.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * DTO for {@link User}
 */
@Table("users")
public class UserEntity implements Persistable<@NonNull Long> {
	@Id
	@Column("id")
	private Long id;

	@Column("api_key")
	private String apiKey;

	@Transient
	private boolean isNew = false;

	public UserEntity(Long id, String apiKey) {
		this.id = id;
		this.apiKey = apiKey;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public boolean isNew() {
		return isNew;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void setNew(boolean aNew) {
		isNew = aNew;
	}
}
