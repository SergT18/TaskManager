import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TasksList {

    private static TasksList instance;
    private static final TreeMap<Integer, Task> tasks = new TreeMap<>();

    public static TasksList getInstance() {
        if (instance == null) {
            instance = new TasksList();
        }
        return instance;
    }

    public TreeMap<Integer, Task> getTasks() {
        return tasks;
    }

    public static void addAllTaskToList(TreeMap<Integer, Task> listTasks) {
        tasks.putAll(listTasks);
    }

    public void addTaskToList(String text) {
        Task t = Task.createTask(text);
        int counter = (tasks.isEmpty()) ? 1 : (int) tasks.lastKey() + 1;
        tasks.put(counter, t);
        System.out.println("<<< Задача была добавлена в список >>>\n");
    }

    public void showListTasks(TaskStatus tStatus) {
        if (tasks.isEmpty()) {
            System.out.println("<<< Список задач пуст >>>\n");
            return;
        }

        TreeMap<Integer, Task> filteredMap = tasks.entrySet()
                .stream()
                .filter(e -> e.getValue().getStatus() == tStatus)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        TreeMap::new
                ));

        filteredMap.forEach((key, value) -> System.out.println(key + ". " + value.toString()));
    }


    public void deleteTaskFromList(int num) {
        if (notContainsTaskFromList(num)) {
            return;
        }
        tasks.remove(num);
        System.out.println("<<< Задача " + num + " была удалена из списка задач >>>");
    }

    public void markTaskAsCompletedByNum(int num) {
        if (notContainsTaskFromList(num)) {
            return;
        }
        tasks.get(num).setStatus(TaskStatus.DONE);
        System.out.println("<<< Задача под номером " + num + " помечена как выполненная >>>\n");
    }

    private boolean notContainsTaskFromList(int num) {
        if (!tasks.containsKey(num)) {
            String t = (num == -1) ? "'неизвестно'" : Integer.toString(num);
            System.out.println("<<< Задачи под номером " + t + " нет в списке задач >>>\n");
            return true;
        }
        return false;
    }

}
