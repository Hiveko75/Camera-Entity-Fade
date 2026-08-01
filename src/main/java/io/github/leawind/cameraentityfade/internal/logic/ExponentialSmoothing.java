package io.github.leawind.cameraentityfade.internal.logic;

/// Frame-rate-independent exponential smoothing helpers.
final class ExponentialSmoothing {
  private ExponentialSmoothing() {}

  static double interpolate(
      double current, double target, double deltaSeconds, double halfLifeSeconds) {
    if (!Double.isFinite(current)
        || !Double.isFinite(target)
        || !Double.isFinite(deltaSeconds)
        || !Double.isFinite(halfLifeSeconds)
        || deltaSeconds < 0.0
        || halfLifeSeconds < 0.0) {
      throw new IllegalArgumentException("Smoothing inputs must be finite and non-negative");
    }
    if (halfLifeSeconds == 0.0) {
      return target;
    }
    if (deltaSeconds == 0.0) {
      return current;
    }
    double alpha = Math.min(1.0, 1.0 - Math.pow(2.0, -deltaSeconds / halfLifeSeconds));
    return current + (target - current) * alpha;
  }
}
