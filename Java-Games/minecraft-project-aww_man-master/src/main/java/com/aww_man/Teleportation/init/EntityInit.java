package com.aww_man.Teleportation.init;


import com.aww_man.Teleportation.Main;
import com.aww_man.Teleportation.items.tools.AzuriteArrow.EntityAzuriteArrow;
import com.aww_man.Teleportation.items.tools.Lazer_Charge.EntityBeam;
import com.aww_man.Teleportation.util.Reference;

import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {
	public static void registerEntities(){
		
		registerArrow("azurite_arrow", EntityAzuriteArrow.class, Reference.ENTITY_AZURITE_ARROW);
		registerArrow("beam", EntityBeam.class, Reference.ENTITY_BEAM);
	}
	
	private static void registerArrow(String name, Class<? extends EntityArrow> entity, int id){
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, Main.instance, 64, 20, true);
	}
	
	
}