package de.szut.ita13.app.schulapp.dao;

import de.szut.ita13.app.schulapp.entity.Table;

/**
 * Created by Chris on 28.04.2015.
 */
public interface DataSource {

    public void open();

    public void close();

}
