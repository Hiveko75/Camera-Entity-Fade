/*? if <1.21.11 {*/
/*
package io.github.leawind.cameraentityfade.internal.bridge.mixin.cameraentityopacity;

import io.github.leawind.cameraentityfade.internal.bridge.CameraEntityRenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderType.class)
abstract class RenderTypeMixin {
  @Inject(method = "armorCutoutNoCull", at = @At("HEAD"), cancellable = true)
  private static void useTranslucentCameraEntityArmor(
      ResourceLocation texture, CallbackInfoReturnable<RenderType> ci) {
    if (CameraEntityRenderState.isApplyingTransparency()) {
      ci.setReturnValue(RenderType.entityTranslucent(texture));
    }
  }
}
*/
/*? }*/
