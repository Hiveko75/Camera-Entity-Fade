/*? if fabric {*/
package io.github.leawind.cameraentityfade.platform.fabric;

import io.github.leawind.cameraentityfade.internal.logic.CameraEntityFadeRuntime;
import net.fabricmc.api.ClientModInitializer;

@SuppressWarnings("unused")
public final class Entrypoint implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    CameraEntityFadeRuntime.getInstance().initialize();
  }
}
/*?}*/
