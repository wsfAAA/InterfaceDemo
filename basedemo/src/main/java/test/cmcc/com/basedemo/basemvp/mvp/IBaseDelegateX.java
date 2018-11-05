package test.cmcc.com.basedemo.basemvp.mvp;


import test.cmcc.com.basedemo.basemvp.persenter.IBasePresenter;

public interface IBaseDelegateX <P extends IBasePresenter>{


    P bindPresenter();

}
