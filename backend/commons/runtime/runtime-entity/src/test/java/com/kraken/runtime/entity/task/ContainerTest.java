package com.kraken.runtime.entity.task;

import com.kraken.runtime.entity.task.Container;
import com.kraken.runtime.entity.task.ContainerStatus;
import org.junit.Test;

import static com.kraken.test.utils.TestUtils.shouldPassAll;
import static org.assertj.core.api.Assertions.assertThat;

public class ContainerTest {

  public static final Container CONTAINER = Container.builder()
      .id("id")
      .name("name")
      .hostId("hostId")
      .startDate(42L)
      .status(ContainerStatus.STARTING)
      .label("label")
      .build();


  @Test
  public void shouldPassTestUtils() {
    shouldPassAll(CONTAINER);
  }

  @Test
  public void shouldWither() {
    assertThat(CONTAINER.withStatus(ContainerStatus.READY).getStatus()).isEqualTo(ContainerStatus.READY);
  }
}
