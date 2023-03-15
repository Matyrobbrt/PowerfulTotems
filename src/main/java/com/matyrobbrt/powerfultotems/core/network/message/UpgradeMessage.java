package com.matyrobbrt.powerfultotems.core.network.message;

import com.google.common.base.Suppliers;
import com.matyrobbrt.powerfultotems.core.helper.TotemHelper;
import com.matyrobbrt.powerfultotems.core.init.ItemInit;
import com.matyrobbrt.powerfultotems.core.init.TotemInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class UpgradeMessage {
    public static final Supplier<? extends List<? extends Item>> UPGRADABLE_TOTEMS = Suppliers.memoize(() -> Stream.of(
            TotemInit.MAGNET_TOTEM, TotemInit.GLUTTONY_TOTEM, TotemInit.IMMUNITY_TOTEM, TotemInit.REGENERATION_TOTEM,
            TotemInit.FALL_TOTEM, TotemInit.WATER_TOTEM, TotemInit.GOLEM_TOTEM, TotemInit.VOID_TOTEM
    ).map(RegistryObject::get).toList());


    public int key;

    public UpgradeMessage() {

    }

    public UpgradeMessage(int key) {
        this.key = key;
    }

    public static void encode(UpgradeMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.key);
    }

    public static UpgradeMessage decode(FriendlyByteBuf buffer) {
        return new UpgradeMessage(buffer.readInt());
    }

    public static void upgrade(ItemStack totem, ItemStack core, int upgradeLevel, Player player) {
        TotemHelper.upgrade(totem, core, upgradeLevel, player);
    }

    public static void handle(UpgradeMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {

            ServerPlayer player = context.getSender();
            ItemStack totem = player.getOffhandItem();
            ItemStack core = player.getMainHandItem();
            int upgradeLevel = 0;

            if (core.getItem() == ItemInit.BASIC_CORE.get())
                upgradeLevel = 1;

            else if (core.getItem() == ItemInit.ADVANCED_CORE.get())
                upgradeLevel = 2;

            else if (core.getItem() == ItemInit.REINFORCED_CORE.get())
                upgradeLevel = 3;

            if (upgradeLevel != 0) {
                if (UPGRADABLE_TOTEMS.get().contains(totem.getItem())) {
                    upgrade(totem, core, upgradeLevel, player);
                } else {
                    player.sendMessage(new TextComponent("You are not holding a upgradeable totem!"),
                            player.getUUID());
                }
            } else {
                player.sendMessage(new TextComponent("You are not holding a totem Core!"), player.getUUID());
            }
        });
        context.setPacketHandled(true);
    }

}
