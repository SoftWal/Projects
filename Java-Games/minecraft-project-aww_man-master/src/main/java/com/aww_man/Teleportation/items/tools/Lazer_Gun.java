package com.aww_man.Teleportation.items.tools;

import java.util.List;

import javax.annotation.Nullable;

import com.aww_man.Teleportation.Main;
import com.aww_man.Teleportation.init.ModItems;
import com.aww_man.Teleportation.util.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Lazer_Gun extends ItemBow implements IHasModel{
	public Lazer_Gun(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.modtabs);
		ModItems.ITEMS.add(this);
		setMaxDamage(1000);
		setMaxStackSize(1);
		
		
		
		this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter(){
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (entityIn == null){
                    return 0.0F;
                }
                else{

                    return entityIn.getActiveItemStack().getItem() != ModItems.LAZER_GUN ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F;
                }
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter(){
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn){
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
		
		ModItems.ITEMS.add(this);
	}
	
	public ItemStack findAmmo(EntityPlayer player){			
			ItemStack stack = new ItemStack(ModItems.LAZER_CHARGE);
	        int x = player.inventory.getSlotFor(stack);
	        ItemStack charge = player.inventory.getStackInSlot(x);
	        if(player.inventory.hasItemStack(charge)&&charge.getItemDamage()<100) {
	        	charge.damageItem(5, player);
	        	ItemStack newItem = new ItemStack(ModItems.LAZERCHARGEARROWINIT);
	        	return newItem;
	        }
	        else {
	        	return ItemStack.EMPTY;
	        }
	    }

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft){
	        if (entityLiving instanceof EntityPlayer)
	        {
	        	
	            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
	            boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
	            ItemStack itemstack = this.findAmmo(entityplayer);

	            int i = this.getMaxItemUseDuration(stack) - timeLeft;
	            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, !itemstack.isEmpty() || flag);
	            if (i < 0) return;

	            if (!itemstack.isEmpty() || flag){
	                if (itemstack.isEmpty()){
	                    itemstack = new ItemStack(Items.ARROW);
	                }

	                float f = getArrowVelocity(i);

	                if ((double)f >= 0.1D){
	                    boolean flag1 = entityplayer.capabilities.isCreativeMode || (itemstack.getItem() instanceof ItemArrow && ((ItemArrow) itemstack.getItem()).isInfinite(itemstack, stack, entityplayer));

	                    if (!worldIn.isRemote){
	                        ItemArrow itemarrow = (ItemArrow)(itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW);
	                        EntityArrow entityarrow = itemarrow.createArrow(worldIn, itemstack, entityplayer);
	                        entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 1.0F);
	                        entityarrow.setDamage(entityarrow.getDamage() + (double)30 * 0.5D + 0.5D);
	                        if (f == 1.0F){
	                            entityarrow.setIsCritical(true);
	                        }

	                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
	                        
	                        if (j > 0){
	                            entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
	                        }

	                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

	                        if (k > 0){
	                            entityarrow.setKnockbackStrength(k);
	                        }

	                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0){
	                            entityarrow.setFire(100);
	                        }

	                        stack.damageItem(1, entityplayer);

	                        if (flag1 || entityplayer.capabilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)){
	                            entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
	                        }

	                        worldIn.spawnEntity(entityarrow);
	                    }

	                    worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

	                    if (!flag1 && !entityplayer.capabilities.isCreativeMode)
	                    {
	                        itemstack.shrink(1);

	                        if (itemstack.isEmpty()){
	                            entityplayer.inventory.deleteStack(itemstack);
	                        }
	                    }

	                    entityplayer.addStat(StatList.getObjectUseStats(this));
	                }
	            }
	        }
	    }

	public static float getArrowVelocity(int charge){
			float f = (float)charge / 20.0F;
	        f = (f * f + f * 2.0F) / 3.0F;
	
	        if (f > 1.0F){
	            f = 1.0F;
	        }
	
	        return f;
	    }
	@Override
	protected boolean isArrow(ItemStack stack) {
		if(stack.getItem() == ModItems.LAZER_CHARGE){
			return true;
		}
		return false;
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List <String> tooltip, ITooltipFlag flagIn ) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add("Must be used with the laser charge, does not consume durabbility in creative");
		
	}
	@Override
	public void registerModels() {
		Main.proxy.registerItemRender(this,0,"inventory");

	}
}
	