package com.matyrobbrt.powerfultotems.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nonnull;

public class NegateFallDamage extends MobEffect{

	public NegateFallDamage() {
		super(MobEffectCategory.BENEFICIAL, 0x26ADFF);
	}

	
	@Override
	public boolean isDurationEffectTick(int duration, int amplified) {
		return true;
	}
	
	@Override
	public void applyEffectTick(@Nonnull LivingEntity entity, int amplifier) {
		
		if (entity.fallDistance > 0.0F) {
			entity.fallDistance = 0.0F;
		}
		
		super.applyEffectTick(entity, amplifier);
	}
	
}
