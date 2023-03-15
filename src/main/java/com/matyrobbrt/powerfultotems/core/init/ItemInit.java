package com.matyrobbrt.powerfultotems.core.init;

import com.matyrobbrt.powerfultotems.PowerfulTotems;
import com.matyrobbrt.powerfultotems.common.item.BrokenGoldenApple;
import com.matyrobbrt.powerfultotems.common.item.Cherry;
import com.matyrobbrt.powerfultotems.common.item.MilkApple;
import com.matyrobbrt.powerfultotems.common.item.cores.AdvancedCore;
import com.matyrobbrt.powerfultotems.common.item.cores.BasicCore;
import com.matyrobbrt.powerfultotems.common.item.cores.ReinforcedCore;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PowerfulTotems.MOD_ID);
	
	public static final RegistryObject<BrokenGoldenApple> BROKEN_GOLDEN_APPLE = ITEMS.register("broken_golden_apple", BrokenGoldenApple::new);
	public static final RegistryObject<Cherry> CHERRY = ITEMS.register("cherry", Cherry::new);
	public static final RegistryObject<MilkApple> MILK_APPLE = ITEMS.register("milk_apple", MilkApple::new);
	
	public static final RegistryObject<BasicCore> BASIC_CORE = ITEMS.register("basic_core", BasicCore::new);
	public static final RegistryObject<AdvancedCore> ADVANCED_CORE = ITEMS.register("advanced_core", AdvancedCore::new);
	public static final RegistryObject<ReinforcedCore> REINFORCED_CORE = ITEMS.register("reinforced_core", ReinforcedCore::new);
}
