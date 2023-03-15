package com.matyrobbrt.powerfultotems.core.network;

import com.matyrobbrt.powerfultotems.PowerfulTotems;
import com.matyrobbrt.powerfultotems.core.network.message.MagnetTotemMessage;
import com.matyrobbrt.powerfultotems.core.network.message.UpgradeMessage;
import com.matyrobbrt.powerfultotems.core.network.message.UseTotemMessage;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class TotemNetwork {

    public static final String NETWORK_VERSION = "1.0.0";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(PowerfulTotems.MOD_ID, "network"), () -> NETWORK_VERSION,
            version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

    public static void init() {

        CHANNEL.messageBuilder(MagnetTotemMessage.class, 0)
                .decoder(MagnetTotemMessage::decode)
                .encoder(MagnetTotemMessage::encode)
                .consumer(MagnetTotemMessage::handle)
                .add();
        CHANNEL.messageBuilder(UpgradeMessage.class, 1)
                .decoder(UpgradeMessage::decode)
                .encoder(UpgradeMessage::encode)
                .consumer(UpgradeMessage::handle)
                .add();
        CHANNEL.messageBuilder(UseTotemMessage.class, 2)
                .decoder(UseTotemMessage::new)
                .encoder(UseTotemMessage::encode)
                .consumer((useTotemMessage, contextSupplier) -> {
                    contextSupplier.get().setPacketHandled(true);
                    contextSupplier.get().enqueueWork(() -> useTotemMessage.handle(contextSupplier));
                })
                .add();
    }

}
