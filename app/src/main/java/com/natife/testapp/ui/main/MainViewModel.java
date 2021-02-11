package com.natife.testapp.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.natife.testapp.Router;
import com.natife.testapp.base.BaseViewModel;
import com.natife.testapp.listeners.ProjectChangeListener;
import com.natife.testapp.model.Project;
import com.natife.testapp.ui.details.DetailsFragmentDirections;
import com.natife.testapp.usecase.GetProjectsUseCase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class MainViewModel extends BaseViewModel {

    private final GetProjectsUseCase useCase;
    private final Router router;

    private final MutableLiveData<List<Project>> _projectsLiveData = new MutableLiveData<>();
    public final LiveData<List<Project>> projectsLiveData = _projectsLiveData;

    @Inject
    public MainViewModel(GetProjectsUseCase useCase, Router router, ProjectChangeListener projectChangeListener) {
        this.useCase = useCase;
        this.router = router;
        loadProjects();

        compositeDisposable.add(
                projectChangeListener.observable
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(updatedProject -> {
                            List<Project> list = _projectsLiveData.getValue();
                            if (list != null) {
                                _projectsLiveData.setValue(
                                        list.stream()
                                        .map(project -> {
                                            if (updatedProject.id == project.id) {
                                                return updatedProject;
                                            } else  {
                                                return project;
                                            }
                                        }).collect(Collectors.toList())
                                );
                            }
                        })
        );
    }

    private void loadProjects() {
        compositeDisposable.add(
                useCase.execute()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> setLoading(true))
                        .doFinally(() -> setLoading(false))
                        .subscribe(_projectsLiveData::setValue, this::handleError)
        );
    }

    public void navigateToDetails(int id) {
        router.navigate(DetailsFragmentDirections.actionDetails(id));
    }
}
