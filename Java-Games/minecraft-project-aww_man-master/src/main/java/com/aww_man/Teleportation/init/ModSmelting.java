package com.aww_man.Teleportation.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModSmelting {
	public static void init() {
		GameRegistry.addSmelting(ModBlocks.Azurite_Ore, new ItemStack(ModItems.AZURITE_INGOT,1),2.0f );
		GameRegistry.addSmelting(ModBlocks.Myhtril_Ore, new ItemStack(ModItems.Mythril,1),2.0f );

	}
}
