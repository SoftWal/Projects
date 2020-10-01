package com.aww_man.Teleportation.items.tools.Lazer_Charge;

import java.util.List;

import com.aww_man.Teleportation.Main;
import com.aww_man.Teleportation.init.ModItems;
import com.aww_man.Teleportation.util.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class Lazer_Charge extends Item implements IHasModel{
	public Lazer_Charge(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.modtabs);
		ModItems.ITEMS.add(this);
		setMaxStackSize(1);
		setMaxDamage(100);
		
	}

	public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
		EntityBeam entityBeam = new EntityBeam(worldIn, shooter);
		return entityBeam;
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add("Press shift+right click with redstone or redstone block in your inventory to replenish durability");
		int max = stack.getMaxDamage()/5;
		int uses = stack.getItemDamage()/5;
		tooltip.add("Uses: "+uses+"/"+max);
	}
	 @Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		 ItemStack charge = playerIn.getHeldItemMainhand();
		 if(playerIn.isSneaking()) {
     		ItemStack block = new ItemStack(Item.REGISTRY.getObjectById(152));
			ItemStack dust = new ItemStack(Item.REGISTRY.getObjectById(331));
     		if(playerIn.inventory.hasItemStack(block)) {
 				int x1=playerIn.inventory.getSlotFor(block);
 				ItemStack IBlock = playerIn.inventory.getStackInSlot(x1);
 				while(charge.getItemDamage()!=0&&IBlock.getCount()!=0) {
 					int y=IBlock.getCount();
 					charge.setItemDamage(charge.getItemDamage()-25);
 					IBlock.setCount(y-1);;
 				}
     		}
     		else if(playerIn.inventory.hasItemStack(dust)) {
				int x=playerIn.inventory.getSlotFor(dust);
				ItemStack IDust = playerIn.inventory.getStackInSlot(x);
				while(charge.getItemDamage()!=0&&IDust.getCount()!=0) {
					int y=IDust.getCount();
					charge.setItemDamage(charge.getItemDamage()-2);
					IDust.setCount(y-1);
				}
			}
     	}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	@Override
	public void registerModels() {
		Main.proxy.registerItemRender(this,0,"inventory");

	}
	
	

}