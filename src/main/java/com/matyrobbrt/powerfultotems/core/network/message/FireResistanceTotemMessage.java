package com.matyrobbrt.powerfultotems.core.network.message;

import java.util.function.Supplier;

import com.matyrobbrt.powerfultotems.core.init.TotemInit;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

public class FireResistanceTotemMessage {

	public int key;

	public FireResistanceTotemMessage() {

	}

	public FireResistanceTotemMessage(int key) {
		this.key = key;
	}

	public static void encode(FireResistanceTotemMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.key);
	}

	public static FireResistanceTotemMessage decode(PacketBuffer buffer) {
		return new FireResistanceTotemMessage(buffer.readInt());
	}

	public static void handle(FireResistanceTotemMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {

			ServerPlayerEntity player = context.getSender();
			ResourceLocation fire_resistance_totem_id = TotemInit.FIRE_RESISTANCE_TOTEM.get().getRegistryName();
			ItemStack stack = null;
			ServerWorld world = player.getLevel();
			Hand hand = Hand.MAIN_HAND;
			int isMainHand = 1;

			// Off hand
			if (player.getOffhandItem().getItem().getRegistryName() == fire_resistance_totem_id) {
				stack = player.getOffhandItem();
				hand = Hand.OFF_HAND;
			} else {
				// Inventory
				for (int i = 0; i <= 35; ++i) {
					ResourceLocation idLocation = player.inventory.getItem(i).getItem().getRegistryName();
					if (idLocation == fire_resistance_totem_id) {
						stack = player.inventory.getItem(i);
						if (player.inventory.getItem(i) == player.getMainHandItem())
							isMainHand = 0;
						break;
					}
				}
			}

			if (stack != null && hand == Hand.MAIN_HAND) {
				if (isMainHand == 1) {
					if (!player.hasEffect(Effects.FIRE_RESISTANCE)) {
						stack.use(world, player, hand);
						int DamageValue = stack.getDamageValue() + 1;
						stack.setDamageValue(DamageValue);
						if (DamageValue == stack.getMaxDamage()) {
							stack.shrink(1);
						}
					}
				} else if (isMainHand == 0) {
					stack.use(world, player, hand);
				}
			} else if (stack != null && hand == Hand.OFF_HAND) {
				stack.use(world, player, hand);
			} else {
				player.sendMessage(
						new StringTextComponent(
								"You don't have a " + "\u00A7b" + "Fire Resistance Totem" + "\u00A7f" + " on you!"),
						player.getUUID());
			}

		});
		context.setPacketHandled(true);
	}
	
}
