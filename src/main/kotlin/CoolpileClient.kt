import javafx.application.Application
import tornadofx.*

class CoolpileClient : App(CompileView::class, Styles::class)

fun main(args: Array<String>) {
    Application.launch(CoolpileClient::class.java, *args)
}