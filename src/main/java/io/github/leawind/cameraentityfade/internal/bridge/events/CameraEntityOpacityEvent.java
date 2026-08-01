package io.github.leawind.cameraentityfade.internal.bridge.events;

import java.util.Objects;

/// Version-neutral render-time query installed by the logic composition root.
public final class CameraEntityOpacityEvent {
  private static Listener listener;

  private CameraEntityOpacityEvent() {}

  public static synchronized void register(Listener newListener) {
    Objects.requireNonNull(newListener);
    if (listener != null) {
      throw new IllegalStateException("Camera entity opacity listener is already registered");
    }
    listener = newListener;
  }

  public static float emit(float partialTick) {
    Listener current = listener;
    if (!Float.isFinite(partialTick) || current == null) {
      return 1.0f;
    }
    float opacity = current.cameraEntityOpacity(partialTick);
    return Float.isFinite(opacity) ? Math.max(0.0f, Math.min(1.0f, opacity)) : 1.0f;
  }

  @FunctionalInterface
  public interface Listener {
    float cameraEntityOpacity(float partialTick);
  }
}
