package me.axiumyu

import com.google.gson.Gson
import me.axiumyu.listener.*
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class DPPlugin : JavaPlugin(), Listener {
    companion object {
        val gs by lazy { Gson() }
        val mm by lazy { MiniMessage.miniMessage() }
        val PATH by lazy { getPlugin(DPPlugin::class.java).dataPath }
    }

    override fun onEnable() {

        logger.severe {
            """
            -------------------------------------------------------------------
            This plugin should only be used in specified DP-Survival.
            If you are not in that server, this plugin will NEVER work properly.
            -------------------------------------------------------------------
                        DO NOT USE THIS PLUGIN IN OTHER SERVERS
            -------------------------------------------------------------------
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
