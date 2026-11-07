package io.github.leawind.cameraentityfade.internal.logic;

import io.github.leawind.cameraentityfade.CameraEntityFade;
import io.github.leawind.cameraentityfade.internal.bridge.Bridge;
import io.github.leawind.cameraentityfade.internal.bridge.events.CameraEntityOpacityEvent;
import io.github.leawind.cameraentityfade.internal.bridge.events.ClientTickEvent;
import io.github.leawind.perspectiveapi.api.Perspective;
import io.github.leawind.perspectiveapi.api.PerspectiveAPI;
import io.github.leawind.perspectiveapi.api.PerspectiveBehavior.BaseType;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

/// Owns the client-tick policy and the render-sampled opacity state.
public final class CameraEntityFadeRuntime {
  private static final double CLIENT_TICK_SECONDS = 0.05;
  private static final double OPACITY_HALF_LIFE_SECONDS = 0.0625;
  private static final String THIRD_PERSON_TRAIT = "third_person";
  private static final CameraEntityFadeRuntime INSTANCE = new CameraEntityFadeRuntime();

  private final CameraEntityOpacity opacity = new CameraEntityOpacity();
  private boolean initialized;
  private boolean active;

  private CameraEntityFadeRuntime() {}

  public static CameraEntityFadeRuntime getInstance() {
    return INSTANCE;
  }

  public synchronized void initialize() {
    if (initialized) {
      return;
    }
    initialized = true;
    CameraEntityOpacityEvent.register(this::sampleOpacity);
    ClientTickEvent.register(this::onClientTick);
    CameraEntityFade.LOGGER.info("{} initialized", CameraEntityFade.MOD_NAME);
  }

  public void onClientTick() {
    Minecraft minecraft = Minecraft.getInstance();
    Entity cameraEntity = minecraft.getCameraEntity();
    if (minecraft.level == null
        || cameraEntity == null
        || cameraEntity.isSpectator()
        || !usesThirdPersonCamera()) {
      deactivate();
      return;
    }

    active = true;
    Vec3 cameraPosition = Bridge.cameraPosition(minecraft);
    AABB bounds = cameraEntity.getBoundingBox();
    double distanceToBounds = Math.sqrt(bounds.distanceToSqr(cameraPosition));
    double target = CameraEntityOpacityPolicy.targetOpacity(distanceToBounds);
    if (crosshairOnEntity(minecraft, cameraPosition, bounds)) {
      target = Math.min(target, CameraEntityOpacityPolicy.CROSSHAIR_OPACITY);
    }
    opacity.setTarget(target);
    opacity.update(CLIENT_TICK_SECONDS, OPACITY_HALF_LIFE_SECONDS);
  }

  /// True when the ray through the crosshair hits the central part of the camera entity's bounding
  /// box (see CROSSHAIR_WIDTH_FRACTION / CROSSHAIR_HEIGHT_FRACTION), not just its edge.
  private static boolean crosshairOnEntity(Minecraft minecraft, Vec3 cameraPosition, AABB bounds) {
    Vec3 forward = Bridge.cameraForward(minecraft);
    if (forward == null || bounds.contains(cameraPosition)) {
      return false;
    }
    AABB core =
        bounds.deflate(
            bounds.getXsize() * (1.0 - CameraEntityOpacityPolicy.CROSSHAIR_WIDTH_FRACTION) / 2.0,
            bounds.getYsize() * (1.0 - CameraEntityOpacityPolicy.CROSSHAIR_HEIGHT_FRACTION) / 2.0,
            bounds.getZsize() * (1.0 - CameraEntityOpacityPolicy.CROSSHAIR_WIDTH_FRACTION) / 2.0);
    Vec3 end = cameraPosition.add(forward.scale(CameraEntityOpacityPolicy.CROSSHAIR_RAY_LENGTH));
    return core.clip(cameraPosition, end).isPresent();
  }

  float sampleOpacity(float partialTick) {
    return active ? opacity.sample(partialTick) : 1.0f;
  }

  private void deactivate() {
    if (active) {
      active = false;
      opacity.reset();
    }
  }

  private static boolean usesThirdPersonCamera() {
    try {
      Perspective current = PerspectiveAPI.getCurrent();
      if (current == null) {
        return false;
      }
      var info = current.info();
      return info.hasTrait(THIRD_PERSON_TRAIT) || info.baseType() != BaseType.FIRST_PERSON;
    } catch (IllegalStateException ignored) {
      return false;
    }
  }
}
