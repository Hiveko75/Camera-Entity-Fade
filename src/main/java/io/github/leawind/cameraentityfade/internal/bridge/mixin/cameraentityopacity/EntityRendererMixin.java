/*? if >=1.21.11 {*/
package io.github.leawind.cameraentityfade.internal.bridge.mixin.cameraentityopacity;

import io.github.leawind.cameraentityfade.internal.bridge.CameraEntityRenderState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityRenderer.class)
abstract class EntityRendererMixin {
  @Inject(
      method =
          "createRenderState(Lnet/minecraft/world/entity/Entity;F)Lnet/minecraft/client/renderer/entity/state/EntityRenderState;",
      at = @At("RETURN"))
  private void captureCameraEntityRenderState(
      Entity entity, float partialTick, CallbackInfoReturnable<EntityRenderState> ci) {
    if (entity == Minecraft.getInstance().getCameraEntity()) {
      CameraEntityRenderState.setCameraEntityRenderState(ci.getReturnValue());
    }
  }
}
/*? } else {*/
/*
package io.github.leawind.cameraentityfade.internal.bridge.mixin.cameraentityopacity;
*/
/*? }*/
