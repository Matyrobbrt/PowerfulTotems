package com.matyrobbrt.powerfultotems.core.network.message;

import java.util.ArrayList;
import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

public class InputMessage {

	public int key;

	public InputMessage() {
		// TODO Auto-generated constructor stub
	}

	public InputMessage(int key) {
		this.key = key;
	}

	public static void encode(InputMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.key);
	}

	public static InputMessage decode(PacketBuffer buffer) {
		return new InputMessage(buffer.readInt());
	}

	public static void handle(InputMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {

			ServerPlayerEntity playerEntity = context.getSender();
			String stringList = playerEntity.inventory.items.toString();
			String stringList2 = "";
			String subString = "";
			ArrayList<String> listArray = new ArrayList<String>(9);
			ServerWorld worldIn = playerEntity.getLevel();
			Minecraft mc = Minecraft.getInstance();

			stringList = stringList.substring(1);

			for (int i = 0; i <= 7; ++i) {
				subString = stringList.substring(0, stringList.indexOf(","));
				listArray.add(i, subString);
				int counT = listArray.get(i).length() + 2;
				stringList2 = stringList.substring(counT);
				stringList = stringList2;
				subString = "";
			}
			stringList = stringList.substring(1, stringList.length());
			listArray.add(8, stringList);
			
			if (playerEntity.hasItemInSlot(EquipmentSlotType.OFFHAND)) {
				playerEntity.getOffhandItem().use(worldIn, playerEntity, Hand.OFF_HAND);
			} else if (playerEntity.hasItemInSlot(EquipmentSlotType.MAINHAND)) {
				playerEntity.getMainHandItem().use(worldIn, playerEntity, Hand.MAIN_HAND);
			}

			// Totem Of Undying
			if (playerEntity.inventory.offhand.toString().contains("[1 totem_of_undying]")) {
				playerEntity.removeAllEffects();
				playerEntity.heal(1F);
				playerEntity.addEffect(new EffectInstance(Effects.REGENERATION, 900, 1));
				playerEntity.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 800));
				playerEntity.addEffect(new EffectInstance(Effects.ABSORPTION, 100, 1));
				mc.gameRenderer.displayItemActivation(new ItemStack(Items.TOTEM_OF_UNDYING));

				playerEntity.getOffhandItem().shrink(1);
			} else if (listArray.get(playerEntity.inventory.selected).contains("1 totem_of_undying")) {
				playerEntity.removeAllEffects();
				playerEntity.heal(1F);
				playerEntity.addEffect(new EffectInstance(Effects.REGENERATION, 900, 1));
				playerEntity.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 800));
				playerEntity.addEffect(new EffectInstance(Effects.ABSORPTION, 100, 1));
				mc.gameRenderer.displayItemActivation(new ItemStack(Items.TOTEM_OF_UNDYING));

				playerEntity.getMainHandItem().shrink(1);
			}

		});
		context.setPacketHandled(true);
	}

}
