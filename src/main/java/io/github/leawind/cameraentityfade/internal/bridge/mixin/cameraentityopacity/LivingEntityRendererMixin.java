package io.github.leawind.cameraentityfade.internal.bridge.mixin.cameraentityopacity;

import io.github.leawind.cameraentityfade.internal.bridge.CameraEntityRenderState;
/*? if >=1.21.11 {*/
import io.github.leawind.cameraentityfade.internal.bridge.CameraEntityRenderTypes;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*? } else {*/
/*import org.spongepowered.asm.mixin.injection.ModifyVariable;
*//*? }*/
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntityRenderer.class)
abstract class LivingEntityRendererMixin {
  /*? if >=1.21.11 {*/
  @Shadow
  public abstract Identifier getTextureLocation(LivingEntityRenderState state);

  /// The extracted renderer needs both a translucent pipeline and the item-entity output target.
  /// This also avoids the back-face culling used by vanilla's force-transparent branch.
  @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
  private void useCameraEntityRenderType(
      LivingEntityRenderState state,
      boolean isBodyVisible,
      boolean forceTransparent,
      boolean appearGlowing,
      CallbackInfoReturnable<RenderType> ci) {
    if (CameraEntityRenderState.isApplyingTransparency()) {
      ci.setReturnValue(
          CameraEntityRenderTypes.entityTranslucentItemTarget(getTextureLocation(state)));
    }
  }
  /*? } else {*/
  /*
  /// Reuses vanilla's force-transparent branch so the entity body is submitted to a blending
  /// RenderType before its vertex/model color receives the smoothed alpha.
  @ModifyVariable(method = "getRenderType", at = @At("HEAD"), argsOnly = true, ordinal = 1)
  private boolean forceCameraEntityTransparency(boolean forceTransparent) {
    return forceTransparent || CameraEntityRenderState.isApplyingTransparency();
  }
  */
  /*? }*/
}
