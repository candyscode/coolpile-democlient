import com.beust.klaxon.Klaxon
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import model.CompileResult
import tornadofx.*
import util.runOnHost
import java.util.*

class CompileView : View() {
    private val model = ViewModel()
    private val codeText = model.bind { SimpleStringProperty() }
    private val compilationTimeText = model.bind { SimpleStringProperty() }
    private val assemblyText = model.bind { SimpleStringProperty() }
    private val errorStreamText = model.bind { SimpleStringProperty() }

    override val root = form {
        fieldset(labelPosition = Orientation.VERTICAL) {
            label("C Code")
            textarea(codeText) {
                prefWidth = 800.0
                prefRowCount = 7
            }
            label("")
            button("Translate to RISC-V") {
                isDefaultButton = true
                useMaxWidth = true
                action {
                    val sourceCode = codeText.value
                    if (sourceCode.isEmpty()) {
                        println("Source code field empty.")
                        return@action
                    }

                    runAsyncWithProgress {
                        isDisable = true
                        val compileResult = sendCompileRequest(sourceCode)!!
                        println(compileResult)

                        with(compileResult) {
                            compilationTimeText.set(compilationTime)
                            assemblyText.set(assembly.decodeFromBase64())
                            errorStreamText.set(errorStream.decodeFromBase64())
                        }
                        isDisable = false
                    }
                }
            }
            label("")
            label("RISC-V Assembly")
            textarea(assemblyText) {
                prefRowCount = 7
                isEditable = false
            }
            label("Error Stream")
            textarea(errorStreamText) {
                prefRowCount = 3
                isEditable = false
            }
            label("Compilation Time")
            textfield(compilationTimeText) {
                isEditable = false
            }
        }
    }

    private fun String.decodeFromBase64() = String(Base64.getDecoder().decode(this))
    private fun String.encodeWithBase64() = String(Base64.getEncoder().encode(this.toByteArray()))

    private fun sendCompileRequest(sourceCode: String): CompileResult? {
        val encodedSourceCode = sourceCode.encodeWithBase64()
        val requestJsonString = "{\"sourceCode\": \"$encodedSourceCode\"}"
        val resultJsonString = runOnHost(arrayOf("./src/main/kotlin/util/getRequest.sh", requestJsonString))

        return Klaxon().parse(resultJsonString)
    }
}