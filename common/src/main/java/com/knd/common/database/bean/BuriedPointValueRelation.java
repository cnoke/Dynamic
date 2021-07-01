package com.knd.common.database.bean;

import androidx.room.DatabaseView;
import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

@DatabaseView("SELECT * FROM BuriedPointValue LEFT JOIN BuriedPointParameter " +
        "ON BuriedPointParameter.pointValueId = BuriedPointValue.id")
public class BuriedPointValueRelation{

    @Embedded
    public BuriedPointValue buriedPointValue;

    @Relation(parentColumn = "id", entityColumn = "pointValueId")
    public List<BuriedPointParameter> buriedPointParameters;
}
