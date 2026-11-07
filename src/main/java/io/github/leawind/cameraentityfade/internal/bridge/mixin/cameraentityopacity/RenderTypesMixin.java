/*? if >=1.21.11 {*/
package io.github.leawind.cameraentityfade.internal.bridge.mixin.cameraentityopacity;

import io.github.leawind.cameraentityfade.internal.bridge.CameraEntityRenderState;
import io.github.leawind.cameraentityfade.internal.bridge.CameraEntityRenderTypes;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderTypes.class)
abstract class RenderTypesMixin {
  @Inject(method = "armorCutoutNoCull", at = @At("HEAD"), cancellable = true)
  private static void useTranslucentCameraEntityArmor(
      Identifier texture, CallbackInfoReturnable<RenderType> ci) {
    if (CameraEntityRenderState.isApplyingTransparency()) {
      ci.setReturnValue(CameraEntityRenderTypes.armorTranslucentItemTarget(texture));
    }
  }

  // 26.3 draws armor trims with their own cutout render type (paletted trim textures), so they
  // stayed opaque while the rest of the armor faded. Mods that only make trims full-bright
  // (e.g. Glowing Trims) still go through this method.
  /*? if >=26.3 {*/
  /*@Inject(method = "armorTrim", at = @At("HEAD"), cancellable = true)
  private static void useTranslucentCameraEntityArmorTrim(
      Identifier texture, boolean decal, CallbackInfoReturnable<RenderType> ci) {
    if (CameraEntityRenderState.isApplyingTransparency()) {
      ci.setReturnValue(CameraEntityRenderTypes.armorTranslucentItemTarget(texture));
    }
  }
  *//*? }*/
}
/*? } else {*/
/*
package io.github.leawind.cameraentityfade.internal.bridge.mixin.cameraentityopacity;
*/
/*? }*/
