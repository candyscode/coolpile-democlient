package util

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.stream.Collectors

fun runOnHost(command: Array<String>, onErrorAction: () -> Unit = {}) =
    Runtime.getRuntime().exec(command).runOnHost(onErrorAction)

private fun Process.runOnHost(onErrorAction: () -> Unit): String {
    val returnCode = waitFor()

    val reader = BufferedReader(InputStreamReader(inputStream))
    val inputStreamText = reader.lines().collect(Collectors.joining("\n"))

    if (returnCode != 0) onErrorAction()

    return inputStreamText
}