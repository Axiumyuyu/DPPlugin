package me.axiumyu

import com.google.gson.Gson
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.axiumyu.command.ItemDeserialize
import me.axiumyu.command.ItemSerialize
import me.axiumyu.listener.DeathCounter
import me.axiumyu.listener.KillListener
import me.axiumyu.listener.PlayerInit
import me.axiumyu.listener.SpawnListener
import me.axiumyu.listener.ThrowableFireBall
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.PiglinBrute
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import kotlin.coroutines.coroutineContext

class DPPlugin : JavaPlugin(), Listener {
    companion object {
        val gs by lazy { Gson() }
        val mm by lazy { MiniMessage.miniMessage() }
        val PATH by lazy { getPlugin(DPPlugin::class.java).dataPath }
    }

    override fun onEnable() {

        logger.severe {
            """
            ------------------------------------------------------------------
            This plugin should only be used in specified DP-Survival.
            If you are not in that server, this plugin will NEVER work properly.
            ------------------------------------------------------------------
                        DO NOT USE THIS PLUGIN IN OTHER SERVERS
            ------------------------------------------------------------------
            """.trimIndent()
        }
        listOf(DeathCounter, KillListener, PlayerInit, SpawnListener, ThrowableFireBall).forEach {
            server.pluginManager.registerEvents(it, this)
        }
        saveDefaultConfig()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
