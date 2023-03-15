package com.matyrobbrt.powerfultotems.common.effect;

import javax.annotation.Nonnull;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;

public class Beacon extends MobEffect {

	public Beacon() {
		super(MobEffectCategory.BENEFICIAL, 0xa3bdff);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplified) {
		return true;
	}
	
	@Override
	public void applyEffectTick(@Nonnull LivingEntity entity, int amplifier) {
		
		entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100));
		entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 100));
		entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100));
		entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 100));
		entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100));
		
		entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100));
		
		super.applyEffectTick(entity, amplifier);
	}
	
}
