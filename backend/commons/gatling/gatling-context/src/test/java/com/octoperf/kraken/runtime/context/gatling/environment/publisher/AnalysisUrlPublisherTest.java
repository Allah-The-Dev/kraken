package com.octoperf.kraken.runtime.context.gatling.environment.publisher;

import com.octoperf.kraken.config.analysis.client.api.AnalysisClientProperties;
import com.octoperf.kraken.runtime.context.entity.ExecutionContextBuilderTest;
import com.octoperf.kraken.runtime.entity.environment.ExecutionEnvironmentEntry;
import com.octoperf.kraken.runtime.entity.task.TaskType;
import com.octoperf.kraken.tests.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;


import static com.octoperf.kraken.tools.environment.KrakenEnvironmentKeys.KRAKEN_ANALYSIS_URL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AnalysisUrlPublisher.class})
@EnableAutoConfiguration
public class AnalysisUrlPublisherTest {

  @Autowired
  AnalysisUrlPublisher publisher;
  @MockBean
  AnalysisClientProperties properties;

  @BeforeEach
  public void setUp() {
    when(properties.getUrl()).thenReturn("http://localhost");
  }

  @Test
  public void shouldTest() {
    assertThat(publisher.test(TaskType.GATLING_RUN)).isFalse();
    assertThat(publisher.test(TaskType.GATLING_DEBUG)).isTrue();
    assertThat(publisher.test(TaskType.GATLING_RECORD)).isTrue();
  }

  @Test
  public void shouldGet() {
    final var env = publisher.apply(ExecutionContextBuilderTest.EXECUTION_CONTEXT_BUILDER).block();
    assertThat(env).isNotNull();
    assertThat(env.stream().map(ExecutionEnvironmentEntry::getKey).anyMatch(key -> key.equals(KRAKEN_ANALYSIS_URL.toString()))).isTrue();
  }

  @Test
  public void shouldTestUtils(){
    TestUtils.shouldPassNPE(AnalysisUrlPublisher.class);
  }
}
