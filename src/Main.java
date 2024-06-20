import java.io.IOException;
import java.util.Scanner;

public class Main {

    static Scanner console = new Scanner(System.in);
    static TasksList tasksList = TasksList.getInstance();
    static Serializer serializer = new Serializer();
    static final String fileName = ".\\files\\text.json";
    static final FileHandler fileHandler = new FileHandler(fileName);

    public static void main(String[] args) throws IOException {

        boolean cycleIndicator = true;

        while (cycleIndicator) {
            printMenu();

            switch (inputHandler(console.nextLine())) {
                case 1:
                    System.out.println("\nСписок активных задач:\n");
                    tasksList.showListTasks(TaskStatus.ACTIVE);
                    break;
                case 2:
                    System.out.println("\nУкажите текст задачи:\n");
                    tasksList.addTaskToList(console.nextLine());
                    break;
                case 3:
                    System.out.println("\nВведите номер задачи, которую следует пометить как выполненную: \n");
                    tasksList.markTaskAsCompletedByNum(inputHandler(console.nextLine()));
                    break;
                case 4:
                    System.out.println("\nВведите номер задачи, которую следуюет удалить из списка задач: \n");
                    tasksList.deleteTaskFromList(inputHandler(console.nextLine()));
                    break;
                case 5:
                    System.out.println("\nСписок выполненных задач:\n");
                    tasksList.showListTasks(TaskStatus.DONE);
                    break;
                case 6:
                    String ser = serializer.serialize(tasksList.getTasks());
                    fileHandler.writeDataToFile(ser);
                    System.out.println("<<< Данные были экспортированы в файл >>>");
                    break;
                case 7:
                    String deser = fileHandler.getFileData();
                    TasksList.addAllTaskToList(serializer.deserialize(deser));
                    System.out.println("<<< Данные были импортированы из файла >>>");
                    break;
                case 8:
                    System.out.println("Выход из программы");
                    cycleIndicator = false;
                    break;
                default:
                    System.out.println("\nПожалуйстай укажите правильный пункт меню!\n");
            }

        }
    }


    static void printMenu() {
        System.out.println("\n------------------------------");
        System.out.println("Выберите пункт меню");
        System.out.println("------------------------------");
        System.out.println("1. Просмотреть список активных задач");
        System.out.println("2. Создать задачу");
        System.out.println("3. Пометить задачу как выполненную");
        System.out.println("4. Удалить задачу");
        System.out.println("5. Просмотреть список выполненных задач");
        System.out.println("6. Экспорт задач в файл");
        System.out.println("7. Импорт задач из файла");
        System.out.println("8. Выход");
        System.out.println("------------------------------\n");
    }

    static int inputHandler(String inputString) {
        boolean isInt = inputString.matches("[-+]?\\d+");
        if (!isInt) {
            System.out.println("Вы ввели не число!");
            return -1;
        }
        return Integer.parseInt(inputString);
    }

}