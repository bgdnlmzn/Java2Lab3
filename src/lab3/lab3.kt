package lab3

//структ
import java.io.File

fun main(args: Array<String>) {
    val path = "C:\\Users\\blyam\\IdeaProjects\\Java 2 Lab 3\\src\\files\\yaml.yaml"

    val file = File(path)
    checkFileAvail(file, path)
    val group = GeneraterShapes.getAllGroups()
    val newOldGroup = checkFormatFile(path, file, group)
    compareGroups(group, newOldGroup)
}

fun checkFormatFile(path: String, file: File, group: Group): Group {
    val text: String
    var newOldGr = Group()
    if (path[path.length - 1] == 't') {
        writeInTxt(file, group)
        text = decodeAndReadTxt(file)
        newOldGr = createGroupTxt(text)
    }
    else if (path[path.length - 1] == 'n') {
        writeInJson(file, group)
        text = file.bufferedReader().readText()
        newOldGr = createGroupJson(text)
    }
    else if (path[path.length - 1] == 'l') {
        writeInYaml(file, group)
        text = file.bufferedReader().readText()
        newOldGr = createGrYaml(text)
    }
    return newOldGr
}
//CHECK FILE AVAILABILITY
fun checkFileAvail(file: File, path: String) {
    if (!file.exists()) {
        error("Файл \"${path}\" не найден в директории")
    }
}

fun writeInTxt(file: File, group: Group) {
    val figures = group.mas
    file.writeText("")//очищаем файл
    for (figure in figures) {
        file.appendText(binStr(figure.toString()))//txt => bin
    }
    println("Запись объектов осуществлена в txt-файл \n")
}

fun writeInJson(file: File, group: Group) {
    file.writeText("{\n\"Figures\":[\n")//open a json structure
    var countOne = 0
    for (figure in group.mas) {//for each figure in group we write the data in json
        countOne ++
//        add new line breaks and necessary symbols
        file.appendText("\t{\n")
        file.appendText("\t\t\"Name\":\"${figure.name}\",\n")
        file.appendText("\t\t\"Coordinates\":[\n")
        var count = 0
        for (coordinate in figure.coordinates) {
            file.appendText("\t\t\t{\n")
            count++
            file.appendText("\t\t\t\"x\":${coordinate[0]},\n")
            file.appendText("\t\t\t\"y\":${coordinate[1]}\n")
            file.appendText("\t\t\t}")
            if (count < figure.coordinates.size) {
                file.appendText(",")
            }
            file.appendText("\n")
        }
        file.appendText("\t\t]")
        if (figure is Ellipse) {
            val elFigure: Ellipse = figure
            file.appendText(",\n")
            file.appendText("\t\t\"Height\":${elFigure.height},\n")
            file.appendText("\t\t\"Weight\":${elFigure.weight},\n")
            file.appendText("\t\t\"Angle\":${elFigure.angle}\n")
        }
        else {
            file.appendText("\n")
        }
        file.appendText("\t}")
        if (countOne < group.mas.size) {
            file.appendText(",")
        }
        file.appendText("\n")
    }
    file.appendText("]\n}")
    println("Запись объектов осуществлена в json-файл \n")
}

fun writeInYaml(file: File, group: Group) {
    file.writeText("Figures:\n")// open yaml struct.
    for (figure in group.mas) {
        file.appendText("- ${figure.name}:\n")
        file.appendText("  Coordinates:\n")
        for (coordinates in figure.coordinates) {
            file.appendText("  - ${coordinates[0]}\n")
            file.appendText("  - ${coordinates[1]}\n")
        }
        if (figure is Ellipse) {//доп парам о эллепсе.
            file.appendText("  Height: ${figure.height}\n")
            file.appendText("  Weight: ${figure.weight}\n")
            file.appendText("  Angle: ${figure.angle}\n")
        }
    }
    println("Запись осуществлена в yaml-файл \n")
}
fun binStr(str: String): String {
    val binStrBuilder = StringBuilder()
    for (char in str) {
        if (char == '\n') {
            binStrBuilder.append(char)
            continue
        }
        val binChar = Integer.toBinaryString(char.code)
        binStrBuilder.append(binChar.padStart(16, '0'))
    }
    return binStrBuilder.toString()
}
//decode and read binarn.-text/bin => txt

