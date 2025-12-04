package ltd.chrshnv.wakagram.adapter.out;

import ltd.chrshnv.wakagram.domain.model.BaseEvent;
import ltd.chrshnv.wakagram.domain.out.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringEventPublisher implements EventPublisher {
	private final ApplicationEventPublisher eventPublisher;

	public SpringEventPublisher(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	@Override
	public void publish(BaseEvent event) {
		eventPublisher.publishEvent(event);
	}
}
