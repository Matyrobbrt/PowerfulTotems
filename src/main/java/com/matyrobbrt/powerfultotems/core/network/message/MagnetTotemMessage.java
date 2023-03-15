package com.matyrobbrt.powerfultotems.core.network.message;

import com.matyrobbrt.powerfultotems.core.helper.NBTHelper;
import com.matyrobbrt.powerfultotems.core.init.TotemInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MagnetTotemMessage {

    public MagnetTotemMessage() {

    }

    public static void encode(MagnetTotemMessage message, FriendlyByteBuf buffer) {
    }

    public static MagnetTotemMessage decode(FriendlyByteBuf buffer) {
        return new MagnetTotemMessage();
    }

    public static void handle(MagnetTotemMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {

            ServerPlayer player = context.getSender();

            ItemStack stack = null;

            // Off hand
            if (player.getOffhandItem().getItem() == TotemInit.MAGNET_TOTEM.get()) {
                stack = player.getOffhandItem();
            } else {
                // Inventory
                for (int i = 0; i <= 35; ++i) {
                    if (player.getInventory().getItem(i).is(TotemInit.MAGNET_TOTEM.get())) {
                        stack = player.getInventory().getItem(i);
                        break;
                    }
                }
            }

            if (stack != null) {
                NBTHelper.flipBoolean(stack, "Enabled");
            } else {
                player.sendMessage(
                        new TextComponent(
                                "You don't have a " + "\u00A7b" + "Magnet Totem" + "\u00A7f" + " on you!"),
                        player.getUUID());
            }

        });
        context.setPacketHandled(true);
    }

}
