package com.aww_man.Teleportation.init;

import java.util.ArrayList;
import java.util.List;

import com.aww_man.Teleportation.blocks.AzuriteBlock;
import com.aww_man.Teleportation.blocks.AzuriteOre;
import com.aww_man.Teleportation.blocks.MythrilBlock;
import com.aww_man.Teleportation.blocks.MythrilOre;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {
	public static final List<Block> BLOCKS= new ArrayList<Block>();
	//Azurite Block
	public static final Block Azurite_Block = new AzuriteBlock("azurite_block",Material.IRON);
	//Azurite Ore
	public static final Block Azurite_Ore = new AzuriteOre("azurite_ore", Material.ROCK);
	//Mythril Ore
	public static final Block Myhtril_Ore = new MythrilOre("mythril_ore", Material.ROCK);
	public static final Block Mythril_BLock = new MythrilBlock("mythril_block",Material.IRON);

}
