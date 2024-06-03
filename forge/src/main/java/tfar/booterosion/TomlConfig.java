package tfar.booterosion;

import net.minecraftforge.common.ForgeConfigSpec;
import tfar.booterosion.platform.MLConfig;

public class TomlConfig implements MLConfig {
    @Override
    public double fallDamagePerBlock() {
        return Server.fall_damage_per_block.get();
    }

    @Override
    public double runDamagePerBlock() {
        return Server.run_damage_per_block.get();
    }


    public static class Server {

        public static ForgeConfigSpec.DoubleValue fall_damage_per_block;
        public static ForgeConfigSpec.DoubleValue run_damage_per_block;
        public Server(ForgeConfigSpec.Builder builder) {
            builder.push("general");
            fall_damage_per_block = builder.defineInRange("fall_damage_per_block",1,0,1000000000d);
            run_damage_per_block = builder.defineInRange("run_damage_per_block",.1,0,1);
            builder.pop();
        }
    }

}
