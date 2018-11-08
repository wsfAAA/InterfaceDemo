package test.cmcc.com.basedemo.basemvp;


import test.cmcc.com.basedemo.basemvp.base.BasePresenter;

/**
 * Created by wsf on 2018/11/6.
 */

public class TestPersenter extends BasePresenter<TestActivity,TestModel> {


    protected void testPersenter() {
        mBaseModel.testModel();
    }

    protected void testSucceed() {
        mBaseView.testview();
    }
}
