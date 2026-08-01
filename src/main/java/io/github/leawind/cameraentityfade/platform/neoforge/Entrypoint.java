package io.github.leawind.cameraentityfade.platform.neoforge;

/*? if neoforge {*/
/*import io.github.leawind.cameraentityfade.CameraEntityFade;
import io.github.leawind.cameraentityfade.internal.logic.CameraEntityFadeRuntime;
/^? if >=1.21.11 {^/
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = CameraEntityFade.MOD_ID, dist = Dist.CLIENT)
public final class Entrypoint {
  public Entrypoint() {
    CameraEntityFadeRuntime.getInstance().initialize();
  }
}
/^? } else {^/
/^import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(CameraEntityFade.MOD_ID)
public final class Entrypoint {
  public Entrypoint() {
    if (FMLEnvironment.dist == Dist.CLIENT) {
      CameraEntityFadeRuntime.getInstance().initialize();
    }
  }
}
^//^? }^/
*//*?}*/
