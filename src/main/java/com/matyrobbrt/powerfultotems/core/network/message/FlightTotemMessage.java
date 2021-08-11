package com.matyrobbrt.powerfultotems.core.network.message;

import java.util.function.Supplier;

import com.matyrobbrt.powerfultotems.core.init.TotemInit;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;

//import com.matyrobbrt.powerfultotems.core.init.TotemInit;

//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.Hand;
//import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
//import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

public class FlightTotemMessage {
	public int key;

	public FlightTotemMessage() {

	}

	public FlightTotemMessage(int key) {
		this.key = key;
	}

	public static void encode(FlightTotemMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.key);
	}

	public static FlightTotemMessage decode(PacketBuffer buffer) {
		return new FlightTotemMessage(buffer.readInt());
	}

	public static void handle(FlightTotemMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			
			ServerPlayerEntity player = context.getSender();
			ResourceLocation flight_totem_id = TotemInit.FLIGHT_TOTEM.get().getRegistryName();
			ItemStack stack = null;
			ServerWorld world = player.getLevel();
			Hand hand = Hand.MAIN_HAND;
			int isMainHand = 1;

			// Off hand
			if (player.getOffhandItem().getItem().getRegistryName() == flight_totem_id) {
				stack = player.getOffhandItem();
				hand = Hand.OFF_HAND;
			} else {
				// Inventory
				for (int i = 0; i <= 35; ++i) {
					ResourceLocation idLocation = player.inventory.getItem(i).getItem().getRegistryName();
					if (idLocation == flight_totem_id) {
						stack = player.inventory.getItem(i);
						if (player.inventory.getItem(i) == player.getMainHandItem())
							isMainHand = 0;
						break;
					}
				}
			}

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
								"You don't have a " + "\u00A7d" + "Flight Totem" + "\u00A7f" + " on you!"),
						player.getUUID());
			}
			
			//context.getSender().sendMessage(new StringTextComponent("The Flight Totem is a Work In Progress!"), context.getSender().getUUID());
			
		});
		context.setPacketHandled(true);
	}
}
