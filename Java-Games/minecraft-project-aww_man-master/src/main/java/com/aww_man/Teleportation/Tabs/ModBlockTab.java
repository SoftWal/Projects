package com.aww_man.Teleportation.Tabs;

import com.aww_man.Teleportation.init.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModBlockTab extends CreativeTabs{
	public ModBlockTab(String label) {
		super("modblockstab");
	}
	public ItemStack getTabIconItem() {
		return new ItemStack(ModBlocks.Myhtril_Ore);
		
	}
}