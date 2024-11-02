import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        while (true) {
            printMenu();
            int command = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (command) {
                case 1:
                    createTask(taskManager);
                    break;
                case 2:
                    createEpic(taskManager);
                    break;
                case 3:
                    createSubtask(taskManager);
                    break;
                case 4:
                    showAllTasks(taskManager);
                    break;
                case 5:
                    showAllEpics(taskManager);
                    break;
                case 6:
                    showAllSubtasks(taskManager);
                    break;
                case 7:
                    updateTask(taskManager);
                    break;
                case 8:
                    deleteTask(taskManager);
                    break;
                case 9:
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Меню трекера задач ===");
        System.out.println("1. Создать задачу");
        System.out.println("2. Создать эпик");
        System.out.println("3. Создать подзадачу");
        System.out.println("4. Показать все задачи");
        System.out.println("5. Показать все эпики");
        System.out.println("6. Показать все подзадачи");
        System.out.println("7. Обновить задачу");
        System.out.println("8. Удалить задачу");
        System.out.println("9. Выйти");
        System.out.print("Выберите действие: ");
    }

    private static void createTask(TaskManager taskManager) {
        System.out.print("Введите название задачи: ");
        String name = scanner.nextLine();

        System.out.print("Введите описание задачи: ");
        String description = scanner.nextLine();

        Task task = new Task(0, name, description, TaskStatus.NEW);
        taskManager.createTask(task);

        System.out.println("Задача создана с ID: " + task.getId());
    }

    private static void createEpic(TaskManager taskManager) {
        System.out.print("Введите название эпика: ");
        String name = scanner.nextLine();

        System.out.print("Введите описание эпика: ");
        String description = scanner.nextLine();

        Epic epic = new Epic(0, name, description);
        taskManager.createEpic(epic);

        System.out.println("Эпик создан с ID: " + epic.getId());
    }

    private static void createSubtask(TaskManager taskManager) {
        System.out.print("Введите ID эпика, к которому относится подзадача: ");
        int epicId = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        if (taskManager.getEpicById(epicId) == null) {
            System.out.println("Эпик с таким ID не найден.");
            return;
        }
        System.out.print("Введите название подзадачи: ");
        String name = scanner.nextLine();

        System.out.print("Введите описание подзадачи: ");
        String description = scanner.nextLine();

        Subtask subtask = new Subtask(0, name, description, TaskStatus.NEW, epicId);
        taskManager.createSubtask(subtask);
        System.out.println("Подзадача создана с ID: " + subtask.getId());
    }

    private static void showAllTasks(TaskManager taskManager) {
        System.out.println("Все задачи: " + taskManager.getAllTasks());
    }

    private static void showAllEpics(TaskManager taskManager) {
        System.out.println("Все эпики: " + taskManager.getAllEpics());
    }

    private static void showAllSubtasks(TaskManager taskManager) {
        System.out.println("Все подзадачи: " + taskManager.getAllSubtasks());
    }

    private static void updateTask(TaskManager taskManager) {
        System.out.print("Введите ID задачи для обновления: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        Task task = taskManager.getTaskById(id);
        if (task == null) {
            System.out.println("Задача с таким ID не найдена.");
            return;
        }

        TaskStatus status = null;
        while (status == null) {
            System.out.print("Введите новый статус (NEW, IN_PROGRESS, DONE): ");
            String statusInput = scanner.nextLine().trim().toUpperCase();

            if (statusInput.equals("NEW")) {
                status = TaskStatus.NEW;
            } else if (statusInput.equals("IN_PROGRESS")) {
                status = TaskStatus.IN_PROGRESS;
            } else if (statusInput.equals("DONE")) {
                status = TaskStatus.DONE;
            } else {
                System.out.println("Некорректный статус. Попробуйте снова.");
            }
        }

        task.setStatus(status);
        taskManager.updateTask(task);
        System.out.println("Задача обновлена.");
    }

    private static void deleteTask(TaskManager taskManager) {
        System.out.print("Введите ID задачи для удаления: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        taskManager.deleteTaskById(id);
        System.out.println("Задача удалена.");
    }
}
