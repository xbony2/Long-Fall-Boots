package xbony2.longfallboots;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@Mod(LongFallBoots.LONGFALLBOOTS)
public final class LongFallBoots {
	public static final String LONGFALLBOOTS = "longfallboots";

	public LongFallBoots(){
		final DeferredRegister<Item> deferredRegister = DeferredRegister.create(ForgeRegistries.ITEMS, LONGFALLBOOTS);
		
		final RegistryObject<ArmorItem> longFallBoots = deferredRegister.register(LONGFALLBOOTS, () -> new ArmorItem(new ArmorMaterial(){
			@Override
			public int getDurabilityForSlot(final EquipmentSlot slot){
				return slot == EquipmentSlot.FEET ? 429 : 0;
			}

			@Override
			public int getDefenseForSlot(final EquipmentSlot slot){
				return slot == EquipmentSlot.FEET ? 3 : 0;
			}

			@Override
			public int getEnchantmentValue(){
				return 10;
			}

			@Override
			public SoundEvent getEquipSound(){
				return SoundEvents.ARMOR_EQUIP_GENERIC;
			}

			@Override
			public Ingredient getRepairIngredient(){
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

			@Override
			public float getKnockbackResistance(){ // all armors have 0 except Netherite (0.1)
				return 0;
			}
		}, EquipmentSlot.FEET, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

		deferredRegister.register(FMLJavaModLoadingContext.get().getModEventBus());

		MinecraftForge.EVENT_BUS.<LivingFallEvent>addListener(EventPriority.LOW, e -> {
			final ItemStack boots = e.getEntity().getItemBySlot(EquipmentSlot.FEET);
			
			if(longFallBoots.orElseThrow(IllegalStateException::new).equals(boots.getItem()))
				e.setDamageMultiplier(0.0F);
		});
	}
}
