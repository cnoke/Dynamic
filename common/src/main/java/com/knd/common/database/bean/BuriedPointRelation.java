package com.knd.common.database.bean;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class BuriedPointRelation{

    @Embedded
    public BuriedPoint buriedPoint;

    @Relation(parentColumn = "id", entityColumn = "pointId")
    public List<BuriedPointValueRelation> buriedPointValues;

}
