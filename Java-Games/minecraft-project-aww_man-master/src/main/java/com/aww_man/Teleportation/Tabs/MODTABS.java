package com.aww_man.Teleportation.Tabs;

import com.aww_man.Teleportation.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class MODTABS extends CreativeTabs{
	public MODTABS(String label) {
		super("modtab");
	}
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.AZURITE_SWORD);
		
	}
}
