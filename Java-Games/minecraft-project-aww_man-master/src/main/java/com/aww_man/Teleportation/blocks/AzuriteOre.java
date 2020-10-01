package com.aww_man.Teleportation.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class AzuriteOre extends BlockBase{

	public AzuriteOre(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.METAL);
		setHardness(5.0f);
		setResistance(15.0f);
		setHarvestLevel("pickaxe",3);
		setLightLevel(1000);
	}
	
}