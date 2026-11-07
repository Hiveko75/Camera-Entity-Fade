package io.github.leawind.cameraentityfade.internal.bridge.mixin.cameraentityopacity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.leawind.cameraentityfade.internal.bridge.CameraEntityRenderState;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
/*? if >=1.21.11 {*/
import io.github.leawind.cameraentityfade.internal.bridge.CameraEntityRenderTypes;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
/*? } else {*/
/*import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
*//*? }*/
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = CapeLayer.class, priority = 500)
abstract class CapeLayerMixin {
  /*? if >=26.3 {*/
  /*@WrapOperation(
      method = "submit",
      at =
          @At(
              value = "INVOKE",
              target =
                  "Lnet/minecraft/client/renderer/SubmitNodeCollector;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/rendertype/RenderType;III)V"),
      require = 0)
  private void useTranslucentCameraEntityCape(
      SubmitNodeCollector collector,
      Model<?> model,
      Object stateObject,
      PoseStack poseStack,
      RenderType renderType,
      int lightCoords,
      int overlayCoords,
      int outlineColor,
      Operation<Void> original) {
    if (CameraEntityRenderState.isApplyingTransparency()) {
      AvatarRenderState state = (AvatarRenderState) stateObject;
      renderType =
          CameraEntityRenderTypes.entityTranslucentItemTarget(
              state.skin.cape().texturePath());
    }
    original.call(
        collector,
        model,
        stateObject,
        poseStack,
        renderType,
        lightCoords,
        overlayCoords,
        outlineColor);
  }
  *//*? } else if >=1.21.11 {*/
  @WrapOperation(
      method = "submit",
      at =
          @At(
              value = "INVOKE",
              target =
                  "Lnet/minecraft/client/renderer/SubmitNodeCollector;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/rendertype/RenderType;IIILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V"),
      require = 0)
  private void useTranslucentCameraEntityCape(
      SubmitNodeCollector collector,
      Model<?> model,
      Object stateObject,
      PoseStack poseStack,
      RenderType renderType,
      int lightCoords,
      int overlayCoords,
      int outlineColor,
      ModelFeatureRenderer.CrumblingOverlay crumblingOverlay,
      Operation<Void> original) {
    if (CameraEntityRenderState.isApplyingTransparency()) {
      AvatarRenderState state = (AvatarRenderState) stateObject;
      renderType =
          CameraEntityRenderTypes.entityTranslucentItemTarget(
              state.skin.cape().texturePath());
    }
    original.call(
        collector,
        model,
        stateObject,
        poseStack,
        renderType,
        lightCoords,
        overlayCoords,
        outlineColor,
        crumblingOverlay);
  }
  /*? } else if >=1.20.4 {*/
  /*@WrapOperation(
      method = "render",
      at =
          @At(
              value = "INVOKE",
              target =
                  "Lnet/minecraft/client/renderer/MultiBufferSource;getBuffer(Lnet/minecraft/client/renderer/RenderType;)Lcom/mojang/blaze3d/vertex/VertexConsumer;"),
      require = 0)
  private VertexConsumer useTranslucentCameraEntityCape(
      MultiBufferSource buffers,
      RenderType renderType,
      Operation<VertexConsumer> original,
      PoseStack poseStack,
      MultiBufferSource targetBuffers,
      int lightCoords,
      AbstractClientPlayer player,
      float limbSwing,
      float limbSwingAmount,
      float partialTick,
      float ageInTicks,
      float netHeadYaw,
      float headPitch) {
    if (CameraEntityRenderState.isApplyingTransparency()) {
      renderType = RenderType.entityTranslucent(player.getSkin().capeTexture());
    }
    return original.call(buffers, renderType);
  }
  *//*? } else {*/
  /*@WrapOperation(
      method = "render",
      at =
          @At(
              value = "INVOKE",
              target =
                  "Lnet/minecraft/client/renderer/MultiBufferSource;getBuffer(Lnet/minecraft/client/renderer/RenderType;)Lcom/mojang/blaze3d/vertex/VertexConsumer;"),
      require = 0)
  private VertexConsumer useTranslucentCameraEntityCape(
      MultiBufferSource buffers,
      RenderType renderType,
      Operation<VertexConsumer> original,
      PoseStack poseStack,
      MultiBufferSource targetBuffers,
      int lightCoords,
      AbstractClientPlayer player,
      float limbSwing,
      float limbSwingAmount,
      float partialTick,
      float ageInTicks,
      float netHeadYaw,
      float headPitch) {
    if (CameraEntityRenderState.isApplyingTransparency()) {
      renderType = RenderType.entityTranslucent(player.getCloakTextureLocation());
    }
    return original.call(buffers, renderType);
  }
  *//*? }*/
}
