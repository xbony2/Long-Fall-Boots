package xbony2.longfallboots;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = LongFallBoots.MODID, version = LongFallBoots.VERSION, dependencies = LongFallBoots.DEPENDENCIES)
@EventBusSubscriber(modid = LongFallBoots.MODID)
public class LongFallBoots {
	public static final String MODID = "longfallboots";
	public static final String VERSION = "1.2.1a";
	public static final String DEPENDENCIES = "required-after:forge@[14.21.1.2387,)";
	
	public static final Item LONG_FALL_BOOTS = new ItemLongFallBoots();

	@SubscribeEvent
	public static void onItemRegistry(Register<Item> event){
		event.getRegistry().register(LONG_FALL_BOOTS);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event){
		ModelLoader.setCustomModelResourceLocation(LONG_FALL_BOOTS, 0, new ModelResourceLocation(LONG_FALL_BOOTS.getRegistryName(), "inventory"));
	}

	@SubscribeEvent
	public static void onLivingFall(LivingFallEvent event) {
		if (event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemLongFallBoots)
			event.setDamageMultiplier(0);
	}
}
