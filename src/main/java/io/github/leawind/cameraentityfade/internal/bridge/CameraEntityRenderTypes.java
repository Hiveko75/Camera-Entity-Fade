/*? if >=1.21.11 {*/
package io.github.leawind.cameraentityfade.internal.bridge;

import io.github.leawind.cameraentityfade.internal.bridge.mixin.cameraentityopacity.RenderTypeAccessor;
import java.util.function.Function;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.rendertype.LayeringTransform;
import net.minecraft.client.renderer.rendertype.OutputTarget;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;

/// Render types used while applying alpha to an extracted camera-entity render state.
public final class CameraEntityRenderTypes {
  private static final Function<Identifier, RenderType> ENTITY_TRANSLUCENT_ITEM_TARGET =
      Util.memoize(
          texture ->
              RenderTypeAccessor.cameraEntityFade$create(
                  "camera_entity_fade_entity_translucent_item_target",
                  RenderSetup.builder(RenderPipelines.ENTITY_TRANSLUCENT)
                      .withTexture("Sampler0", texture)
                      .setOutputTarget(OutputTarget.ITEM_ENTITY_TARGET)
                      .useLightmap()
                      .useOverlay()
                      .affectsCrumbling()
                      .sortOnUpload()
                      .setOutline(RenderSetup.OutlineProperty.AFFECTS_OUTLINE)
                      .createRenderSetup()));

  private static final Function<Identifier, RenderType> ARMOR_TRANSLUCENT_ITEM_TARGET =
      Util.memoize(
          texture ->
              RenderTypeAccessor.cameraEntityFade$create(
                  "camera_entity_fade_armor_translucent_item_target",
                  RenderSetup.builder(RenderPipelines.ARMOR_TRANSLUCENT)
                      .withTexture("Sampler0", texture)
                      .useLightmap()
                      .useOverlay()
                      .setLayeringTransform(LayeringTransform.VIEW_OFFSET_Z_LAYERING)
                      .affectsCrumbling()
                      .sortOnUpload()
                      .setOutline(RenderSetup.OutlineProperty.AFFECTS_OUTLINE)
                      .setOutputTarget(OutputTarget.ITEM_ENTITY_TARGET)
                      .createRenderSetup()));

  private CameraEntityRenderTypes() {}

  public static RenderType entityTranslucentItemTarget(Identifier texture) {
    return ENTITY_TRANSLUCENT_ITEM_TARGET.apply(texture);
  }

  public static RenderType armorTranslucentItemTarget(Identifier texture) {
    return ARMOR_TRANSLUCENT_ITEM_TARGET.apply(texture);
  }
}
/*? } else {*/
/*package io.github.leawind.cameraentityfade.internal.bridge;
*//*? }*/
