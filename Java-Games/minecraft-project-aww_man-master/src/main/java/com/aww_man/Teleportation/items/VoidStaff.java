package com.aww_man.Teleportation.items;



import java.util.List;


import com.aww_man.Teleportation.Main;
import com.aww_man.Teleportation.init.ModItems;
import com.aww_man.Teleportation.util.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


public class VoidStaff extends Item implements IHasModel{
	public VoidStaff(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.modtabs);
		ModItems.ITEMS.add(this);
		setMaxDamage(100);
		setMaxStackSize(1);
		
		
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack item = playerIn.getHeldItem(handIn);
			Vec3d Look = playerIn.getLookVec();
			item.damageItem(5, playerIn);
			EntityEnderPearl enderpearl = new  EntityEnderPearl(worldIn, playerIn);
			enderpearl.setPosition(playerIn.posX+Look.x*30d, playerIn.posY+Look.y*30d, playerIn.posZ+Look.z*30d);
			worldIn.spawnEntity(enderpearl);
			worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			playerIn.getCooldownTracker().setCooldown(this, 90);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS,item);
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List <String> tooltip, ITooltipFlag flagIn ) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add("Warming may cause no-clip which might lead to death if improperly used");
		tooltip.add("Special Ability: Consumes a stack of ender pearls, when its durability reaches 1, to replenish durability");
		
		int max = stack.getMaxDamage()/5;
		int uses = stack.getItemDamage()/5;
		tooltip.add("Uses: "+uses+"/"+max);
		
	}
	
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRender(this,0,"inventory");
		
	}
	
}
