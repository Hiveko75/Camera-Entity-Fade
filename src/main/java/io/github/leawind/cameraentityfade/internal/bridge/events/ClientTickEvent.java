package io.github.leawind.cameraentityfade.internal.bridge.events;

import java.util.Objects;

/// Version-neutral client-tick callback installed by the logic composition root.
public final class ClientTickEvent {
  private static Runnable listener;

  private ClientTickEvent() {}

  public static synchronized void register(Runnable newListener) {
    Objects.requireNonNull(newListener);
    if (listener != null) {
      throw new IllegalStateException("Client tick listener is already registered");
    }
    listener = newListener;
  }

  public static void emit() {
    Runnable current = listener;
    if (current != null) {
      current.run();
    }
  }
}
