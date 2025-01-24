Features Added:
Checkbox: Each task has a checkbox to mark it as completed.
Dynamic Loading: Tasks are initialized from a simulated PDF source (initialTasks).
State Management: Tracks the isCompleted status for each task using Task data class.
How It Works:
PDF Task Initialization: Replace the tasksFromPdf list with dynamically parsed tasks from a PDF. Use a library like Apache PDFBox in Android for this.
