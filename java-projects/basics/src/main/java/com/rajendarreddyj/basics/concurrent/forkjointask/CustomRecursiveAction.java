package com.rajendarreddyj.basics.concurrent.forkjointask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

public class CustomRecursiveAction extends RecursiveAction {

    private static final long serialVersionUID = 1L;
    private String workLoad = "";
    private static final int THRESHOLD = 4;

    private static Logger logger = Logger.getAnonymousLogger();

    public CustomRecursiveAction(final String workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected void compute() {

        if (this.workLoad.length() > THRESHOLD) {
            ForkJoinTask.invokeAll(this.createSubtasks());
        } else {
            this.processing(this.workLoad);
        }
    }

    private Collection<CustomRecursiveAction> createSubtasks() {

        List<CustomRecursiveAction> subtasks = new ArrayList<>();

        String partOne = this.workLoad.substring(0, this.workLoad.length() / 2);
        String partTwo = this.workLoad.substring(this.workLoad.length() / 2, this.workLoad.length());

        subtasks.add(new CustomRecursiveAction(partOne));
        subtasks.add(new CustomRecursiveAction(partTwo));

        return subtasks;
    }

    private void processing(final String work) {
        String result = work.toUpperCase();
        logger.info("This result - (" + result + ") - was processed by " + Thread.currentThread().getName());
    }
}
