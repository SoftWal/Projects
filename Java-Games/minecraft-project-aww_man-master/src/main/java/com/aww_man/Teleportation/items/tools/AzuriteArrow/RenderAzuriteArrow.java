package com.aww_man.Teleportation.items.tools.AzuriteArrow;

import com.aww_man.Teleportation.util.Reference;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderAzuriteArrow extends RenderArrow<EntityAzuriteArrow>{
	public RenderAzuriteArrow(RenderManager manager) {
		super(manager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityAzuriteArrow entity) {
		return new ResourceLocation(Reference.MOD_ID + ":textures/entity/arrows/azurite_arrow.png");
	}
	
}