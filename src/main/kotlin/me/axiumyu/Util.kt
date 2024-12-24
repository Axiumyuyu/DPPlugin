package me.axiumyu

import net.kyori.adventure.text.format.TextColor.color
import org.bukkit.NamespacedKey
import org.bukkit.entity.LivingEntity
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.persistence.PersistentDataHolder
import org.bukkit.persistence.PersistentDataType.INTEGER
import org.bukkit.plugin.java.JavaPlugin.getPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.Vector

import kotlin.math.cos
import kotlin.math.sin

object Util {
    @JvmStatic
    fun axiumyuKey(name: String): NamespacedKey {
        return NamespacedKey("axiumyu", name)
    }

    @JvmField
    val DEATH_COUNT = axiumyuKey("death_count")

    @JvmField
    val STATE = axiumyuKey("state")

    @JvmField
    val KILL_COMMON = axiumyuKey("kill_common")

    @JvmField
    val KILL_UNCOMMON = axiumyuKey("kill_uncommon")

    @JvmField
    val KILL_RARE = axiumyuKey("kill_rare")

    @JvmField
    val KILL_EPIC = axiumyuKey("kill_epic")

    @JvmField
    val KILL_LEGENDARY = axiumyuKey("kill_legendary")

    @JvmField
    val FIRST = axiumyuKey("first")

    @JvmField
    val KEEP_INV_TIMES = axiumyuKey("keep_inv_times")

    @JvmField
    val KEEP_EXP_TIMES = axiumyuKey("keep_exp_times")

    @JvmField
    val KEEP_ARM_TIMES = axiumyuKey("keep_arm_times")

    /**
     *  键盘自己打字.jpg
     */
    @JvmStatic
    fun <T : PersistentDataHolder> T.pdc(): PersistentDataContainer {
        return this.persistentDataContainer
    }

    @JvmStatic
    fun <T : PersistentDataHolder> T.addPDC(nameSpace : NamespacedKey, number: Int = 1) {
        this.pdc().set(nameSpace, INTEGER, this.pdc().get(nameSpace, INTEGER)!! + number)
    }

    @JvmStatic
    fun isCommon(entity: LivingEntity) = entity.scoreboardTags.contains("common")

    @JvmStatic
    fun isUncommon(entity: LivingEntity) = entity.scoreboardTags.contains("uncommon")

    @JvmStatic
    fun isRare(entity: LivingEntity) = entity.scoreboardTags.contains("rare")

    @JvmStatic
    fun isEpic(entity: LivingEntity) = entity.scoreboardTags.contains("epic")

    @JvmStatic
    fun isLegendary(entity: LivingEntity) = entity.scoreboardTags.contains("legendary")


    @JvmStatic
    fun pitchYaw2Vector(pitch: Number, yaw: Number): Vector {
        //调整yaw方向：在Minecraft中，+yaw是向东转，所以我们需要使用-yaw
        val adjustedYaw = -Math.toRadians(yaw.toDouble())

        // Pitch不需要调整，但是要记得Minecraft的pitch是向下为正
        val pitchRad = Math.toRadians(pitch.toDouble())

        //计算方向向量的各个分量
        val x = -sin(adjustedYaw) * cos(pitchRad) //注意X轴方向
        val y = sin(pitchRad)                     //在Minecraft中Y轴向上为正
        val z = cos(adjustedYaw) * cos(pitchRad)  //注意Z轴方向

        return Vector(x, y, z)
    }

    @JvmStatic
    inline fun laterTask(delay : Long, crossinline action : ()-> Unit) {
        object : BukkitRunnable() { override fun run() { action() } }.runTaskLater(getPlugin(DPPlugin::class.java), delay)
    }

    enum class Color(rgb: Int){
        LIGHT_PINK(0xFFEDFA),
        PINK(0xE6AED6),
        DEEP_PINK(0xCC78B4),

        LIGHT_BLUE(0xA3FFFC),
        BLUE(0x5CE6E1),
        DEEP_BLUE(0x21CCC6),

        LIGHT_GREEN(0xC2FFC2),
        GREEN(0x87E687),
        DEEP_GREEN(0x56CC56),

        LIGHT_PURPLE(0xDAC9FF),
        PURPLE(0xAC93E6),
        DEEP_PURPLE(0x8564CC),

        WARN(0xF05179),
        TIPS(0xFFEA3A),
        INFO(0xCCCCCC);

        val color = color(rgb)
    }
}