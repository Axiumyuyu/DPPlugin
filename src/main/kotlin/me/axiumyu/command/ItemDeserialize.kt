package me.axiumyu.command

import com.google.gson.Gson
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.io.File

object ItemDeserialize : CommandExecutor {
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        if (p0 !is Player) return false
        if (p3.isEmpty()) return false
        val i = p3[0].toIntOrNull()?:1
        /*if (config.get("items.$i.serialize-name") == null ||
            config.get("items.$i.name-tostring") == null) return false
        val item = config.getList("items.$i.serialize") ?: listOf<String>()
        val itemMap = mutableMapOf<String, Any>()
        item.forEach {
            if (it is String && config.get("items.$i.serialize.${it}") != null) itemMap[it] =
                config.get("items.$i.serialize.${it}")!!
        }*/
        val index = p0.inventory.firstEmpty()
        lateinit var item : ItemStack
        if (index != -1) {
            Thread{
                item = Gson().fromJson(File("item//$i.json").readText(Charsets.UTF_8), ItemStack::class.java) ?: ItemStack(Material.STONE)
            }.start()
            p0.inventory.setItem(index, item)
        }
        return true
    }

}