package com.aww_man.Teleportation.blocks;

import com.aww_man.Teleportation.Main;
import com.aww_man.Teleportation.init.ModBlocks;
import com.aww_man.Teleportation.init.ModItems;
import com.aww_man.Teleportation.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel{
	public BlockBase(String name,Material material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.modblocktabs);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRender(Item.getItemFromBlock(this),0, "normal");
		
	}
	
}
