package com.aww_man.Teleportation.util.handlers;


import com.aww_man.Teleportation.items.tools.AzuriteArrow.EntityAzuriteArrow;
import com.aww_man.Teleportation.items.tools.AzuriteArrow.RenderAzuriteArrow;
import com.aww_man.Teleportation.items.tools.Lazer_Charge.EntityBeam;
import com.aww_man.Teleportation.items.tools.Lazer_Charge.RenderBeam;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHandler {
	
	public static void registerEntityRenders(){
		
		RenderingRegistry.registerEntityRenderingHandler(EntityAzuriteArrow.class, new IRenderFactory<EntityAzuriteArrow>() {
			@Override
			public Render<? super EntityAzuriteArrow> createRenderFor(RenderManager manager) {
				return new RenderAzuriteArrow(manager);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityBeam.class, new IRenderFactory<EntityBeam>() {
			@Override
			public Render<? super EntityBeam> createRenderFor(RenderManager manager) {
				return new RenderBeam(manager);
			}
		});
	}
}