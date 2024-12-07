package me.axiumyu.listener

import me.axiumyu.Util.Color.TIPS
import me.axiumyu.Util.DEATH_COUNT
import me.axiumyu.Util.KEEP_INV_TIMES
import me.axiumyu.Util.pdc
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.title.TitlePart
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.persistence.PersistentDataType.INTEGER

object DeathCounter : Listener {
    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        val pl = event.player
        val pdc = pl.pdc()
        pdc.set(DEATH_COUNT,INTEGER,pdc.get(DEATH_COUNT,INTEGER)!!+1)
        if (pdc.get(KEEP_INV_TIMES, INTEGER)!!>0){
            event.setShouldDropExperience(false)
            event.keepInventory = true
            pdc.set(KEEP_INV_TIMES, INTEGER, pdc.get(KEEP_INV_TIMES, INTEGER)!!-1)
            pl.sendTitlePart(TitlePart.TITLE,text().content("你消耗了一次死亡不掉落次数").color(TIPS.color).build())
        }
    }
}