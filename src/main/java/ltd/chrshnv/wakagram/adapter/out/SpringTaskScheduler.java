package ltd.chrshnv.wakagram.adapter.out;

import ltd.chrshnv.wakagram.domain.out.TaskScheduler;
import org.springframework.scheduling.concurrent.SimpleAsyncTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class SpringTaskScheduler implements TaskScheduler {

	private final SimpleAsyncTaskScheduler simpleAsyncTaskScheduler;

	public SpringTaskScheduler(SimpleAsyncTaskScheduler simpleAsyncTaskScheduler) {
		this.simpleAsyncTaskScheduler = simpleAsyncTaskScheduler;
	}

	@Override
	public void schedule(Runnable task, Duration delay) {
		simpleAsyncTaskScheduler.scheduleWithFixedDelay(task, delay);
	}
}
