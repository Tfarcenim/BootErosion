package tfar.booterosion.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.booterosion.platform.Services;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow public abstract ItemStack getItemBySlot(EquipmentSlot pSlot);

    @Shadow @Final private static AttributeModifier SPEED_MODIFIER_SPRINTING;

    @Inject(at = @At("TAIL"), method = "onChangedBlock")
    private void init(BlockPos $$0, CallbackInfo ci) {
        ItemStack boots = getItemBySlot(EquipmentSlot.FEET);
        if (!boots.isEmpty()) {
            LivingEntity living = (LivingEntity)(Object)this;
            if (living.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPEED_MODIFIER_SPRINTING) &&
                    living.getRandom().nextDouble() < Services.PLATFORM.getConfig().runDamagePerBlock()) {
                boots.hurtAndBreak(1, living, living1 -> living1.broadcastBreakEvent(EquipmentSlot.FEET));
            }
        }
    }
}