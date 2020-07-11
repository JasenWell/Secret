package com.xyp.mimi.di.module;

import dagger.Binds;
import dagger.Module;

import com.xyp.mimi.mvp.contract.ProblemContract;
import com.xyp.mimi.mvp.model.ProblemModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/20/2020 18:41
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class ProblemModule {

    @Binds
    abstract ProblemContract.Model bindProblemModel(ProblemModel model);
}