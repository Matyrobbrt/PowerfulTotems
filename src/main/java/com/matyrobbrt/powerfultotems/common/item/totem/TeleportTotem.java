package com.matyrobbrt.powerfultotems.common.item.totem;

import java.util.List;

import javax.annotation.Nullable;

import com.matyrobbrt.powerfultotems.core.config.TotemConfig;
import com.matyrobbrt.powerfultotems.core.helper.NBTHelper;
import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class TeleportTotem extends Item {

	public TeleportTotem() {
		super(new Item.Properties().stacksTo(16).setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP).rarity(Rarity.RARE));
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand hand) {

		ItemStack stack = player.getItemInHand(hand);

		if (player.isShiftKeyDown()) {

			NBTHelper.setBoolean(stack, "bound", true);
			NBTHelper.setDouble(stack, "x", player.getX());
			NBTHelper.setDouble(stack, "y", player.getY());
			NBTHelper.setDouble(stack, "z", player.getZ());

			if (worldIn.isClientSide)
				player.sendMessage(new StringTextComponent("The totem has been bound to your location!"),
						player.getUUID());

		} else {

			if (NBTHelper.getBoolean(stack, "bound") == false) {
				if (worldIn.isClientSide)
					player.sendMessage(new StringTextComponent(
							"The totem doesn't have a location stored! Shift right click to store your current location"),
							player.getUUID());
			} else if (!player.getCooldowns().isOnCooldown(this)) {

				double x = NBTHelper.getDouble(stack, "x");
				double y = NBTHelper.getDouble(stack, "y");
				double z = NBTHelper.getDouble(stack, "z");

				if (worldIn.isClientSide) {
					worldIn.playLocalSound(x, y, z, SoundEvents.ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F,
							false);
				} else {
					ServerPlayerEntity playerServer = (ServerPlayerEntity) player;
					playerServer.setPos(x, y, z);
					final ServerPlayerEntity notThisPlayer = (ServerPlayerEntity) player;
					worldIn.playSound(notThisPlayer, x, y, z, SoundEvents.ENDERMAN_TELEPORT, SoundCategory.PLAYERS,
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

		if (enchantment.isAllowedOnBooks()) {
			return false;
		}

		if (enchantment.isCompatibleWith(Enchantments.MENDING)) {
			return false;
		}

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
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {

		if (NBTHelper.getBoolean(stack, "bound") == true) {
			tooltip.add(new StringTextComponent("Stored destination ="));
			tooltip.add(new StringTextComponent("X: " + NBTHelper.getInt(stack, "x")));
			tooltip.add(new StringTextComponent("Y: " + NBTHelper.getInt(stack, "y")));
			tooltip.add(new StringTextComponent("Z: " + NBTHelper.getInt(stack, "z")));
			tooltip.add(new StringTextComponent("Right click to teleport."));
		} else {
			tooltip.add(new StringTextComponent("Hold down sneak (shift) and then right"));
			tooltip.add(new StringTextComponent("click to store your current location"));
		}

		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}

}
