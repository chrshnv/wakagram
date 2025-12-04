package ltd.chrshnv.wakagram.domain.out;

import java.time.Duration;

public interface TaskScheduler {
	void schedule(Runnable task, Duration delay);
}
