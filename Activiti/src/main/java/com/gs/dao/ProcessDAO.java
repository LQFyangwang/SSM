package com.gs.dao;

import com.gs.bean.Process;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessDAO {
    void save(Process process);
    List<Process> listAll();
}
