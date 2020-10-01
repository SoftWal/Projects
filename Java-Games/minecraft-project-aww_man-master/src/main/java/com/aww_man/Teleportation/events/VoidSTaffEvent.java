package com.aww_man.Teleportation.events;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class VoidSTaffEvent {
	@SubscribeEvent
	public void onTickPlayerEvent(PlayerTickEvent event) {
		Minecraft mc =Minecraft.getMinecraft();
		if(event.player instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) event.player;
			ItemStack itemheld = player.getHeldItemMainhand();
			ItemStack item = new ItemStack(Item.REGISTRY.getObjectById(368));
			if(player.inventory.hasItemStack(item)&&itemheld.getItemDamage()==100&&itemheld.getMaxDamage()==100) {
				mc.player.sendChatMessage("The staff has consumed ender pearls and replenished its durability");
				int x=player.inventory.getSlotFor(item);
				ItemStack pearls = player.inventory.getStackInSlot(x);
				while(itemheld.getItemDamage()!=0&&pearls.getCount()!=0) {
					int y=pearls.getCount();
					itemheld.setItemDamage(itemheld.getItemDamage()-10);
					pearls.setCount(y-1);
				}
			}
		}
	} 
}
