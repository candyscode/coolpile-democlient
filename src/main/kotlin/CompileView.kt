import javafx.geometry.Orientation
import tornadofx.*

class CompileView : View() {
    override val root = form {
        fieldset(labelPosition = Orientation.VERTICAL) {
            label("C Code")
            textarea {
                prefWidth = 800.0
                prefRowCount = 7
            }
            label("")
            button("Translate to RISC-V") {
                isDefaultButton = true
                useMaxWidth = true
            }
            label("")
            label("RISC-V Assembly")
            textarea {
                prefRowCount = 7
            }
            label("Error Stream")
            textarea {
                prefRowCount = 3
            }
            label("Compilation Time")
            textfield()
        }
    }
}