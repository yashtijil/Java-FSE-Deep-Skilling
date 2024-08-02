class Node {
    Task task;
    Node next;

    Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

public class LinkedList {
    private Node head;

    public LinkedList() {
        this.head = null;
    }

    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public Task searchTask(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.getTaskId() == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    public void traverseTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    public void deleteTask(int taskId) {
        if (head == null) {
            return;
        }

        if (head.task.getTaskId() == taskId) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null && current.next.task.getTaskId() != taskId) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    public static void main(String[] args) {
        LinkedList taskList = new LinkedList();

        taskList.addTask(new Task(1, "Design UI", "Pending"));
        taskList.addTask(new Task(2, "Develop Backend", "In Progress"));
        taskList.addTask(new Task(3, "Write Documentation", "Completed"));

        System.out.println("All tasks:");
        taskList.traverseTasks();

        System.out.println("\nSearch for task with ID 2:");
        Task foundTask = taskList.searchTask(2);
        System.out.println(foundTask != null ? foundTask : "Task not found.");

        System.out.println("\nDeleting task with ID 2.");
        taskList.deleteTask(2);

        System.out.println("\nTasks after deletion:");
        taskList.traverseTasks();
    }
}
