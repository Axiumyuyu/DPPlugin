package me.axiumyu.listener

import net.kyori.adventure.text.Component.text
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntitySpawnEvent

object SpawnListener : Listener {
    @EventHandler
    fun onSpawn(event: EntitySpawnEvent) {
        if (event.entity.location.y >-32) return
        if (event.entity.name().toString().contains("Common")){
            event.entity.world.sendMessage(text("Common"))
        }else if (event.entity.name().toString().contains("Uncommon")){
            event.entity.world.sendMessage(text("Uncommon"))
        }else if (event.entity.name().toString().contains("Rare")){
            event.entity.world.sendMessage(text("Rare"))
        }else if (event.entity.name().toString().contains("Epic")){
            event.entity.world.sendMessage(text("Epic"))
        }else if (event.entity.name().toString().contains("Legendary")){
            event.entity.world.sendMessage(text("Legendary"))
        }
        event.entity.world.players.forEach {
            it.sendMessage(text("${it.totalExperience}all exp"))
        }
    }
}