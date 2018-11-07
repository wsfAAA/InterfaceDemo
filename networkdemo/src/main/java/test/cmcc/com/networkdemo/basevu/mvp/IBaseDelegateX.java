package test.cmcc.com.networkdemo.basevu.mvp;


import test.cmcc.com.networkdemo.basevu.persenter.IBasePresenter;

public interface IBaseDelegateX <P extends IBasePresenter>{

    /**
     * 绑定 Presenter
     * @return
     */
    P bindPresenter();

}
