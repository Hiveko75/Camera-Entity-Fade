/*? if <1.21.11 {*/
/*
package io.github.leawind.cameraentityfade.internal.bridge.mixin.cameraentityopacity;

import io.github.leawind.cameraentityfade.internal.bridge.CameraEntityRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
abstract class LevelRendererMixin {
  @Inject(method = "renderEntity", at = @At("HEAD"), cancellable = true)
  private void beforeRenderCameraEntity(
      Entity entity,
      double cameraX,
      double cameraY,
      double cameraZ,
      float partialTick,
      PoseStack poseStack,
      MultiBufferSource bufferSource,
      CallbackInfo ci) {
    if (entity != Minecraft.getInstance().getCameraEntity()) {
      return;
    }
    if (CameraEntityRenderState.begin(partialTick)) {
      CameraEntityRenderState.end();
      ci.cancel();
    }
  }

  @Inject(method = "renderEntity", at = @At("TAIL"))
  private void afterRenderCameraEntity(
      Entity entity,
      double cameraX,
      double cameraY,
      double cameraZ,
      float partialTick,
      PoseStack poseStack,
      MultiBufferSource bufferSource,
      CallbackInfo ci) {
    if (entity == Minecraft.getInstance().getCameraEntity()) {
      CameraEntityRenderState.end();
    }
  }
}
*/
/*? }*/
