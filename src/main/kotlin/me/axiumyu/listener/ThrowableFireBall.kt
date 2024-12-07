package me.axiumyu.listener

import me.axiumyu.Util.pitchYaw2Vector
import org.bukkit.GameMode
import org.bukkit.Material.FIRE_CHARGE
import org.bukkit.entity.Fireball
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

object ThrowableFireBall : Listener {
    @EventHandler
    fun onFireBallThrow(event: PlayerInteractEvent) {
        if (event.item?.type == null) return
        if (event.item?.type != FIRE_CHARGE) return
        val pl = event.player
        if (pl.gameMode!= GameMode.SURVIVAL) return
        if (event.action.isRightClick){
            event.isCancelled = true
            val vector = pitchYaw2Vector(pl.pitch, pl.yaw)
            event.player.launchProjectile(Fireball::class.java, vector){
                it.isGlowing = true
            }
        }
    }
}