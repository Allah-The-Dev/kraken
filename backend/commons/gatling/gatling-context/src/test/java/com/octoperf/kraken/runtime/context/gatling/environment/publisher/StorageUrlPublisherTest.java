package com.octoperf.kraken.runtime.context.gatling.environment.publisher;

import com.octoperf.kraken.runtime.context.entity.ExecutionContextBuilderTest;
import com.octoperf.kraken.runtime.entity.environment.ExecutionEnvironmentEntry;
import com.octoperf.kraken.runtime.entity.task.TaskType;
import com.octoperf.kraken.config.storage.client.api.StorageClientProperties;
import com.octoperf.kraken.tests.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static com.octoperf.kraken.tools.environment.KrakenEnvironmentKeys.KRAKEN_STORAGE_URL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StorageUrlPublisher.class)
public class StorageUrlPublisherTest {

  @Autowired
  StorageUrlPublisher publisher;
  @MockBean
  StorageClientProperties properties;

  @BeforeEach
  public void setUp() {
    when(properties.getUrl()).thenReturn("url");
  }

  @Test
  public void shouldTest() {
    assertThat(publisher.test(TaskType.GATLING_RUN)).isTrue();
    assertThat(publisher.test(TaskType.GATLING_DEBUG)).isTrue();
    assertThat(publisher.test(TaskType.GATLING_RECORD)).isTrue();
  }

  @Test
  public void shouldApply() {
    final var env = publisher.apply(ExecutionContextBuilderTest.EXECUTION_CONTEXT_BUILDER).block();
    assertThat(env).isNotNull();
    assertThat(env.stream().map(ExecutionEnvironmentEntry::getKey).anyMatch(key -> key.equals(KRAKEN_STORAGE_URL.name()))).isTrue();
  }

  @Test
  public void shouldTestUtils() {
    TestUtils.shouldPassNPE(StorageUrlPublisher.class);
  }
}
