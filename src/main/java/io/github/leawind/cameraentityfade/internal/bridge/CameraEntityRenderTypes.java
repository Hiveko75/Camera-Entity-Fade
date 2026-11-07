/*? if >=1.21.11 {*/
package io.github.leawind.cameraentityfade.internal.bridge;

import io.github.leawind.cameraentityfade.internal.bridge.mixin.cameraentityopacity.RenderTypeAccessor;
import java.util.function.Function;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.rendertype.LayeringTransform;
/*? if <26.3 {*/
import net.minecraft.client.renderer.rendertype.OutputTarget;
/*? }*/
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
                      /*? if >=26.3 {*/
                      /*.setOitPipelines(RenderPipelines.OIT_ENTITY)
                      *//*? }*/
                      .withTexture("Sampler0", texture)
                      /*? if <26.3 {*/
                      .setOutputTarget(OutputTarget.ITEM_ENTITY_TARGET)
                      /*? }*/
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
                  // 26.3 has no translucent armor pipeline; the translucent entity pipeline with the
                  // armor layering offset is the closest match and also supports OIT.
                  /*? if >=26.3 {*/
                  /*RenderSetup.builder(RenderPipelines.ENTITY_TRANSLUCENT)
                      .setOitPipelines(RenderPipelines.OIT_ENTITY)
                  *//*? } else {*/
                  RenderSetup.builder(RenderPipelines.ARMOR_TRANSLUCENT)
                  /*? }*/
                      .withTexture("Sampler0", texture)
                      .useLightmap()
                      .useOverlay()
                      .setLayeringTransform(LayeringTransform.VIEW_OFFSET_Z_LAYERING)
                      .affectsCrumbling()
                      .sortOnUpload()
                      .setOutline(RenderSetup.OutlineProperty.AFFECTS_OUTLINE)
                      /*? if <26.3 {*/
                      .setOutputTarget(OutputTarget.ITEM_ENTITY_TARGET)
                      /*? }*/
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
