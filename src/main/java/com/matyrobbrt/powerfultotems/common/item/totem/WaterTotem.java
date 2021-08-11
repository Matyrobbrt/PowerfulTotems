package com.matyrobbrt.powerfultotems.common.item.totem;

import java.util.List;

import javax.annotation.Nullable;

import com.matyrobbrt.powerfultotems.core.helper.TotemHelper;
import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.block.BlockState;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class WaterTotem extends Item {

	public WaterTotem() {
		super(new Item.Properties().stacksTo(1).setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP).durability(256)
				.rarity(Rarity.RARE));
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

		ItemStack stack = player.getItemInHand(hand);
		RayTraceResult raytraceresult = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.ANY);

		/**
		player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.5F, NBTHelper.getBoolean(stack, "Enabled") ? 0.5F : 1.0F);

		NBTHelper.flipBoolean(stack, "Enabled");
		NBTHelper.flipBoolean(stack, "IsNotMagnetable");
		**/

		if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
			return ActionResult.pass(stack);
		} else if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
			return ActionResult.pass(stack);
		} else {
			BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
			BlockPos blockpos = blockraytraceresult.getBlockPos();
			Direction direction = blockraytraceresult.getDirection();
			BlockPos blockpos1 = blockpos.relative(direction);

			if (world.mayInteract(player, blockpos1) && player.mayUseItemAt(blockpos1, direction, stack)) {
				BlockState blockstate = world.getBlockState(blockpos);
				BlockPos blockpos2 = canBlockContainFluid(world, blockpos, blockstate) ? blockpos : blockpos1;
				world.setBlockAndUpdate(blockpos2, Fluids.WATER.defaultFluidState().createLegacyBlock());
				TotemHelper.coreDamageItem(stack, 1, world);
			}
		}

		return new ActionResult<>(ActionResultType.SUCCESS, stack);
	}

	private boolean canBlockContainFluid(World worldIn, BlockPos posIn, BlockState blockstate) {
		return blockstate.getBlock() instanceof ILiquidContainer
				&& ((ILiquidContainer) blockstate.getBlock()).canPlaceLiquid(worldIn, posIn, blockstate, Fluids.WATER);
	}

	/**
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {

		if (entity instanceof PlayerEntity && NBTHelper.getBoolean(stack, "Enabled")) {

			PlayerEntity player = (PlayerEntity) entity;
			ItemStack mainHandItem = player.getMainHandItem();
			ItemStack offHandItem = player.getOffhandItem();
			ResourceLocation bucket_id = Items.BUCKET.getRegistryName();
			int count;

			if (mainHandItem.getItem().getRegistryName() == bucket_id) {
				count = mainHandItem.getCount();
				player.setItemInHand(Hand.MAIN_HAND, new ItemStack(Items.WATER_BUCKET));
				if (count > 1)
					player.addItem(new ItemStack(Items.BUCKET, count - 1));
				TotemHelper.coreDamageItem(stack, 1, world);
			} else if (offHandItem.getItem().getRegistryName() == bucket_id) {
				count = offHandItem.getCount();
				player.setItemInHand(Hand.OFF_HAND, new ItemStack(Items.WATER_BUCKET));
				if (count > 1)
					player.addItem(new ItemStack(Items.BUCKET, count - 1));
				TotemHelper.coreDamageItem(stack, 1, world);
			}
		}

	}
	**/

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {

		if (enchantment.isAllowedOnBooks()) {
			return false;
		}

		if (!enchantment.isCompatibleWith(Enchantments.MENDING)) {
			return false;
		}

		return false;
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return true;
	}

	@Override
	public boolean isFireResistant() {
		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {

		TotemHelper.coreTooltip(stack, worldIn, tooltip, flagIn);
		//TotemHelper.enabledInfo(stack, worldIn, tooltip, flagIn);
		tooltip.add(new StringTextComponent("Right click to place a water source!"));

		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}

}
