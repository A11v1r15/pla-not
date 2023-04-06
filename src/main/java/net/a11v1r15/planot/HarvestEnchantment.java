package net.a11v1r15.planot;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;

public class HarvestEnchantment extends Enchantment {
    public HarvestEnchantment() {
        super(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.BREAKABLE, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }
 
    @Override
    public int getMinPower(int level) {
        return level * 20;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof HoeItem;
    }
 
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof MooshroomEntity && !target.world.isClient) {
            target.world.spawnEntity(new ItemEntity(target.world, target.getX(), target.getBodyY(1.0), target.getZ(), new ItemStack(((MooshroomEntity)target).getVariant().getMushroomState().getBlock())));
        }
 
        super.onTargetDamaged(user, target, level);
    }
}