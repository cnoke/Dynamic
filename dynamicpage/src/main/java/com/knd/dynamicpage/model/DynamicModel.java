package com.knd.dynamicpage.model;

import com.knd.dynamicannotations.Model;
import com.knd.dynamicpage.inter.DynamicContract;

@Model
public class DynamicModel extends DynamicBaseModel<DynamicContract.IPresenter>
        implements DynamicContract.IModel{


}
