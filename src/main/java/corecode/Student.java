package corecode;

import java.util.Queue;

abstract class Student {

    String name;
    int lifeForce;                                      //0 - 100 where 0 is dead and 100 is super healthy
    int yearsOfExperience;                              //1+
    int workLifeBalance;                                //% of work in WLB
    boolean isWorking;                                  //not working if getting rest...
    Queue<Integer> taskList;                            //all executed tasks (it's used to gain experience)

    Integer getTask(Integer firstTaskInList) {
        return firstTaskInList / yearsOfExperience;     //task is less difficult if a student is more experienced
    }

    abstract boolean dealWithTask(Integer task, UserIO userIO) throws InterruptedException;

    boolean isWorking(UserIO userIO) {
        if (lifeForce < 1) {
            isWorking = false;
            isDying(userIO);
        }
        return isWorking;
    }

    abstract void checkExperience();

    abstract boolean isDying(UserIO userIO);

    abstract void getRest(UserIO userIO) throws InterruptedException;

    abstract void work(UserIO userIO) throws InterruptedException;

    @Override
    public String toString() {
        return "\nStudent " + name + ":" + "\nyearsOfExperience: " + yearsOfExperience + "\nlifeForce: " + lifeForce + "\nworkLifeBalance=" + workLifeBalance;
    }
}
