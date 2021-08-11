package com.matyrobbrt.powerfultotems.core.network.message;

import java.util.function.Supplier;

import com.matyrobbrt.powerfultotems.core.init.TotemInit;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

public class FireworkTotemMessage {

	public int key;

	public FireworkTotemMessage() {

	}

	public FireworkTotemMessage(int key) {
		this.key = key;
	}

	public static void encode(FireworkTotemMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.key);
	}

	public static FireworkTotemMessage decode(PacketBuffer buffer) {
		return new FireworkTotemMessage(buffer.readInt());
	}

	public static void handle(FireworkTotemMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {

			ServerPlayerEntity player = context.getSender();
			ResourceLocation firework_totem_id = TotemInit.FIREWORK_TOTEM.get().getRegistryName();
			ResourceLocation firework_id = Items.FIREWORK_ROCKET.getRegistryName();
			ItemStack stack = null;
			ServerWorld world = player.getLevel();
			Hand hand = Hand.MAIN_HAND;
			int isMainHand = 1;

			// Off hand
			if (player.getOffhandItem().getItem().getRegistryName() == firework_totem_id) {
				stack = player.getOffhandItem();
				hand = Hand.OFF_HAND;
			} else {
				// Inventory
				for (int i = 0; i <= 35; ++i) {
					ResourceLocation idLocation = player.inventory.getItem(i).getItem().getRegistryName();
					if (idLocation == firework_totem_id) {
						stack = player.inventory.getItem(i);
						if (player.inventory.getItem(i) == player.getMainHandItem())
							isMainHand = 0;
						break;
					}
				}
			}

			if (!player.isShiftKeyDown()) {
				if (stack != null && hand == Hand.MAIN_HAND) {
					stack.use(world, player, hand);
					if (isMainHand == 1) {
						int DamageValue = stack.getDamageValue() + 1;
						stack.setDamageValue(DamageValue);
						if (DamageValue == stack.getMaxDamage()) {
							stack.shrink(1);
						}
					}
				} else if (stack != null && hand == Hand.OFF_HAND) {
					stack.use(world, player, hand);
				} else {
					player.sendMessage(
							new StringTextComponent(
									"You don't have a " + "\u00A76" + "Firework Totem" + "\u00A7f" + " on you!"),
							player.getUUID());
				}
			} else {
				for (int i = 0; i <= 35; ++i) {
					if (player.inventory.getItem(i).getItem().getRegistryName() == firework_id) {
						if (player.inventory.getItem(i).getCount() >= 16 && stack.getDamageValue() >= 16) {
							stack.setDamageValue(stack.getDamageValue() - 16);
							player.inventory.getItem(i).shrink(16);
						} else if (stack.getDamageValue() != 0
								&& player.inventory.getItem(i).getCount() >= stack.getDamageValue()) {
							player.inventory.getItem(i).shrink(stack.getDamageValue());
							stack.setDamageValue(0);
						} else if (stack.getDamageValue() != 0
								&& player.inventory.getItem(i).getCount() <= stack.getDamageValue()) {
							stack.setDamageValue(stack.getDamageValue() - player.inventory.getItem(i).getCount());
							player.inventory.getItem(i).shrink(player.inventory.getItem(i).getCount());

						}
					}

				}
			}

		});
		context.setPacketHandled(true);
	}

}
