package com.xyp.mimi.di.module.project;


import dagger.Binds;
import dagger.Module;

import com.xyp.mimi.mvp.contract.project.ProjectContract;
import com.xyp.mimi.mvp.model.project.ProjectModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/07/2020 16:16
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class ProjectModule {

    @Binds
    abstract ProjectContract.Model bindprojectModel(ProjectModel model);
}