fun decodeAndReadTxt(file: File): String {
    val text = StringBuilder()//для хранения текста
    val lines = file.bufferedReader().readLines()//считываем строки
    for (line in lines) {
        val binSymb = line.chunked(16)//разбиваем строки на бин. символы (по 16 бит)
        for (nullOrOne in binSymb) {//перебираем эти символы с проверкой на 16 бит
            val symbInd = if (nullOrOne.split("0").size + nullOrOne.split("1").size - 2 == 16)
                nullOrOne.toInt(2)//преобразуем в число
            else
                error("Файл содержит небинарный код")
            text.append(symbInd.toChar())//преобразуем числ в симв и добав. в стрингбилдер
        }
        text.append('\n')
    }
    println("Считаваем объекты из txt-файла")
    return text.toString()//в виде стр
}
//далее 3 функции для создания групп на основе текстовых данных прочитанных из файлов с разным форматом
//в этих ф. текстовая инф. преобразуется в объекты фигур
fun createGroupTxt(text: String): Group {
    val group = Group()
    for (objStr in text.split("\n\n")) {//перебираем строки по разделению на новые
        var count = 0//счетчик строк
        var name = "?"
        val listX: MutableList<Int> = mutableListOf(); val listY: MutableList<Int> = mutableListOf()
        var height = -1; var weight = -1; var angle = -1
        for (line in objStr.split("\n")) {//перебираем строки
            count++
            val regex = "\\d+(\\.\\d+)?".toRegex()//регулярное выражение для поиска чисел
            val matches = regex.findAll(line)//поиск всех чисел в стр
            when (count) {//считываем данные о фигуре
                1 -> name = line
                2 -> {
                    val coordinates = matches.map { it.value.toInt() }.toList()
                    for (i in coordinates.indices) {
                        if (i % 2 == 0) {
                            listX.add(coordinates[i])//add x in list
                        }
                        else {
                            listY.add(coordinates[i])//add y in list
                        }
                    }
                }
                3 -> height = matches.map { it.value.toInt() }.toList()[0]
                4 -> weight = matches.map { it.value.toInt() }.toList()[0]
                5 -> angle = matches.map { it.value.toInt() }.toList()[0]
                else -> error("Объект \"${name}\" не может существовать")//если символов больше чем ожидалось
            }
        }
        //создаем объект фиг. по считанным данным и add in group
        val figure = createFigure(name, listX, listY, height, weight, angle)
        group.add(figure)
    }
    return group
}

fun createGroupJson(text: String): Group {
    val group = Group()
    var countOne = 0
    for (lines in text.split("\"Name\":\"")) {
        countOne++
        if (countOne == 1) {
            continue
        }
        var countLine = 0
        val name = StringBuilder()
        val listX: MutableList<Int> = mutableListOf(); val listY: MutableList<Int> = mutableListOf()
        var height: Int = -1; var weight: Int = -1; var angle: Int = -1
        for (line in lines.split("\n")) {
            countLine++
            val regex = Regex("\\d+")
            when(countLine) {
                1 -> {
                    for (ch in line) {
                        if (ch == '\"') break
                        name.append(ch)
                    }
                }
                4, 8, 12, 16 -> {
                    if (line.length >= 4) {
                        if (line[3] == 'H') {
                            height = regex.find(line)?.value?.toInt()!!
                        }
                        else if(line[0] != '\t' || line[1] != '}') {
                            listX.add(regex.find(line)?.value?.toInt()!!)
                        }
                    }
                    else if (line[0] != '\t' || line[1] != '}'){
                        if (regex.find(line)?.value?.toInt() != null) {
                            listX.add(regex.find(line)?.value?.toInt()!!)
                        }
                    }
                }
                5, 9, 13, 17 -> {
                    if (line.length >= 4) {
                        if (line[3] == 'W') {
                            weight = regex.find(line)?.value?.toInt()!!
                        }
                        else if (line[0] != '\t' || line[1] != '{') {
                            listY.add(regex.find(line)?.value?.toInt()!!)
                        }
                    }
                    else if (line[0] != '\t' || line[1] != '{'){
                        if (regex.find(line)?.value?.toInt() != null) {
                            listY.add(regex.find(line)?.value?.toInt()!!)
                        }
                    }
                }
                10 -> {
                    if (regex.find(line)?.value?.toInt() != null) {
                        angle = regex.find(line)?.value?.toInt()!!
                    }
                }
            }
        }
        val figure = createFigure(name.toString(), listX, listY, height, weight, angle)
        group.add(figure)
    }
    return group
}

