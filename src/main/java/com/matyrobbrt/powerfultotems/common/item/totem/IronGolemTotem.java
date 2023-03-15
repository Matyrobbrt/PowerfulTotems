package com.matyrobbrt.powerfultotems.common.item.totem;

import com.matyrobbrt.powerfultotems.core.helper.TotemHelper;
import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.List;

public class IronGolemTotem extends Item {

    public IronGolemTotem() {
        super(new Item.Properties().stacksTo(1).setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP).durability(4)
                .rarity(Rarity.EPIC));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {

        ItemStack stack = player.getItemInHand(hand);
        BlockHitResult blockraytraceresult = getPlayerPOVHitResult(world, player, ClipContext.Fluid.ANY);
        BlockPos blockpos = blockraytraceresult.getBlockPos();
        Direction direction = blockraytraceresult.getDirection();
        BlockPos blockpos1 = blockpos.relative(direction);
        BlockState blockstate = world.getBlockState(blockpos);
        BlockPos blockpos2 = canBlockContainFluid(world, blockpos, blockstate) ? blockpos : blockpos1;

        IronGolem golem = new IronGolem(EntityType.IRON_GOLEM, world);
        double x = blockpos2.getX();
        double y = blockpos2.getY();
        double z = blockpos2.getZ();
        golem.setPos(x, y, z);
        world.addFreshEntity(golem);

        TotemHelper.coreDamageItem(stack, 1, world);

        return super.use(world, player, hand);
    }

    private boolean canBlockContainFluid(Level worldIn, BlockPos posIn, BlockState blockstate) {
        return blockstate.getBlock() instanceof LiquidBlockContainer
                && ((LiquidBlockContainer) blockstate.getBlock()).canPlaceLiquid(worldIn, posIn, blockstate, Fluids.WATER);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
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
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip,
                                TooltipFlag flagIn) {
        TotemHelper.coreTooltip(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TextComponent("Right click to summon an Iron Golem!"));
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

}
