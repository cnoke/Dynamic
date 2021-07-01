package com.knd.base.application;

import com.effective.android.anchors.task.Task;
import com.effective.android.anchors.task.TaskCreator;

import org.jetbrains.annotations.NotNull;

class MyTaskCreator implements TaskCreator {

    @NotNull
    @Override
    public Task createTask(@NotNull String taskName) {
        return BaseApplication.taskMap.get(taskName);
    }

}
