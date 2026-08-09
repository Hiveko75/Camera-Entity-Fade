/*? if >=1.21.11 {*/
package io.github.leawind.cameraentityfade.internal.bridge.mixin.cameraentityopacity;

import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(RenderType.class)
public interface RenderTypeAccessor {
  @Invoker("create")
  static RenderType cameraEntityFade$create(String name, RenderSetup setup) {
    throw new AssertionError();
  }
}
/*? } else {*/
/*package io.github.leawind.cameraentityfade.internal.bridge.mixin.cameraentityopacity;
*//*? }*/
