package xbony2.longfallboots;

import javax.annotation.ParametersAreNonnullByDefault;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@Mod(LongFallBoots.LONGFALLBOOTS)
public final class LongFallBoots {
	public static final String LONGFALLBOOTS = "longfallboots";

	public LongFallBoots(){
		final DeferredRegister<Item> deferredRegister = new DeferredRegister<>(ForgeRegistries.ITEMS, LONGFALLBOOTS);
		
		final RegistryObject<ArmorItem> longFallBoots = deferredRegister.register(LONGFALLBOOTS, () -> new ArmorItem(new IArmorMaterial(){
			@Override
			public int getDurability(final EquipmentSlotType slot){
				return slot == EquipmentSlotType.FEET ? 66 : 0;
			}

			@Override
			public int getDamageReductionAmount(final EquipmentSlotType slot){
				return slot == EquipmentSlotType.FEET ? 3 : 0;
			}

			@Override
			public int getEnchantability(){
				return 10;
			}

			@Override
			public SoundEvent getSoundEvent(){
				return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
			}

			@Override
			public Ingredient getRepairMaterial(){
				return Ingredient.EMPTY;
			}

			@Override
			public String getName(){
				return LONGFALLBOOTS + ':' + LONGFALLBOOTS;
			}

			@Override
			public float getToughness(){
				return 2.0F;
			}
		}, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));

		deferredRegister.register(FMLJavaModLoadingContext.get().getModEventBus());

		MinecraftForge.EVENT_BUS.<LivingFallEvent>addListener(e -> {
			final ItemStack boots = e.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.FEET);
			
			if(longFallBoots.orElseThrow(IllegalStateException::new).equals(boots.getItem()))
				e.setDamageMultiplier(0.0F);
			
		});
	}
}
