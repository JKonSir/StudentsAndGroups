package DAO;

import table.CreateTableModel;

import java.beans.Beans;

/**
 * Created by gijoe on 7/1/2015.
 */
public interface AccessDateDAO{

    void add(Beans bean);
    void delete(Long id);
    void update(Beans bean, Long id);
    CreateTableModel getTableModelFull();
}
