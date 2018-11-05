package test.cmcc.com.basedemo.test.mvpvu;

import test.cmcc.com.basedemo.basemvp.persenter.BasePresenterX;

/**
 * Created by wsf on 2018/11/5.
 * view 和model 沟通的桥梁
 */
public class MvpPersenter extends BasePresenterX<MvpVu,MvpModel> {

    public void testPersenter(){
        baseModel.testModel();
    }
}
