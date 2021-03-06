package com.kraken.runtime.tasks.configuration;

import com.kraken.runtime.entity.task.TaskType;
import com.kraken.runtime.tasks.configuration.entity.TaskConfiguration;
import reactor.core.publisher.Mono;

public interface TaskConfigurationService {

  Mono<TaskConfiguration> getConfiguration(TaskType taskType);

}
