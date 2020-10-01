package com.aww_man.Teleportation.items.tools;

import com.aww_man.Teleportation.Main;
import com.aww_man.Teleportation.init.ModItems;
import com.aww_man.Teleportation.util.IHasModel;

import net.minecraft.item.ItemAxe;

public class ToolAxe extends ItemAxe implements IHasModel{
	public ToolAxe(String name,ToolMaterial material) {
		super(material, 6.0F,-3.2F);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.modtoolstabs);
		ModItems.ITEMS.add(this);
		setMaxStackSize(1);
	}
	@Override
	public int getItemEnchantability() {
		return 10;
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRender(this,0,"inventory");

	}

}