package com.aww_man.Teleportation.Tabs;

import com.aww_man.Teleportation.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModArmorTab extends CreativeTabs{
	public ModArmorTab(String label) {
		super("modarmortab");
	}
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.MYTHRIL_HELMET);
		
	}
}