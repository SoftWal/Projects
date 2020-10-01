package com.aww_man.Teleportation.init;

import java.util.ArrayList;
import java.util.List;

import com.aww_man.Teleportation.items.ItemBase;
import com.aww_man.Teleportation.items.VoidStaff;
import com.aww_man.Teleportation.items.armor.ArmorBase;
import com.aww_man.Teleportation.items.poketDimsnesion.PocketBed;
import com.aww_man.Teleportation.items.poketDimsnesion.PocketEnd;
import com.aww_man.Teleportation.items.tools.AzuriteBow;
import com.aww_man.Teleportation.items.tools.Lazer_Gun;
import com.aww_man.Teleportation.items.tools.ToolAxe;
import com.aww_man.Teleportation.items.tools.ToolBattleAxe;
import com.aww_man.Teleportation.items.tools.ToolHoe;
import com.aww_man.Teleportation.items.tools.ToolPickaxe;
import com.aww_man.Teleportation.items.tools.ToolShovel;
import com.aww_man.Teleportation.items.tools.ToolSword;
import com.aww_man.Teleportation.items.tools.AzuriteArrow.AzuriteArrow;
import com.aww_man.Teleportation.items.tools.Lazer_Charge.LazerChargeArrowInit;
import com.aww_man.Teleportation.items.tools.Lazer_Charge.Lazer_Charge;
import com.aww_man.Teleportation.util.Reference;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	//Tools/ Materials
	public static final ToolMaterial MATERIAL_AZURITE = EnumHelper.addToolMaterial("material_azurite", 4, 1561, 8.0F, 3.0F, 10);
	
	
	public static final ArmorMaterial ARMOR_MATERIAL_MYTHRIL = EnumHelper.addArmorMaterial("armor_material_mythril", Reference.MOD_ID + ":mythril",  14, new int[] {3,15,6,9}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);
	//Azurite Ingot
	public static final Item AZURITE_INGOT = new ItemBase("azurite_ingot");
	//Mythril
	public static final Item Mythril = new ItemBase("mythril");
	//Tools
	public static final ItemSword AZURITE_SWORD = new ToolSword("azurite_sword", MATERIAL_AZURITE);
	public static final ItemAxe AZURITE_AXE =  new ToolAxe("azurite_axe",MATERIAL_AZURITE);
	public static final ItemPickaxe AZURITE_PICKAXE = new ToolPickaxe("azurite_pickaxe", MATERIAL_AZURITE);
	public static final Item AZURITE_ARROW = new AzuriteArrow("azurite_arrow");
	public static final Item AZURITE_BOW = new AzuriteBow("azurite_bow");
	public static final Item LAZER_GUN = new Lazer_Gun("lazer_gun");
	public static final Item LAZER_CHARGE= new Lazer_Charge("lazer_charge");
	public static final ItemSpade AZURITE_SHOVEL= new ToolShovel("azurite_shovel", MATERIAL_AZURITE);
	public static final ItemHoe AZURITE_HOE= new ToolHoe("azurite_hoe", MATERIAL_AZURITE);
	public static final ItemAxe AZURITE_BATTLE_AXE= new ToolBattleAxe("azurite_battle_axe", MATERIAL_AZURITE);
	public static final Item LAZERCHARGEARROWINIT = new LazerChargeArrowInit("lazerchargearrowinit");
	//SpecialTool
	public static final Item VOID_STAFF= new VoidStaff("void_staff");
	public static final Item POCKET_END= new PocketEnd("pocket_end");
	public static final Item POCKET_NETHER= new PocketBed("pocket_bed");



	
	//Armor
	public static final Item MYTHRIL_HELMET = new ArmorBase("mythril_helmet", ARMOR_MATERIAL_MYTHRIL, 1,EntityEquipmentSlot.HEAD);
	public static final Item MYTHRIL_CHESTPLATE = new ArmorBase("mythril_chestplate", ARMOR_MATERIAL_MYTHRIL, 1,EntityEquipmentSlot.CHEST);
	public static final Item MYTHRIL_BOOTS = new ArmorBase("mythril_boots", ARMOR_MATERIAL_MYTHRIL, 1,EntityEquipmentSlot.FEET);
	public static final Item MYTHRIL_LEGGINGS = new ArmorBase("mythril_leggings", ARMOR_MATERIAL_MYTHRIL, 2,EntityEquipmentSlot.LEGS);
}
