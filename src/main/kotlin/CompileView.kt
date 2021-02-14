import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import tornadofx.*

class CompileView : View() {
    private val model = ViewModel()
    private val cCode = model.bind { SimpleStringProperty() }

    override val root = form {
        fieldset(labelPosition = Orientation.VERTICAL) {
            label("C Code")
            textarea(cCode) {
                prefWidth = 800.0
                prefRowCount = 7
            }
            label("")
            button("Translate to RISC-V") {
                isDefaultButton = true
                useMaxWidth = true
                action {
                    val sourceCode = cCode.value
                    if (sourceCode.isNotEmpty()) sendCompileRequest(cCode.value)
                }
            }
            label("")
            label("RISC-V Assembly")
            textarea {
                prefRowCount = 7
                isEditable = false
            }
            label("Error Stream")
            textarea {
                prefRowCount = 3
                isEditable = false
            }
            label("Compilation Time")
            textfield("5700 ms") {
                isEditable = false
            }
        }
    }

    private fun sendCompileRequest(sourceCode: String) {

    }
}