package com.matyrobbrt.powerfultotems.common.effect;

import javax.annotation.Nonnull;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class NegateFallDamage extends Effect{

	public NegateFallDamage() {
		super(EffectType.BENEFICIAL, 0x26ADFF);
	}

	
	@Override
	public boolean isDurationEffectTick(int duration, int amplified) {
		// TODO Auto-generated method stub
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
