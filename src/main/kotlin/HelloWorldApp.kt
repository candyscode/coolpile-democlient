import javafx.application.Application
import javafx.scene.text.FontWeight
import tornadofx.*

class HelloWorldApp : App(HelloWorld::class, Styles::class)

class Styles : Stylesheet() {
    init {
        label {
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
    }
}

fun main(args: Array<String>) {
    Application.launch(HelloWorldApp::class.java, *args)
}