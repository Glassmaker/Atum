package rebelkeithy.mods.atum.artifacts;

import java.util.List;

import org.lwjgl.input.Keyboard;

import rebelkeithy.mods.atum.AtumItems;
import rebelkeithy.mods.atum.entities.EntityGhost;
import rebelkeithy.mods.atum.entities.EntityPharaoh;
import rebelkeithy.mods.atum.entities.EntityStoneSoldier;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;
import net.minecraftforge.common.IArmorTextureProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;

public class ItemHorusFlight extends ItemArmor implements IArmorTextureProvider
{
	String texture;

	public ItemHorusFlight(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
	{
		super(par1, par2EnumArmorMaterial, par3, par4);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@ForgeSubscribe
	public void onJump(LivingJumpEvent event)
	{
		if(event.entityLiving.getCurrentArmor(0) != null)
		{
			if(event.entityLiving.getCurrentArmor(0).itemID == this.itemID)
			{
				event.entityLiving.motionY += 0.2;
				event.entityLiving.motionX *= 1.2;
				event.entityLiving.motionZ *= 1.2;
			}
		}
	}
	 
	 @ForgeSubscribe
	 public void onFallDamage(LivingFallEvent event)
	 {
		 if(event.entityLiving.getCurrentArmor(0) != null)
		 {
			 if(event.entityLiving.getCurrentArmor(0).itemID == AtumItems.horusFlight.itemID)
			 {
				 event.distance = 0;
			 }
		 }
	 }

    @SideOnly(Side.CLIENT)

    /**
     * Return an item rarity from EnumRarity
     */
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }

    @SideOnly(Side.CLIENT)

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
    {
    	if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
    	{
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "Nimbleness I: Increased jump height,");
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "protection from fall damage");
    	} else {
        	par3List.add("Nimbleness I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
    	}
    }


	public Item setTextureFile(String string) 
	{
		texture = string;
		return this;
	}

	@Override
	public String getArmorTextureFile(ItemStack itemstack) 
	{
		return "/armor/" + texture + ".png";
	}

    /**
     * Return whether this item is repairable in an anvil.
     */
	@Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return par2ItemStack.itemID == Item.diamond.itemID;
    }
}
