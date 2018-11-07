package test.cmcc.com.basedemo.basemvp.bk;


import android.support.v7.app.AppCompatActivity;

import test.cmcc.com.basedemo.basemvp.base.BaseModel;

/**
 * Created by wsf on 2018/11/6.
 */

public interface BindViewModel<V extends AppCompatActivity, M extends BaseModel> {
    V bindView();

    M bindModel();
}
