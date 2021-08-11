package com.matyrobbrt.powerfultotems.core.network.message;

import java.util.function.Supplier;

import com.matyrobbrt.powerfultotems.core.helper.TotemHelper;
import com.matyrobbrt.powerfultotems.core.init.ItemInit;
import com.matyrobbrt.powerfultotems.core.init.TotemInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

public class UpgradeMessage {

	public int key;

	public UpgradeMessage() {

	}

	public UpgradeMessage(int key) {
		this.key = key;
	}

	public static void encode(UpgradeMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.key);
	}

	public static UpgradeMessage decode(PacketBuffer buffer) {
		return new UpgradeMessage(buffer.readInt());
	}

	public static void upgrade(ItemStack totem, ItemStack core, int upgradeLevel, PlayerEntity player) {
		TotemHelper.upgrade(totem, core, upgradeLevel, player);
	}

	public static void handle(UpgradeMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {

			ServerPlayerEntity player = context.getSender();
			ItemStack totem = player.getOffhandItem();
			ItemStack core = player.getMainHandItem();
			ResourceLocation totem_id = totem.getItem().getRegistryName();
			int upgradeLevel = 0;

			if (core.getItem().getRegistryName() == ItemInit.BASIC_CORE.get().getRegistryName())
				upgradeLevel = 1;

			else if (core.getItem().getRegistryName() == ItemInit.ADVANCED_CORE.get().getRegistryName())
				upgradeLevel = 2;

			else if (core.getItem().getRegistryName() == ItemInit.REINFORCED_CORE.get().getRegistryName())
				upgradeLevel = 3;

			if (upgradeLevel != 0) {
				if (totem_id == TotemInit.MAGNET_TOTEM.get().getRegistryName())
					upgrade(totem, core, upgradeLevel, player);
				else if (totem_id == TotemInit.GLUTTONY_TOTEM.get().getRegistryName())
					upgrade(totem, core, upgradeLevel, player);
				else if (totem_id == TotemInit.IMMUNITY_TOTEM.get().getRegistryName())
					upgrade(totem, core, upgradeLevel, player);
				else if (totem_id == TotemInit.REGENERATION_TOTEM.get().getRegistryName())
					upgrade(totem, core, upgradeLevel, player);
				else if (totem_id == TotemInit.FALL_TOTEM.get().getRegistryName())
					upgrade(totem, core, upgradeLevel, player);
				else if (totem_id == TotemInit.WATER_TOTEM.get().getRegistryName())
					upgrade(totem, core, upgradeLevel, player);
				else if (totem_id == TotemInit.GOLEM_TOTEM.get().getRegistryName())
					upgrade(totem, core, upgradeLevel, player);
				else if (totem_id == TotemInit.VOID_TOTEM.get().getRegistryName())
					upgrade(totem, core, upgradeLevel, player);
				else if (totem_id == TotemInit.TROWEL_TOTEM.get().getRegistryName())
					upgrade(totem, core, upgradeLevel, player);

				else
					player.sendMessage(new StringTextComponent("You are not holding a upgradeable totem!"),
							player.getUUID());
			} else
				player.sendMessage(new StringTextComponent("You are not holding a totem Core!"), player.getUUID());

		});
		context.setPacketHandled(true);
	}

}
