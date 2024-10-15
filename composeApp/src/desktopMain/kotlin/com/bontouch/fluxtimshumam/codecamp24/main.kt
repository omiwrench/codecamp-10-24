package com.bontouch.fluxtimshumam.codecamp24

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CodeCamp24",
    ) {
        App()
    }
}