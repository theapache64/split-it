import java.io.File
import java.util.*

fun main(args: Array<String>) {

    require(args.isNotEmpty()) { "File path should be passed" }
    val filePath = args[0]
    val file = File(filePath)
    require(file.exists()) { "Passed file not exists : $filePath" }

    println("Reading file, please wait...")
    val lines = file.readLines()
    val lineCount = lines.size
    println("Line count : $lineCount")
    println("How many files you want to split into ?")
    val scanner = Scanner(System.`in`)
    val fileCount = scanner.nextInt()
    if (fileCount > 1) {
        println("File count : $fileCount")
        val linesPerFile = lineCount / fileCount
        println("Lines per file : $linesPerFile")
        val fileParts = lines.chunked(linesPerFile)
        for (filePart in fileParts.withIndex()) {
            val newFile = File("${file.absoluteFile.parent}/${file.nameWithoutExtension}_chunk_${filePart.index + 1}.csv")
            require(newFile.createNewFile()) { "Failed to create ${newFile.absolutePath}" }
            println("------------------------")
            println("Created ${newFile.name}")
            println("Adding content to ${newFile.name}...")
            for (line in filePart.value) {
                newFile.appendText("$line\n")
            }
            println("Added content to ${newFile.name}")
        }
        println("Done!!")
    } else {
        println("File count should be greater than 1")
    }

}