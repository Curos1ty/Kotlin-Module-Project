import java.util.Scanner

val scanner = Scanner(System.`in`)
val archiveMenuList = mutableListOf<ArchiveMenu>()


/**
 * Основная функция для работы с меню.
 * @param titleMenu Заголовок меню.
 * @param firstMenuText Текст первого элемента меню.
 * @param listMenuItems Список элементов меню.
 * @param selectedArchive Текущий выбранный архив.
 * @param note Текущая выбранная заметка.
 */
fun <T>menuHandler(
    titleMenu: String,
    firstMenuText: String,
    listMenuItems: List<T>,
    selectedArchive: ArchiveMenu?,
    note: NotesMenu?,
) {
    // Вывод информации о заметке, если она выбрана.
    if (note != null) {
        note.printContentNotes()
        return
    } else {
        // Вывод заголовка и списка элементов меню.
        println(titleMenu)
        println("0 Создать $firstMenuText")

        listMenuItems.forEachIndexed { i, item -> println("${i + 1} $item") }
        println("${listMenuItems.size + 1} Выход")
        println("Введите целое число для управления меню:")
    }

    var checkInput = false

    // Цикл для обработки ввода пользователя.
    while(!checkInput) {
        val userInput: Int? = scanner.nextLine().toIntOrNull()

        if (userInput != null) {
            if (userInput <= listMenuItems.size + 1 && userInput > -1) {
                checkInput = when (userInput) {
                    0 -> {
                        if (note != null) {
                            true
                        } else {
                            menuCreate(titleMenu, firstMenuText, listMenuItems, selectedArchive)
                        }
                    }
                    listMenuItems.size + 1 -> true
                    else -> {
                        val titleMenuArchive = if (
                            titleMenu.split("архива").size > 1 &&
                            titleMenu.split("архива")[1].isNotEmpty()
                        ) {
                            titleMenu.substringAfter("архива").trim(':', '-', ' ')
                        } else {
                            listMenuItems[userInput-1]
                        }
                        selectedMenuItems(
                            "Список заметок архива - ${titleMenuArchive}:",
                            "заметку",
                            listMenuItems,
                            userInput,
                            selectedArchive
                        )
                    }
                }
            } else {
                println("Такого меню нет, введите целое число от 0 до ${listMenuItems.size + 1}")
            }
        } else {
            println("Пожалуйста, введите целое число от 0 до ${listMenuItems.size + 1}")
        }
    }
}

/**
 * Обработка создания нового элемента меню (архива или заметки).
 * @param titleMenu Заголовок меню.
 * @param firstMenuText Текст первого элемента меню.
 * @param listMenuItems Список элементов меню.
 * @param selectedArchive Текущий выбранный архив (если есть).
 * @return Возвращает true после успешного выполнения операции.
 */
fun <T>menuCreate(
    titleMenu: String,
    firstMenuText: String,
    listMenuItems: List<T>,
    selectedArchive: ArchiveMenu?
): Boolean {
    // Если текущий архив выбран, добавляет заметку в архив.
    if (selectedArchive != null) {
        selectedArchive.addedNote()
        NotesListMenu(titleMenu, firstMenuText, listMenuItems, selectedArchive)
    } else {
        // В противном случае создает новый архив.
        createNewArchive()
        ArchiveListMenu(titleMenu, firstMenuText, listMenuItems)
    }
    return true
}

/**
 * Обработка выбора элемента из списка.
 * @param titleMenu Заголовок меню.
 * @param firstMenuText Текст первого элемента меню.
 * @param listMenuItems Список элементов меню.
 * @param userInput Ввод пользователя (номер выбранного элемента).
 * @param selectedArchive Текущий выбранный архив (если есть).
 * @return Возвращает true после успешного выполнения операции.
 */
fun <T>selectedMenuItems(
    titleMenu: String,
    firstMenuText: String,
    listMenuItems: List<T>,
    userInput: Int,
    selectedArchive: ArchiveMenu?
): Boolean {
    // Если выбран какой-то архив, выводит список заметок в этом архиве.
    if (selectedArchive != null) {
        val selNote = listMenuItems[userInput - 1] as NotesMenu
        val selectedNote= NotesMenu(selNote.titleNote, selNote.textNote)
        selectedNote.printContentNotes()
        NotesListMenu(titleMenu, firstMenuText, listMenuItems, selectedArchive)
    } else {
        // В противном случае выводит список заметок выбранного архива.
        val selArchive: ArchiveMenu = listMenuItems[userInput - 1] as ArchiveMenu
        NotesListMenu(
            titleMenu,
            firstMenuText,
            selArchive.showNotes(),
            selArchive
        )
        ArchiveListMenu("Список архивов:", "архив", listMenuItems)
    }
    return true
}

/**
 * Создает новый архив.
 */
fun createNewArchive() {
    println("Введите название для архива")
    var archiveName = scanner.nextLine()

    // Проверка на пустое название архива.
    while(archiveName.isNullOrBlank()) {
        println("Название архива не может быть пустым!")
        archiveName = scanner.nextLine()
    }

    // Добавление нового архива в список.
    archiveMenuList.add(ArchiveMenu(archiveName))
}