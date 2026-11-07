package io.github.leawind.cameraentityfade.internal.logic;

/// Maps the camera's shortest distance from its entity bounds to a target opacity.
final class CameraEntityOpacityPolicy {
  // The old implementation reached full opacity one block from the entity eye and reserved the
  // innermost 0.36 blocks for full transparency. Measuring from the AABB removes that inner span.
  // Raised from 0.64 so the fade starts a little further away from the entity.
  static final double FADE_DISTANCE = 1.0;

  private CameraEntityOpacityPolicy() {}

  static double targetOpacity(double distanceToBounds) {
    if (!Double.isFinite(distanceToBounds)) {
      return 1.0;
    }
    return Math.max(0.0, Math.min(1.0, distanceToBounds / FADE_DISTANCE));
  }
}
