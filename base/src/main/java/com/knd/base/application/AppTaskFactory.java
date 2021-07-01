package com.knd.base.application;

import com.effective.android.anchors.task.project.Project;

class AppTaskFactory extends Project.TaskFactory{

    public AppTaskFactory() {
        super(new MyTaskCreator());
    }

}
