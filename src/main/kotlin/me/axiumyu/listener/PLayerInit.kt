package me.axiumyu.listener

import me.axiumyu.Util.DEATH_COUNT
import me.axiumyu.Util.FIRST
import me.axiumyu.Util.ITEM_NUMBER
import me.axiumyu.Util.KILL_COMMON
import me.axiumyu.Util.KILL_EPIC
import me.axiumyu.Util.KILL_LEGENDARY
import me.axiumyu.Util.KILL_RARE
import me.axiumyu.Util.KILL_UNCOMMON
import me.axiumyu.Util.STATE
import me.axiumyu.Util.pdc
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.persistence.PersistentDataType.*

object PlayerInit : Listener {
    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        e.player.pdc().apply {
            if (!has(FIRST,BOOLEAN)){
                set(FIRST, BOOLEAN, true)
                set(DEATH_COUNT, INTEGER, 0)
                set(STATE, STRING, "")
                set(KILL_COMMON, INTEGER, 0)
                set(KILL_UNCOMMON, INTEGER, 0)
                set(KILL_RARE, INTEGER, 0)
                set(KILL_EPIC, INTEGER, 0)
                set(KILL_LEGENDARY, INTEGER, 0)
            }
        }
    }
}