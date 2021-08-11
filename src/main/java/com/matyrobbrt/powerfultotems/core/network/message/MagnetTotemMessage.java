package com.matyrobbrt.powerfultotems.core.network.message;

import java.util.function.Supplier;

import com.matyrobbrt.powerfultotems.core.helper.NBTHelper;
import com.matyrobbrt.powerfultotems.core.init.TotemInit;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

public class MagnetTotemMessage {

	public int slot;

	public MagnetTotemMessage() {

	}

	public MagnetTotemMessage(int slot) {
		this.slot = slot;
	}

	public static void encode(MagnetTotemMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.slot);
	}

	public static MagnetTotemMessage decode(PacketBuffer buffer) {
		return new MagnetTotemMessage(buffer.readInt());
	}

	public static void handle(MagnetTotemMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {

			ServerPlayerEntity player = context.getSender();

			ResourceLocation magnet_totem_id = TotemInit.MAGNET_TOTEM.get().getRegistryName();
			ItemStack stack = null;

			// Off hand
			if (player.getOffhandItem().getItem().getRegistryName() == magnet_totem_id) {
				stack = player.getOffhandItem();
			} else {
				// Inventory
				for (int i = 0; i <= 35; ++i) {
					ResourceLocation idLocation = player.inventory.getItem(i).getItem().getRegistryName();
					if (idLocation == magnet_totem_id) {
						stack = player.inventory.getItem(i);
						break;
					}
				}
			}

			if (stack != null) {
				NBTHelper.flipBoolean(stack, "Enabled");
			} else {
				player.sendMessage(
						new StringTextComponent(
								"You don't have a " + "\u00A7b" + "Magnet Totem" + "\u00A7f" + " on you!"),
						player.getUUID());
			}

		});
		context.setPacketHandled(true);
	}

}
