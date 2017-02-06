package com.rajendarreddyj.basics.concurrent.forkjointask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CustomRecursiveTask extends RecursiveTask<Integer> {

    private static final long serialVersionUID = 1L;

    private int[] arr;

    private static final int THRESHOLD = 20;

    public CustomRecursiveTask(final int[] arr) {
        this.arr = arr;
    }

    @Override
    protected Integer compute() {

        if (this.arr.length > THRESHOLD) {

            return ForkJoinTask.invokeAll(this.createSubtasks()).stream().mapToInt(ForkJoinTask::join).sum();

        } else {
            return this.processing(this.arr);
        }
    }

    private Collection<CustomRecursiveTask> createSubtasks() {
        List<CustomRecursiveTask> dividedTasks = new ArrayList<>();
        dividedTasks.add(new CustomRecursiveTask(Arrays.copyOfRange(this.arr, 0, this.arr.length / 2)));
        dividedTasks.add(new CustomRecursiveTask(Arrays.copyOfRange(this.arr, this.arr.length / 2, this.arr.length)));
        return dividedTasks;
    }

    private Integer processing(final int[] arr) {
        return Arrays.stream(arr).filter(a -> (a > 10) && (a < 27)).map(a -> a * 10).sum();
    }
}
