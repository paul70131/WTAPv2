package com.Paul70131.WTAPv2.client.util;

import java.util.List;

import com.Paul70131.WTAPv2.client.module.ModuleManager;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class rayTrace {
	private static Minecraft mc = Minecraft.getMinecraft();
	
	private static Entity pointedEntity;
	public static double hitbox = 1;
	
	
	public static void getRayTrace(double value) {
		if (mc.getRenderViewEntity() != null)
	    {
	        if (mc.theWorld != null)
	        {
	            pointedEntity = null;
	            double d0 = value;
	            mc.objectMouseOver = mc.getRenderViewEntity().rayTrace(d0, 1.0F);
	            double d1 = d0;
	            Vec3 vec3 = mc.getRenderViewEntity().getPositionEyes(1.0F);

	            Vec3 vec31 = mc.getRenderViewEntity().getLook(1.0F);
	            Vec3 vec32 = vec3.addVector(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0);
	            pointedEntity = null;
	            Vec3 vec33 = null;
	            float f1 = 1.0F;
	            List list = (List) mc.theWorld.getEntitiesWithinAABBExcludingEntity(mc.getRenderViewEntity(), mc.getRenderViewEntity().getEntityBoundingBox().addCoord(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0).expand((double)f1, (double)f1, (double)f1));
	            double d2 = d1;

	            for (int i = 0; i < list.size(); ++i)
	            {
	                Entity entity = (Entity)((java.util.List<Entity>) list).get(i);

	                if (entity.canBeCollidedWith())
	                {
	                	float f2;
	                	if (!ModuleManager.getModuleByName("Hitbox").state) {
	                		f2 = entity.getCollisionBorderSize();
	                	} else {
	                		f2 = (float) (entity.getCollisionBorderSize() * ModuleManager.getModuleByName("Hitbox").getValue("multiplier").value);
	                	}
	                    AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand((double)f2, (double)f2, (double)f2);
	                    MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);

	                    if (axisalignedbb.isVecInside(vec3))
	                    {
	                        if (0.0D < d2 || d2 == 0.0D)
	                        {
	                            pointedEntity = entity;
	                            vec33 = movingobjectposition == null ? vec3 : movingobjectposition.hitVec;
	                            d2 = 0.0D;
	                        }
	                    }
	                    else if (movingobjectposition != null)
	                    {
	                        double d3 = vec3.distanceTo(movingobjectposition.hitVec);

	                        if (d3 < d2 || d2 == 0.0D)
	                        {
	                            if (entity == mc.getRenderViewEntity().ridingEntity && !entity.canRiderInteract())
	                            {
	                                if (d2 == 0.0D)
	                                {
	                                    pointedEntity = entity;
	                                    vec33 = movingobjectposition.hitVec;
	                                }
	                            }
	                            else
	                            {
	                                pointedEntity = entity;
	                                vec33 = movingobjectposition.hitVec;
	                                d2 = d3;
	                            }
	                        }
	                    }
	                }
	            }

	            if (pointedEntity != null && (d2 < d1 || mc.objectMouseOver == null))
	            {
	                mc.objectMouseOver = new MovingObjectPosition(pointedEntity, vec33);
	            }
	        }
	    }
	 }
	public static Entity getRayTraceNoHitbox(double value) {
		if (mc.getRenderViewEntity() != null)
	    {
	        if (mc.theWorld != null)
	        {
	            pointedEntity = null;
	            double d0 = value;
	            mc.objectMouseOver = mc.getRenderViewEntity().rayTrace(d0, 1.0F);
	            double d1 = d0;
	            Vec3 vec3 = mc.getRenderViewEntity().getPositionEyes(1.0F);

	            Vec3 vec31 = mc.getRenderViewEntity().getLook(1.0F);
	            Vec3 vec32 = vec3.addVector(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0);
	            pointedEntity = null;
	            Vec3 vec33 = null;
	            float f1 = 1.0F;
	            List list = (List) mc.theWorld.getEntitiesWithinAABBExcludingEntity(mc.getRenderViewEntity(), mc.getRenderViewEntity().getEntityBoundingBox().addCoord(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0).expand((double)f1, (double)f1, (double)f1));
	            double d2 = d1;

	            for (int i = 0; i < list.size(); ++i)
	            {
	                Entity entity = (Entity)((java.util.List<Entity>) list).get(i);

	                if (entity.canBeCollidedWith())
	                {
	                	float f2;
	                	f2 = entity.getCollisionBorderSize();

	                    AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand((double)f2, (double)f2, (double)f2);
	                    MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);

	                    if (axisalignedbb.isVecInside(vec3))
	                    {
	                        if (0.0D < d2 || d2 == 0.0D)
	                        {
	                            pointedEntity = entity;
	                            vec33 = movingobjectposition == null ? vec3 : movingobjectposition.hitVec;
	                            d2 = 0.0D;
	                        }
	                    }
	                    else if (movingobjectposition != null)
	                    {
	                        double d3 = vec3.distanceTo(movingobjectposition.hitVec);

	                        if (d3 < d2 || d2 == 0.0D)
	                        {
	                            if (entity == mc.getRenderViewEntity().ridingEntity && !entity.canRiderInteract())
	                            {
	                                if (d2 == 0.0D)
	                                {
	                                    pointedEntity = entity;
	                                    vec33 = movingobjectposition.hitVec;
	                                }
	                            }
	                            else
	                            {
	                                pointedEntity = entity;
	                                vec33 = movingobjectposition.hitVec;
	                                d2 = d3;
	                            }
	                        }
	                    }
	                }
	            }

	            if (pointedEntity != null && (d2 < d1 || mc.objectMouseOver == null))
	            {
	               return pointedEntity;
	            }
	        }
	    }
		return pointedEntity;
	 }
}