package com.Paul70131.WTAPv2.client.module.modules;

import com.Paul70131.WTAPv2.client.module.Categorys;
import com.Paul70131.WTAPv2.client.module.GuiReference;
import com.Paul70131.WTAPv2.client.module.Module;
import com.Paul70131.WTAPv2.client.module.Value;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.potion.Potion;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class Keepsprint extends Module {
	
	private boolean active = false;

	public Keepsprint(Categorys category, String name) {
		super(category, name);
		// TODO Auto-generated constructor stub
	}
	@Override 
	public void init() {
		this.valueList.add(new Value("reducer", 90, 60, 100, GuiReference.GUISLIDER));
		
	}
	@Override
	public void onUpdate(Event event) {
		if (this.state && event instanceof AttackEntityEvent) {
			AttackEntityEvent attackEvent = (AttackEntityEvent)event;
			if (attackEvent.entity.getName().equals(mc.thePlayer.getName())) {
				Entity target = attackEvent.target;
				if (!this.active) {
				this.active = true;
				event.setCanceled(true);
				
				mc.getNetHandler().addToSendQueue(new C02PacketUseEntity(target, C02PacketUseEntity.Action.ATTACK));
	
					
	            if (target.canAttackWithItem()) {
	                if (!target.hitByEntity(mc.thePlayer)) {
	                    float f = (float)mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
	                    int i = 0;
	                    float f1 = 0.0F;
	
	                    if (target instanceof EntityLivingBase) {
	                        f1 = EnchantmentHelper.func_152377_a(mc.thePlayer.getHeldItem(), ((EntityLivingBase)target).getCreatureAttribute());
	                    } else {
	                        f1 = EnchantmentHelper.func_152377_a(mc.thePlayer.getHeldItem(), EnumCreatureAttribute.UNDEFINED);
	                    }
	
	                    i = i + EnchantmentHelper.getKnockbackModifier(mc.thePlayer);
	
	                    if (mc.thePlayer.isSprinting())
	                    {
	                        ++i;
	                    }
	
	                    if (f > 0.0F || f1 > 0.0F)
	                    {
	                        boolean flag = mc.thePlayer.fallDistance > 0.0F && !mc.thePlayer.onGround && !mc.thePlayer.isOnLadder() && !mc.thePlayer.isInWater() && !mc.thePlayer.isPotionActive(Potion.blindness) && mc.thePlayer.ridingEntity == null && target instanceof EntityLivingBase;
	
	                        if (flag && f > 0.0F)
	                        {
	                            f *= 1.5F;
	                        }
	
	                        f = f + f1;
	                        boolean flag1 = false;
	                        int j = EnchantmentHelper.getFireAspectModifier(mc.thePlayer);
	
	                        if (target instanceof EntityLivingBase && j > 0 && !target.isBurning())
	                        {
	                            flag1 = true;
	                            target.setFire(1);
	                        }
	
	                        double d0 = target.motionX;
	                        double d1 = target.motionY;
	                        double d2 = target.motionZ;
	                        boolean flag2 = target.attackEntityFrom(DamageSource.causePlayerDamage(mc.thePlayer), f);
	
	                        if (flag2)
	                        {
	                            if (i > 0)
	                            {
	                                target.addVelocity((double)(-MathHelper.sin(mc.thePlayer.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(mc.thePlayer.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F));
	                                mc.thePlayer.motionX *= (this.getValue("reducer").value / 100);
	                                mc.thePlayer.motionZ *= (this.getValue("reducer").value / 100);
	                             //   mc.thePlayer.setSprinting(false);
	                            }
	
	                            if (target instanceof EntityPlayerMP && target.velocityChanged)
	                            {
	                                ((EntityPlayerMP)target).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(target));
	                                target.velocityChanged = false;
	                                target.motionX = d0;
	                                target.motionY = d1;
	                                target.motionZ = d2;
	                            }
	
	                            if (flag)
	                            {
	                                mc.thePlayer.onCriticalHit(target);
	                            }
	
	                            if (f1 > 0.0F)
	                            {
	                                mc.thePlayer.onEnchantmentCritical(target);
	                            }
	
	                            mc.thePlayer.setLastAttacker(target);
	
	                            if (target instanceof EntityLivingBase)
	                            {
	                                EnchantmentHelper.applyThornEnchantments((EntityLivingBase)target, mc.thePlayer);
	                            }
	
	                            EnchantmentHelper.applyArthropodEnchantments(mc.thePlayer, target);
	                            ItemStack itemstack = mc.thePlayer.getCurrentEquippedItem();
	                            Entity entity = target;
	
	                            if (target instanceof EntityDragonPart)
	                            {
	                                IEntityMultiPart ientitymultipart = ((EntityDragonPart)target).entityDragonObj;
	
	                                if (ientitymultipart instanceof EntityLivingBase)
	                                {
	                                    entity = (EntityLivingBase)ientitymultipart;
	                                }
	                            }
	
	                            if (itemstack != null && entity instanceof EntityLivingBase)
	                            {
	                                itemstack.hitEntity((EntityLivingBase)entity, mc.thePlayer);
	
	                                if (itemstack.stackSize <= 0)
	                                {
	                                    mc.thePlayer.destroyCurrentEquippedItem();
	                                }
	                            }
	
	                            if (target instanceof EntityLivingBase)
	                            {
	                                mc.thePlayer.addStat(StatList.damageDealtStat, Math.round(f * 10.0F));
	
	                                if (j > 0)
	                                {
	                                    target.setFire(j * 4);
	                                }
	                            }
	
	                            mc.thePlayer.addExhaustion(0.3F);
	                        }
	                        else if (flag1)
	                        {
	                            target.extinguish();
	                        	}
	                    	}
	                    this.active = false;
		                }
		            }
	            }
			} 
		}
    }
}
