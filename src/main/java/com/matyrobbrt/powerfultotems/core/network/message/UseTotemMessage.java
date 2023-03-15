package com.matyrobbrt.powerfultotems.core.network.message;

import com.matyrobbrt.powerfultotems.common.item.totem.BaseTotem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

// fire res
// flight
// god
// water breathing
// fall
// firework
// speed
public record UseTotemMessage(Item totem) {
    public UseTotemMessage(FriendlyByteBuf buf) {
        this(buf.readRegistryIdUnsafe(ForgeRegistries.ITEMS));
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeRegistryIdUnsafe(ForgeRegistries.ITEMS, totem);
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        final var context = contextSupplier.get();
        ServerPlayer player = context.getSender();
        ItemStack stack = null;
        ServerLevel world = player.getLevel();

        // Off hand
        if (player.getOffhandItem().getItem() == totem) {
            stack = player.getOffhandItem();
        } else {
            // Inventory
            for (int i = 0; i <= 35; ++i) {
                ItemStack inv = player.getInventory().getItem(i);
                if (inv.getItem() == totem) {
                    stack = inv;
                    break;
                }
            }
        }

        if (stack != null) {
            ((BaseTotem) stack.getItem()).use(world, player, stack);
        } else {
            player.sendMessage(new TextComponent("You don't have a ").append(totem.getDescription().copy().withStyle(ChatFormatting.AQUA)).append(" on you!"), player.getUUID());
        }
    }
}
