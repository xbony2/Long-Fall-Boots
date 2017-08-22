package xbony2.longfallboots;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = LongFallBoots.MODID, version = LongFallBoots.VERSION)
public class LongFallBoots {
	public static final String MODID = "longfallboots";
	public static final String VERSION = "1.2.0a";
	
	public static Item longFallBoots;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		longFallBoots = new ItemLongFallBoots();
		MinecraftForge.EVENT_BUS.register(this); //This gets stupider and stupider every version.
	}
	
	@SubscribeEvent
	public void registerItems(Register<Item> event){
		event.getRegistry().register(longFallBoots);
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event){
		ModelLoader.setCustomModelResourceLocation(longFallBoots, 0, new ModelResourceLocation(longFallBoots.getRegistryName(), "inventory"));
	}
}