fun createGrYaml(text: String): Group {
    val group = Group()
    var countBlocks = 0
    for (blocks in text.split("\n- ")) {
        countBlocks++
        if (countBlocks == 1) {
            continue
        }
        var countLines = 0
        val name = StringBuilder()
        val listX: MutableList<Int> = mutableListOf(); val listY: MutableList<Int> = mutableListOf()
        var height: Int = -1; var weight: Int = -1; var angle: Int = -1
        val regex = Regex("\\d+")
        for (line in blocks.split("\n")) {
            countLines++
            when(countLines) {
                1 -> {
                    for (ch in line) {
                        if (ch == ':') break
                        name.append(ch)

                    }
                }
                3, 5, 7, 9 -> {
                    if (line[2] == '-') {
                        listX.add(regex.find(line)?.value?.toInt()!!)
                    }
                    else if (line[2] == 'H') {
                        height = regex.find(line)?.value?.toInt()!!
                    }
                    else if (line[2] == 'A') {
                        angle = regex.find(line)?.value?.toInt()!!
                    }
                }
                4, 6, 8, 10 -> {
                    if (line.length == 0) {
                        continue
                    }
                    if (line[2] == '-') {
                        listY.add(regex.find(line)?.value?.toInt()!!)
                    }
                    else {
                        weight = regex.find(line)?.value?.toInt()!!
                    }
                }
            }
        }
        val figure = createFigure(name.toString(), listX, listY, height, weight, angle)
        group.add(figure)
    }
    return group
}

fun createFigure(
    name: String,
    listX: MutableList<Int>,
    listY: MutableList<Int>,
    height: Int,
    weight: Int,
    angle: Int) : Shapes {
    var figure: Shapes = Point("dwa", intArrayOf(1, 2))
    if (height != -1) {
        figure = Ellipse(name, intArrayOf(listX[0], listY[0]), height, weight, angle)
    }
    else if (listX.size == 4) {
        figure = Rectangle(name, intArrayOf(listX[0], listY[0]),
            intArrayOf(listX[1], listY[1]), intArrayOf(listX[2], listY[2]),
            intArrayOf(listX[3], listY[3]))
    }
    else if (listX.size == 3) {
        figure = Triangle(name, intArrayOf(listX[0], listY[0]),
            intArrayOf(listX[1], listY[1]), intArrayOf(listX[2], listY[2]))
    }
    else if (listX.size == 1) {
        figure = Point(name, intArrayOf(listX[0], listY[0]))
    }
    return figure
}

fun compareGroups(oldGr: Group, newGr: Group) {
//    получаем списки фигур
    val oldFigures = oldGr.mas
    val newFigures = newGr.mas
//    println(oldGr.toString())
//    println(newGr.toString())
    for (i in oldFigures.indices) {
//        сравниваем по хэш-кодам(на ссылки памяти)
        if (oldFigures[i].hashCode().equals(newFigures[i].hashCode())) {
//            println("${oldFigures[i].hashCode()} ${newFigures[i].hashCode()}")
            println("Ссылки на память ОДИНАКОВЫЕ")
            println(i+100);
        }
        else {
            println("Ссылки на память РАЗНЫЕ")
            println(i+1000);
        }
//сравниваем по значениям
        if (oldFigures[i].equals(newFigures[i])) {
            println("Значения объектов РАВНЫ ")
            println("\n")
        }
        else {
            println("Значения объектов НЕ РАВНЫ")
            println("\n")
        }
    }
}
