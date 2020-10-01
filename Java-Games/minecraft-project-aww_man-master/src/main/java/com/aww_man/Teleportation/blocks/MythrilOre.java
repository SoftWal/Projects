package com.aww_man.Teleportation.blocks;

import java.util.Random;

import com.aww_man.Teleportation.init.ModItems;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class MythrilOre extends BlockBase{

	public MythrilOre(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.METAL);
		setHardness(5.0f);
		setResistance(15.0f);
		setHarvestLevel("pickaxe",4);
	}
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.Mythril;
	}
	@Override
	public int quantityDropped(Random rand) {
		int max = 3;
		int min = 1;
		return rand.nextInt(max) + min;
	}
}