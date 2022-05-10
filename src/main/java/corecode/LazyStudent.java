package corecode;

import java.util.LinkedList;

import static java.lang.System.exit;

class LazyStudent extends Student {

    public LazyStudent() {
        this.name = "Lazy Louie";
        this.lifeForce = 50;
        this.yearsOfExperience = 1;
        this.workLifeBalance = 30;
        this.isWorking = true;
        this.taskList = new LinkedList<>();
    }

    @Override
    boolean dealWithTask(Integer task, UserIO userIO) throws InterruptedException {
        userIO.printStuff("Louie: Is it weekend yet?");                //Yes, we can!
        int taskDifficulty = getTask(task);
        if (isDying(userIO)) {
            getRest(userIO);
            return false;                                               //returns false because the task was not executed
        }
        if (isWorking(userIO)) {
            work(userIO);
            lifeForce -= taskDifficulty;                                //the more difficult the task, the quicker it drains the life forces
            taskList.add(taskDifficulty);                               //adds executed tasks to gain experience
            checkExperience();                                          //checks if it's time to upgrade experience
            return true;
        }
        return false;
    }

    @Override
    boolean isDying(UserIO userIO) {
        if (lifeForce < 1) {
            userIO.printStuff(name + " is dead.");
            exit(1);                                     //If someone's dead the show mustn't go on.
        }
        if (lifeForce < 20) {
            userIO.printStuff(name + " is tired. Time to get rest.");
            isWorking = false;
            return true;
        }
        return false;
    }

    @Override
    void getRest(UserIO userIO) throws InterruptedException {
        int sleepTime = 30 * workLifeBalance;                           //the higher the value of WLB (only 30 here) the longer you need to rest
        userIO.printStuffWithoutEnter("getting rest");
        for (int i = 0; i < 10; i++) {
            userIO.printStuffWithoutEnter(".");
            Thread.sleep(sleepTime / 10);
        }
        userIO.printStuff("");
        userIO.printStuff("");
        lifeForce += 30;                                                //if you're rested your life forces increase
        isWorking = true;                                               //you can go back to work now
    }

    @Override
    void work(UserIO userIO) throws InterruptedException {
        int sleepTime = 20 * (100 - workLifeBalance);                           //time required to get the task done
        userIO.printStuffWithoutEnter("yeah, yeah, I'm working...");
        for (int i = 0; i < 5; i++) {
            userIO.printStuffWithoutEnter(".");
            Thread.sleep(sleepTime / 10);
        }
        userIO.printStuff("");
        userIO.printStuff("");
    }

    @Override
    void checkExperience() {
        if (taskList.size() > 6) {
            yearsOfExperience++;
            taskList.clear();
        }
    }
}
