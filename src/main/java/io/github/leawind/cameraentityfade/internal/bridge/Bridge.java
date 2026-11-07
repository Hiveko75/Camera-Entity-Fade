package io.github.leawind.cameraentityfade.internal.bridge;

import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
/*? if >=26.2 {*/
import org.joml.Vector3fc;
/*? }*/

/// Small home for source-version differences needed by otherwise shared logic.
public final class Bridge {
  private Bridge() {}

  public static Vec3 cameraPosition(Minecraft minecraft) {
    /*? if >=26.2 {*/
    return minecraft.gameRenderer.mainCamera().position();
    /*? } else if >=1.21.11 {*/
    /*return minecraft.gameRenderer.getMainCamera().position();
    *//*? } else {*/
    /*return minecraft.gameRenderer.getMainCamera().getPosition();
    *//*? }*/
  }

  /// Direction the camera, and therefore the crosshair, is pointing; null where unsupported.
  public static @Nullable Vec3 cameraForward(Minecraft minecraft) {
    /*? if >=26.2 {*/
    Vector3fc forward = minecraft.gameRenderer.mainCamera().forwardVector();
    return new Vec3(forward.x(), forward.y(), forward.z());
    /*? } else {*/
    /*return null;
    *//*? }*/
  }
}
