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
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class IronGolemTotem extends Item {

	public IronGolemTotem() {
		super(new Item.Properties().stacksTo(1).setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP).durability(4)
				.rarity(Rarity.EPIC));
	}
	
	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		
		ItemStack stack = player.getItemInHand(hand);
		RayTraceResult raytraceresult = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.ANY);
		BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
		BlockPos blockpos = blockraytraceresult.getBlockPos();
		Direction direction = blockraytraceresult.getDirection();
		BlockPos blockpos1 = blockpos.relative(direction);
		BlockState blockstate = world.getBlockState(blockpos);
		BlockPos blockpos2 = canBlockContainFluid(world, blockpos, blockstate) ? blockpos : blockpos1;
		
		IronGolemEntity golem = new IronGolemEntity(EntityType.IRON_GOLEM, world);
		Double x = (double) blockpos2.getX();
		Double y = (double) blockpos2.getY();
		Double z = (double) blockpos2.getZ();
		golem.setPos(x, y, z);
		world.addFreshEntity(golem);
		
		TotemHelper.coreDamageItem(stack, 1, world);
		
		return super.use(world, player, hand);
	}
	
	private boolean canBlockContainFluid(World worldIn, BlockPos posIn, BlockState blockstate) {
		return blockstate.getBlock() instanceof ILiquidContainer
				&& ((ILiquidContainer) blockstate.getBlock()).canPlaceLiquid(worldIn, posIn, blockstate, Fluids.WATER);
	}
	
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
		tooltip.add(new StringTextComponent("Right click to summon a iron golem!"));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}

}
