package com.matyrobbrt.powerfultotems.common.item.totem;

import java.util.List;

import javax.annotation.Nullable;

import com.matyrobbrt.powerfultotems.core.config.TotemConfig;
import com.matyrobbrt.powerfultotems.core.helper.NBTHelper;
import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;

public class TeleportTotem extends Item {

	public TeleportTotem() {
		super(new Item.Properties().stacksTo(16).setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP).rarity(Rarity.RARE));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {

		ItemStack stack = player.getItemInHand(hand);

		if (player.isShiftKeyDown()) {

			NBTHelper.setBoolean(stack, "bound", true);
			NBTHelper.setDouble(stack, "x", player.getX());
			NBTHelper.setDouble(stack, "y", player.getY());
			NBTHelper.setDouble(stack, "z", player.getZ());

			if (worldIn.isClientSide)
				player.sendMessage(new TextComponent("The totem has been bound to your location!"),
						player.getUUID());

		} else {

			if (NBTHelper.getBoolean(stack, "bound") == false) {
				if (worldIn.isClientSide)
					player.sendMessage(new TextComponent(
							"The totem doesn't have a location stored! Shift right click to store your current location"),
							player.getUUID());
			} else if (!player.getCooldowns().isOnCooldown(this)) {

				double x = NBTHelper.getDouble(stack, "x");
				double y = NBTHelper.getDouble(stack, "y");
				double z = NBTHelper.getDouble(stack, "z");

				if (worldIn.isClientSide) {
					worldIn.playLocalSound(x, y, z, SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F,
							false);
				} else {
					ServerPlayer playerServer = (ServerPlayer) player;
					playerServer.setPos(x, y, z);
					final ServerPlayer notThisPlayer = (ServerPlayer) player;
					worldIn.playSound(notThisPlayer, x, y, z, SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS,
							1.0F, 1.0F);
					stack.shrink(1);
					player.getCooldowns().addCooldown(this, TotemConfig.teleport_totem_cooldown.get() * 20);
				}
			}

		}

		return super.use(worldIn, player, hand);
	}

	@Override
	public int getDamage(ItemStack stack) {
		return 2;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return false;
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return NBTHelper.getBoolean(stack, "bound");
	}

	@Override
	public boolean isFireResistant() {
		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip,
			TooltipFlag flagIn) {

		if (NBTHelper.getBoolean(stack, "bound")) {
			tooltip.add(new TextComponent("Stored destination ="));
			tooltip.add(new TextComponent("X: " + NBTHelper.getInt(stack, "x")));
			tooltip.add(new TextComponent("Y: " + NBTHelper.getInt(stack, "y")));
			tooltip.add(new TextComponent("Z: " + NBTHelper.getInt(stack, "z")));
			tooltip.add(new TextComponent("Right click to teleport."));
		} else {
			tooltip.add(new TextComponent("Hold down sneak (shift) and then right"));
			tooltip.add(new TextComponent("click to store your current location"));
		}

		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}

}
