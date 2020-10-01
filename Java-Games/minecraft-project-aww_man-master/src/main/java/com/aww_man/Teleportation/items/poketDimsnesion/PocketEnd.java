package com.aww_man.Teleportation.items.poketDimsnesion;

import java.util.List;

import com.aww_man.Teleportation.Main;
import com.aww_man.Teleportation.init.ModItems;
import com.aww_man.Teleportation.util.IHasModel;

import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class PocketEnd extends Item implements IHasModel{
	public PocketEnd(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.modtabs);
		ModItems.ITEMS.add(this);
		setMaxDamage(2);
		setMaxStackSize(1);
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack item = playerIn.getHeldItem(handIn);
		int rand = ThreadLocalRandom.current().nextInt(1,5);
		if(rand>1) {
			playerIn.changeDimension(1);
		}
		else if(rand==1) {
			playerIn.setHealth(0);
		}
		item.damageItem(2, playerIn);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS,item);
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List <String> tooltip, ITooltipFlag flagIn ) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		int use = stack.getItemDamage();
		int max = stack.getMaxDamage();
		tooltip.add("Teleports player to the end");
		tooltip.add("Warning: highly unstable, may cause death");
		if(use==2){
			tooltip.add("Uses: "+(use-1)+"/"+max);
		}else {
			tooltip.add("Uses: "+use+"/"+max);
		}
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRender(this,0,"inventory");
		
	}
}
