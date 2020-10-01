package com.aww_man.Teleportation.Tabs;

import com.aww_man.Teleportation.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModToolsTab extends CreativeTabs{
	public ModToolsTab(String label) {
		super("modtooltab");
	}
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.AZURITE_PICKAXE);
		
	}
}