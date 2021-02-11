package com.natife.testapp.listeners;

import com.natife.testapp.model.Project;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProjectChangeListener {

    private final BehaviorSubject<Project> subject = BehaviorSubject.create();
    public final Observable<Project> observable = subject;

    @Inject
    public ProjectChangeListener() {
    }

    public void notifyChanged(Project project) {
        subject.onNext(project);
    }
}
