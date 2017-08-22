package xbony2.longfallboots;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemLongFallBoots extends ItemArmor {
	public static final ArmorMaterial MATERIAL = EnumHelper.addArmorMaterial("longfallboots", "longfallboots:longfallboots", 66, new int[]{3, 8, 6, 6}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3.0F);

	public ItemLongFallBoots(){
		super(MATERIAL, 1, EntityEquipmentSlot.FEET);
		this.setCreativeTab(CreativeTabs.COMBAT);
		this.setRegistryName("longfallboots");
		this.setUnlocalizedName("longfallboots");
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemstack){
		player.fallDistance = itemstack.getItem() == LongFallBoots.longFallBoots ? 1.0f : 0.0f;
	}
}
