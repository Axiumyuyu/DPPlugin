package me.axiumyu.listener

import me.axiumyu.Util.KILL_COMMON
import me.axiumyu.Util.KILL_EPIC
import me.axiumyu.Util.KILL_LEGENDARY
import me.axiumyu.Util.KILL_RARE
import me.axiumyu.Util.KILL_UNCOMMON
import me.axiumyu.Util.isCommon
import me.axiumyu.Util.isEpic
import me.axiumyu.Util.isLegendary
import me.axiumyu.Util.isRare
import me.axiumyu.Util.isUncommon
import me.axiumyu.Util.addPDC
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent

object KillListener : Listener {

    @EventHandler
    fun onKillEvent(event: EntityDeathEvent) {
        val killer = event.entity.killer
        if (killer !is Player) return
        when {
            isCommon(event.entity) -> killer.addPDC(KILL_COMMON)

            isUncommon(event.entity) -> killer.addPDC(KILL_UNCOMMON)

            isRare(event.entity) -> killer.addPDC(KILL_RARE)

            isEpic(event.entity) -> killer.addPDC(KILL_EPIC)

            isLegendary(event.entity) -> killer.addPDC(KILL_LEGENDARY)
        }
    }
}