package site.blmdz.dao;

import java.util.List;

import com.github.pagehelper.Page;

public interface BaseDao<T> {
	int create(T record);
	int delete(Long id);
    T findById(Long id);
    int update(T record);
    List<T> list();
    Page<T> page();
}