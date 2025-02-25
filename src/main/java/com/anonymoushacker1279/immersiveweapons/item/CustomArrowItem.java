package com.anonymoushacker1279.immersiveweapons.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

public class CustomArrowItem extends ArrowItem {

	/**
	 * The amount of damage this arrow does by default.
	 */
	public double damage;

	public RegistryObject<Item> ref;

	/**
	 * @param properties Default Item.Properties
	 * @param damageIn   Amount of damage this arrow deals as base (will be multiplied by projectile velocity later!)
	 */
	public CustomArrowItem(Properties properties, double damageIn) {
		super(properties);
		this.damage = damageIn;
	}

	public void setItemReference(RegistryObject<Item> refIn) {
		this.ref = refIn;
	}

	@Override
	public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
		AbstractArrowEntity arrowentity = new ArrowEntity(worldIn, shooter);
		arrowentity.setBaseDamage(this.damage);
		return arrowentity;
	}

	// We override this method here because the version in ArrowItem *directly* compares against ArrowItem.class, rather than this more flexible check.
	@Override
	public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity player) {
		int enchant = net.minecraft.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.enchantment.Enchantments.INFINITY_ARROWS, bow);
		return enchant > 0;
	}
}