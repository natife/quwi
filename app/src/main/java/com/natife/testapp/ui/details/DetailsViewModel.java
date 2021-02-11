package com.natife.testapp.ui.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.natife.testapp.Router;
import com.natife.testapp.base.BaseViewModel;
import com.natife.testapp.listeners.ProjectChangeListener;
import com.natife.testapp.model.Project;
import com.natife.testapp.ui.EditNameDialogDirections;
import com.natife.testapp.usecase.GetProjectUseCase;
import com.natife.testapp.usecase.UpdateProjectNameUseCase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

public class DetailsViewModel extends BaseViewModel {

    private final GetProjectUseCase getProjectUseCase;
    private final UpdateProjectNameUseCase updateProjectNameUseCase;
    private final Router router;

    private final MutableLiveData<Project> _projectLiveData = new MutableLiveData<>();
    public final LiveData<Project> projectLiveData = _projectLiveData;

    @Inject
    public DetailsViewModel(
            UpdateProjectNameUseCase updateProjectNameUseCase,
            GetProjectUseCase getProjectUseCase,
            DetailsFragmentArgs args,
            ProjectChangeListener projectChangeListener,
            Router router
    ) {
        this.getProjectUseCase = getProjectUseCase;
        this.updateProjectNameUseCase = updateProjectNameUseCase;
        this.router = router;

        compositeDisposable.add(
                getProjectUseCase.execute(args.getId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> setLoading(true))
                        .doFinally(() -> setLoading(false))
                        .subscribe(_projectLiveData::setValue, this::handleError)
        );

        compositeDisposable.add(
                projectChangeListener.observable
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(updateProject -> {
                            Project project = _projectLiveData.getValue();
                            if (project != null && updateProject.id == project.id) {
                                _projectLiveData.setValue(updateProject);
                            }
                        })
        );
    }

    public void updateName(String name) {
        Project project = _projectLiveData.getValue();
        if (project != null) {
            compositeDisposable.add(
                    updateProjectNameUseCase.execute(project.id, name)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(router::navigateUp, this::handleError)
            );
        }
    }

    public void editName() {
        Project project = _projectLiveData.getValue();
        if (project != null) {
            router.navigate(EditNameDialogDirections.actionEditName(project.name));
        }
    }

    public void back() {
        router.navigateUp();
    }
}
