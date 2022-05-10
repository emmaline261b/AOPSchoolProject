package corecode;

import java.util.LinkedList;
import java.util.Random;

class School {

    static final int STANDARD_TASK_DIFFICULTY = 15;
    Random random = new Random();
    UserIO userIO = new UserIO();
    Student lazy = new LazyStudent();
    Student hardWorker = new HardWorkingStudent();
    LinkedList<Integer> classTasks;

    public School() {
        this.classTasks = new LinkedList<>();
    }

    void run() {

        userIO.printStuff(lazy.toString());                                //show what you've got
        userIO.printStuff(hardWorker.toString());

        addRandomTasks(10, classTasks);                //add random-difficulty tasks to list
        //addStandardTasks(10, classTasks);              //add standard tasks (difficulty: 20) to list

        while (!classTasks.isEmpty()) {                             //execute tasks
            try {
                removeTask(assignTask(lazy));
                if (classTasks.isEmpty()) {
                    break;
                }
                removeTask(assignTask(hardWorker));
            } catch (InterruptedException e) {                      //hmmmm.... what an ugly piece of code here...
                userIO.printStuff(e.getMessage());
            }
        }
        userIO.printStuff("*** All tasks are done. ***");                         //show what you've got
        userIO.printStuff(lazy.toString());
        userIO.printStuff(hardWorker.toString());
    }

    private void removeTask(boolean taskDoneWell) {
        if (taskDoneWell) classTasks.removeFirst();
    }

    private boolean assignTask(Student student) throws InterruptedException {
        return student.dealWithTask(classTasks.getFirst(), userIO);
    }

    private void printListOfTasks(LinkedList<Integer> classTasks) {
        userIO.printStuff("\nList of tasks: " + classTasks.toString() + "\n");
    }

    private void addStandardTasks(int numberOfTasks, LinkedList<Integer> classTasks) {
        for (int i = 0; i < numberOfTasks; i++) {
            classTasks.add(STANDARD_TASK_DIFFICULTY);
        }
        printListOfTasks(classTasks);
    }

    private void addRandomTasks(int numberOfTasks, LinkedList<Integer> classTasks) {
        for (int i = 0; i < numberOfTasks; i++) {
            classTasks.add(random.nextInt(STANDARD_TASK_DIFFICULTY * 3));
        }
        printListOfTasks(classTasks);
    }
}

