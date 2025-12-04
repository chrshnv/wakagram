CREATE TABLE IF NOT EXISTS messages (
	id TEXT NOT NULL,
	user_id BIGINT NOT NULL,
	CONSTRAINT messages_pk PRIMARY KEY (id),
	CONSTRAINT messages_fk FOREIGN KEY (user_id) REFERENCES users(id)
);
