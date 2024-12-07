package me.axiumyu.command

import me.axiumyu.Util.ITEM_NUMBER
import me.axiumyu.Util.pdc
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType

class ItemSerialize(val config : FileConfiguration) : CommandExecutor {

    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        if (p0 !is Player) return false
        val item = p0.inventory.itemInMainHand
        val pdc = p0.pdc()
        val i = pdc.get(ITEM_NUMBER, PersistentDataType.INTEGER)

        pdc.set(ITEM_NUMBER, PersistentDataType.INTEGER, pdc.get(ITEM_NUMBER, PersistentDataType.INTEGER)!! + 1)
        return true
    }
}