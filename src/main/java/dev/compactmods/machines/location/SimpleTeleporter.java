package dev.compactmods.machines.location;

import io.github.fabricators_of_create.porting_lib.extensions.ITeleporter;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import java.util.function.Function;

public record SimpleTeleporter(Vec3 pos) implements ITeleporter {

    public static SimpleTeleporter to(Vec3 pos) {
        return new SimpleTeleporter(pos);
    }

    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        entity = repositionEntity.apply(false);
        entity.teleportTo(pos.x, pos.y, pos.z);
        return entity;
    }
}
