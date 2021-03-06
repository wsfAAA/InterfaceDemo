package test.cmcc.com.networkdemo.basevu.persenter;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import test.cmcc.com.networkdemo.basevu.model.BaseModel;
import test.cmcc.com.networkdemo.basevu.vu.Vu;


public  class  BasePresenterX<V extends Vu,M extends BaseModel> implements IBasePresenter {

    protected final String TAG=getClass().getSimpleName();

    protected V baseView;

    protected M baseModel;

    protected  M bindModel(){
        BaseModel baseModel = null;
        try {
            Class c = this.getClass();
            Type t = c.getGenericSuperclass();
            if (t instanceof ParameterizedType) {
                Type[] p = ((ParameterizedType) t).getActualTypeArguments();
                Class<BaseModel> vClass = (Class<BaseModel>) p[1];
                baseModel = vClass.newInstance();
                baseModel.setmPresenter(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (M)baseModel;
    }

    public BasePresenterX(){};

    public BasePresenterX(V v){
        attachView(v);
    }

    public void attachView(V vu) {
        this.baseView=vu;
        baseModel=bindModel();
    }

    public void detachView() {
        baseView=null;
    }

    public void onDestroy(){
        if(baseModel!=null){
            baseModel.onDestroy();
            baseModel=null;
        }
        detachView();
    }

    public V getVu() {
        return baseView;
    }
}
