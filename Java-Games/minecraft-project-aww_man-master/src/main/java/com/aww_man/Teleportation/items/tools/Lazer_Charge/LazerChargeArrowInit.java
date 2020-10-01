package com.aww_man.Teleportation.items.tools.Lazer_Charge;

import com.aww_man.Teleportation.Main;
import com.aww_man.Teleportation.util.IHasModel;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LazerChargeArrowInit extends ItemArrow implements IHasModel{
	public LazerChargeArrowInit(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);


	}
	
	@Override
	public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
		EntityBeam entityBeam = new EntityBeam(worldIn, shooter);
		return entityBeam;
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRender(this,0,"inventory");

	}
}
