package io.github.leawind.cameraentityfade.internal.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ExponentialSmoothingTest {
  @Test
  void reachesHalfwayAfterOneHalfLife() {
    assertEquals(0.5, ExponentialSmoothing.interpolate(1.0, 0.0, 0.1, 0.1), 1.0e-12);
  }

  @Test
  void zeroHalfLifeAppliesImmediately() {
    assertEquals(0.25, ExponentialSmoothing.interpolate(1.0, 0.25, 0.0, 0.0), 1.0e-12);
  }

  @Test
  void rejectsInvalidTiming() {
    assertThrows(
        IllegalArgumentException.class,
        () -> ExponentialSmoothing.interpolate(1.0, 0.0, -0.1, 0.1));
  }
}
