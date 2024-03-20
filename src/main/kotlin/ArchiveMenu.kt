/**
 * Класс для хранения данных.
 * @property titleArchive Название для архива].
 */
data class ArchiveMenu(private val titleArchive: String) {
    private val notesList = mutableListOf<NotesMenu>()

    override fun toString(): String {
        return titleArchive
    }

    /**
     * Добавляет новую заметку в архив.
     */
    fun addedNote() {
        println("Введите название заметки:")
        var titleNote = scanner.nextLine()
        while(titleNote.isNullOrBlank()) {
            println("Название заметки не может быть пустым.")
            titleNote = scanner.nextLine()
        }
        println("Введите текст заметки:")
        var textNote = scanner.nextLine()
        while(textNote.isNullOrBlank()) {
            println("Текст заметки не может быть пустым.")
            textNote = scanner.nextLine()
        }
        notesList.add(NotesMenu(titleNote, textNote))
    }

    /**
     * Возвращает список заметок из текущего архива.
     * @return Список заметок.
     */
    fun showNotes(): List<NotesMenu> {
        return notesList
    }
}

/**
 * Класс представляет меню списка архивов.
 * @property titleMenu Заголовок меню.
 * @property firstMenuText Текст первого элемента меню.
 * @property listMenuItems Список элементов меню.
 */
class ArchiveListMenu
<T>(
    titleMenu: String,
    firstMenuText: String,
    listMenuItems: List<T>
) {
    /**
     * Инициализирует объект и вызывает обработчик меню для отображения списка архивов.
     */
    init {
        menuHandler(titleMenu, firstMenuText, listMenuItems, null, null)
    }
}
