package com.aww_man.Teleportation.items.tools.Lazer_Charge;

import com.aww_man.Teleportation.util.Reference;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBeam extends RenderArrow<EntityBeam>{
	public RenderBeam(RenderManager manager) {
		super(manager);
	}

	
	@Override
	protected ResourceLocation getEntityTexture(EntityBeam entity) {
		return new ResourceLocation(Reference.MOD_ID + ":textures/entity/arrows/beam.png");
	}
	
	

}