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

public class WaterBreathingTotemMessage {
	
	public int key;

	public WaterBreathingTotemMessage() {

	}

	public WaterBreathingTotemMessage(int key) {
		this.key = key;
	}

	public static void encode(WaterBreathingTotemMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.key);
	}

	public static WaterBreathingTotemMessage decode(PacketBuffer buffer) {
		return new WaterBreathingTotemMessage(buffer.readInt());
	}
	
	public static void handle(WaterBreathingTotemMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {

			ServerPlayerEntity player = context.getSender();
			ResourceLocation water_breathing_totem_id = TotemInit.WATER_BREATHING_TOTEM.get().getRegistryName();
			ItemStack stack = null;
			ServerWorld world = player.getLevel();
			Hand hand = Hand.MAIN_HAND;
			int isMainHand = 1;

			// Off hand
			if (player.getOffhandItem().getItem().getRegistryName() == water_breathing_totem_id) {
				stack = player.getOffhandItem();
				hand = Hand.OFF_HAND;
			} else {
				// Inventory
				for (int i = 0; i <= 35; ++i) {
					ResourceLocation idLocation = player.inventory.getItem(i).getItem().getRegistryName();
					if (idLocation == water_breathing_totem_id) {
						stack = player.inventory.getItem(i);
						if (player.inventory.getItem(i) == player.getMainHandItem())
							isMainHand = 0;
						break;
					}
				}
			}

			if (stack != null && hand == Hand.MAIN_HAND) {
				if (isMainHand == 1) {
					if (!player.hasEffect(Effects.WATER_BREATHING)) {
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
								"You don't have a " + "\u00A7b" + "Water Breathing Totem" + "\u00A7f" + " on you!"),
						player.getUUID());
			}

		});
		context.setPacketHandled(true);
	}
	
}
