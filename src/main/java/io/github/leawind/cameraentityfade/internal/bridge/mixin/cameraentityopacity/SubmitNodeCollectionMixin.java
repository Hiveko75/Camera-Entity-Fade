/*? if >=1.21.11 {*/
package io.github.leawind.cameraentityfade.internal.bridge.mixin.cameraentityopacity;

import io.github.leawind.cameraentityfade.internal.bridge.CameraEntityRenderState;
import net.minecraft.client.renderer.SubmitNodeCollection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SubmitNodeCollection.class)
abstract class SubmitNodeCollectionMixin {
  @ModifyVariable(method = "submitModel", at = @At("HEAD"), index = 7, argsOnly = true)
  private int applyCameraEntityOpacity(int color) {
    return CameraEntityRenderState.applyOpacity(color);
  }

  /*? if <26.2 {*/
  @ModifyVariable(
      method = "submitModelPart",
      at = @At("HEAD"),
      index = 9,
      argsOnly = true,
      require = 0)
  private int applyCameraEntityOpacityToModelPart(int color) {
    return CameraEntityRenderState.applyOpacity(color);
  }
  /*? }*/
}
/*? } else {*/
/*package io.github.leawind.cameraentityfade.internal.bridge.mixin.cameraentityopacity;
*//*? }*/
