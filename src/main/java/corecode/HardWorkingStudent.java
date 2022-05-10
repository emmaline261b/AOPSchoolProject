package corecode;

import java.util.LinkedList;

import static java.lang.System.exit;

class HardWorkingStudent extends Student {

    public HardWorkingStudent() {
        this.name = "Hard-Working Harry";
        this.lifeForce = 30;
        this.yearsOfExperience = 1;
        this.workLifeBalance = 80;
        this.isWorking = true;
        this.taskList = new LinkedList<>();
    }

    @Override
    boolean dealWithTask(Integer task, UserIO userIO) throws InterruptedException {
        userIO.printStuff("Harry: Work is my life!");                       //no work-life balance issues there...
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
    void work(UserIO userIO) throws InterruptedException {
        int sleepTime = 20 * (100 - workLifeBalance);
        userIO.printStuffWithoutEnter("Yuppi! Working, working");
        for (int i = 0; i < 3; i++) {
            userIO.printStuffWithoutEnter(".");
            Thread.sleep(sleepTime / 10);
        }
        userIO.printStuff("");
        userIO.printStuff("");
    }

    @Override
    boolean isDying(UserIO userIO) {
        if (lifeForce < 1) {
            userIO.printStuff(name + " is dead.");
            exit(1);                                                           //If someone's dead the show mustn't go on.
        }
        if (lifeForce < 10) {                                                       //Works till the verge of death before getting rest.
            userIO.printStuff("" + name + " is tired. Time to get rest.");
            isWorking = false;
            return true;
        }
        return false;
    }

    @Override
    void getRest(UserIO userIO) throws InterruptedException {
        int sleepTime = 20 * workLifeBalance;                       //the higher the value of WLB (80 here) the longer you need to rest
        userIO.printStuffWithoutEnter("getting rest");
        for (int i = 0; i < 10; i++) {
            userIO.printStuffWithoutEnter(".");
            Thread.sleep(sleepTime / 10);
        }
        userIO.printStuff("");
        userIO.printStuff("");
        lifeForce += 20;                                            //doesn't rest as well as the lazy one
        isWorking = true;                                           //you can go back to work now
    }

    @Override
    void checkExperience() {
        if (taskList.size() > 4) {
            yearsOfExperience++;
            taskList.clear();
        }
    }
}

