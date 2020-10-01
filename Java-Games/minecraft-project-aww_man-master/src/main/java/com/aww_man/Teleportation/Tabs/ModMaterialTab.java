package com.aww_man.Teleportation.Tabs;

import com.aww_man.Teleportation.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModMaterialTab extends CreativeTabs{
	public ModMaterialTab(String label) {
		super("modmaterialtab");
	}
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.AZURITE_INGOT);
		
	}
}