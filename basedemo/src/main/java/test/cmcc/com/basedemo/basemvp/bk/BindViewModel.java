package test.cmcc.com.basedemo.basemvp.bk;


import test.cmcc.com.basedemo.basemvp.base.BaseModel;

/**
 * Created by wsf on 2018/11/6.
 */

public interface BindViewModel<M extends BaseModel> {

    M bindModel();
}
