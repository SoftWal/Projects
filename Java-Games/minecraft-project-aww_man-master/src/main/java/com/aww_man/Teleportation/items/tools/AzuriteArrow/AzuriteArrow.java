  package com.aww_man.Teleportation.items.tools.AzuriteArrow;

import com.aww_man.Teleportation.Main;
import com.aww_man.Teleportation.init.ModItems;
import com.aww_man.Teleportation.util.IHasModel;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AzuriteArrow extends ItemArrow implements IHasModel{
	public AzuriteArrow(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.modtabs);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
		EntityAzuriteArrow entityAzuriteArrow = new EntityAzuriteArrow(worldIn, shooter);
		return entityAzuriteArrow;
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRender(this,0,"inventory");

	}
}