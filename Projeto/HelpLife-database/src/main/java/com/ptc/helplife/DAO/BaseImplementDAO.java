package com.ptc.helplife.DAO;

import java.util.List;
import java.util.Map;

public interface BaseImplementDAO<entity>{
	
	public void create(entity obj);

    public entity readById(long id);

    public List<entity> readByCriteria(Map<String, Object> criteria);

    public void update(entity obj);

    public void delete(entity obj);

}
