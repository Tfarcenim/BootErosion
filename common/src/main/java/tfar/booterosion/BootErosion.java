package tfar.booterosion;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ItemSteerable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tfar.booterosion.platform.Services;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class BootErosion {

    public static final String MOD_ID = "booterosion";
    public static final String MOD_NAME = "BootErosion";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {

    }

    public static void onFall(LivingEntity living,float distance) {
        ItemStack boots = living.getItemBySlot(EquipmentSlot.FEET);
        if (!boots.isEmpty()) {
            boots.hurtAndBreak((int)(distance * Services.PLATFORM.getConfig().fallDamagePerBlock()),living, living1 -> living1.broadcastBreakEvent(EquipmentSlot.FEET));
        }
    }

}