package dev.toastmc.client.util

import net.minecraft.client.MinecraftClient
import net.minecraft.text.LiteralText
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import java.io.File
import kotlin.math.atan2
import kotlin.math.sqrt

val mc = MinecraftClient.getInstance()
val MOD_DIRECTORY: File = File(mc.runDirectory, "toastclient" + "/")

fun lit(string: String): LiteralText {
    return LiteralText(string)
}

fun getNeededYaw(vec: Vec3d): Float {
    return mc.player!!.yaw + MathHelper.wrapDegrees(
        Math.toDegrees(atan2(vec.z - mc.player!!.z, vec.x - mc.player!!.x)).toFloat() - 90f - mc.player!!.yaw
    )
}

fun getNeededPitch(vec: Vec3d): Float {
    val diffX: Double = vec.x - mc.player!!.x
    val diffY: Double = vec.y - (mc.player!!.y + mc.player!!.getEyeHeight(mc.player!!.pose))
    val diffZ: Double = vec.z - mc.player!!.z
    val diffXZ = sqrt(diffX * diffX + diffZ * diffZ)
    return mc.player!!.pitch + MathHelper.wrapDegrees(
        (-Math.toDegrees(
            atan2(
                diffY,
                diffXZ
            )
        )).toFloat() - mc.player!!.pitch
    )
}

fun distance(blockPos: BlockPos, blockPos1: BlockPos): Double {
    val dX = blockPos1.x - blockPos.x
    val dY = blockPos1.y - blockPos.x
    val dZ = blockPos1.y - blockPos.z
    return sqrt((dX * dX + dY * dY + dZ * dZ).toDouble())
}