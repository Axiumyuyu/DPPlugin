package me.axiumyu.command

import me.axiumyu.Util.STATE
import me.axiumyu.Util.laterTask
import me.axiumyu.Util.pdc
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object PLayerStart : CommandExecutor {
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        if (p0 !is Player) return false
        p0.run {
            if (pdc().get(STATE, PersistentDataType.STRING) == "started") return false
            addPotionEffect(PotionEffect(PotionEffectType.RESISTANCE, 36000, 10, false, true, true))
            inventory.setItem(inventory.firstEmpty(), ItemStack(Material.CHERRY_LOG, 5))
            pdc().set(STATE, PersistentDataType.STRING, "started")
            teleport(Location(world, 0.0, 1.0, 0.0))
            respawnLocation = location
        }
        laterTask(36000) {
            p0.pdc().set(STATE, PersistentDataType.STRING,"vulnerable")
        }
        return true
    }
}