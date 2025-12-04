package ltd.chrshnv.wakagram.domain.out;

import ltd.chrshnv.wakagram.domain.model.BaseEvent;

public interface EventPublisher {
	void publish(BaseEvent event);
}
