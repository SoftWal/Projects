package com.aww_man.Teleportation.events;

import com.aww_man.Teleportation.init.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class PayerInventoryFailSafe {
	@SubscribeEvent
	public void onTickPlayerEvent(PlayerTickEvent event) {
		EntityPlayer player = event.player;
		ItemStack stack =new ItemStack(ModItems.LAZER_GUN);
		ItemStack charge = new ItemStack(ModItems.LAZER_CHARGE);
		if(player.capabilities.isCreativeMode==true&&player.inventory.hasItemStack(stack)&&!player.inventory.hasItemStack(charge)) {
			player.inventory.addItemStackToInventory(charge);
		}
	}
}
