package me.axiumyu.listener

import com.destroystokyo.paper.MaterialTags
import com.destroystokyo.paper.MaterialTags.ARMOR
import me.axiumyu.Util.DEATH_COUNT
import me.axiumyu.Util.KEEP_ARM_TIMES
import me.axiumyu.Util.KEEP_EXP_TIMES
import me.axiumyu.Util.KEEP_INV_TIMES
import me.axiumyu.Util.addPDC
import me.axiumyu.Util.pdc
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.persistence.PersistentDataType

object PlayerDeath : Listener {

    @EventHandler
    fun onPlayerDeath(e: PlayerDeathEvent) {
        e.keepInventory = false
        e.keepLevel = false
        e.player.addPDC(DEATH_COUNT)
        val pdc = e.player.pdc()
        val keepInv = pdc.getOrDefault(KEEP_INV_TIMES, PersistentDataType.INTEGER, 0)
        val keepExp = pdc.getOrDefault(KEEP_EXP_TIMES, PersistentDataType.INTEGER, 0)
        val keepArm = pdc.getOrDefault(KEEP_ARM_TIMES, PersistentDataType.INTEGER, 0)
        if (keepInv > 0) {
            e.keepInventory = true
            e.player.addPDC(KEEP_INV_TIMES, -1)
        }
        if (keepExp > 0) {
            e.keepLevel = true
            e.player.addPDC(KEEP_EXP_TIMES, -1)
        }
        if (e.keepInventory) return
        if (keepArm > 0) {
            val armors = e.drops.filter {
                ARMOR.isTagged(it)
            }
            e.drops.removeAll(armors)
            e.itemsToKeep.addAll(armors)
            e.player.addPDC(KEEP_ARM_TIMES, -1)
        }
    }
}