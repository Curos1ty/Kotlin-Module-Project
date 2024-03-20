/**
 * Класс представляет заметку с заголовком и текстом.
 * @property titleNote Заголовок заметки.
 * @property textNote Текст заметки.
 */
data class NotesMenu (val titleNote: String, val textNote: String) {
    override fun toString(): String {
        return titleNote
    }

    /**
     * Выводит содержимое заметки.
     */
    fun printContentNotes(): Unit {
        println("Название заметки: $titleNote")
        println("Содержимое заметки: $textNote\n")
    }
}

/**
 * Класс представляет меню списка заметок.
 * @property titleMenu Заголовок меню.
 * @property firstMenuText Текст первого элемента меню.
 * @property listMenuItems Список элементов меню.
 * @property selectedArchive Выбранный архив, к которому привязан список заметок.
 */
class NotesListMenu
<T>(
    titleMenu: String,
    firstMenuText: String,
    listMenuItems: List<T>,
    selectedArchive: ArchiveMenu
) {
    /**
     * Инициализирует объект и обрабатывает отображение меню.
     */
    init {
        menuHandler(titleMenu, firstMenuText, listMenuItems, selectedArchive, null)
    }
}