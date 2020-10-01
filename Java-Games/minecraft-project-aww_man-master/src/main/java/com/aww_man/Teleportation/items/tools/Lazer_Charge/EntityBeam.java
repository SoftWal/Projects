package com.aww_man.Teleportation.items.tools.Lazer_Charge;


import com.aww_man.Teleportation.init.ModItems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityBeam extends EntityArrow{
	public EntityBeam(World worldIn) {
		super(worldIn);
	}
	
	public EntityBeam(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}
	
	public EntityBeam(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
		this.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
	}
	
	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(ModItems.LAZERCHARGEARROWINIT);
	}
	
	@Override
	protected void arrowHit(EntityLivingBase living) {
		super.arrowHit(living);
		
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(this.world.isRemote){
			if(this.inGround){
				if(this.timeInGround % 5 == 0){
					this.isDead=true;
				}
			}
		}
	}

}
