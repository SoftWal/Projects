package com.aww_man.Teleportation.items.poketDimsnesion;

import java.util.List;

import com.aww_man.Teleportation.Main;
import com.aww_man.Teleportation.init.ModItems;
import com.aww_man.Teleportation.util.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class PocketBed extends Item implements IHasModel{
	public PocketBed(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.modtabs);
		ModItems.ITEMS.add(this);
		setMaxDamage(1);
		setMaxStackSize(1);
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack item = playerIn.getHeldItem(handIn);
		playerIn.setSpawnPoint(playerIn.getPosition(), true);
		int index = playerIn.inventory.getSlotFor(item);
		playerIn.inventory.removeStackFromSlot(index);
		worldIn.setWorldTime(0);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS,item);
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List <String> tooltip, ITooltipFlag flagIn ) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add("Sets world spawn");
		
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRender(this,0,"inventory");
		
	}
}
