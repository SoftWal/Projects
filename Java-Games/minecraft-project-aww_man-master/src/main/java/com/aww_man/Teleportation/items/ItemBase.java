package com.aww_man.Teleportation.items;

import com.aww_man.Teleportation.Main;
import com.aww_man.Teleportation.init.ModItems;
import com.aww_man.Teleportation.util.IHasModel;

import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel{
	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.modmaterialtabs);
		ModItems.ITEMS.add(this);
	}
	@Override
	public void registerModels() {
		Main.proxy.registerItemRender(this,0,"inventory");
		
	}

}
