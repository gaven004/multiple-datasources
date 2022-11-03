package com.g.mds;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRolesService {
    @Autowired
    protected SysRolesRepository repository;

    @DataSource(DataSourceKey.MASTER)
    public SysRole get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @DataSource(DataSourceKey.SLAVE)
    public Iterable<SysRole> findAll() {
        return repository.findAll();
    }

}
