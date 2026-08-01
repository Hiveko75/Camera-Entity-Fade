package io.github.leawind.cameraentityfade.internal.bridge;

import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

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
}
