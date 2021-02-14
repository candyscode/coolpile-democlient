import javafx.scene.text.FontWeight
import tornadofx.Stylesheet
import tornadofx.px

class Styles : Stylesheet() {
    init {
        Companion.label {
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
    }
}