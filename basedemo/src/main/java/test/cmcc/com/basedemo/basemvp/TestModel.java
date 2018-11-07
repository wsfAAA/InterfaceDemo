package test.cmcc.com.basedemo.basemvp;


import test.cmcc.com.basedemo.basemvp.base.BaseModel;

/**
 * Created by wsf on 2018/11/6.
 */
public class TestModel extends BaseModel<TestPersenter> {

    protected void testModel(){
        mPresenter.testSucceed();
    }
}
