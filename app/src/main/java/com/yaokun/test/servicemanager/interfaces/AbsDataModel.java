package com.yaokun.test.servicemanager.interfaces;

import java.util.Collection;
import java.util.List;

public interface AbsDataModel {

    int getColumnCount();

    int getCellColor(int pos);

    int getTextColor(int pos);

    List<String> getDatalist();


}
