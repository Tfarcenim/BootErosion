package tfar.booterosion;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;

@Mod(BootErosion.MOD_ID)
public class BootErosionForge {
    
    public BootErosionForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.addListener(this::onFall);
        ModLoadingContext.get().registerConfig(Type.SERVER, SERVER_SPEC);
        BootErosion.init();
    }

    private void onFall(LivingFallEvent event) {
        BootErosion.onFall(event.getEntity(),event.getDistance());
    }

    public static final TomlConfig.Server SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;

    static {
        final Pair<TomlConfig.Server, ForgeConfigSpec> specPair2 = new ForgeConfigSpec.Builder().configure(TomlConfig.Server::new);
        SERVER_SPEC = specPair2.getRight();
        SERVER = specPair2.getLeft();
    }

}