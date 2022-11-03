package com.g.mds;

/**
 * Repository for domain model class SysRoles.
 *
 * @author Hibernate Tools
 * @see com.g.sys.sec.persistence.SysRoles
 */

import org.springframework.data.repository.PagingAndSortingRepository;

public interface SysRolesRepository extends
        PagingAndSortingRepository<SysRole, Long> {
}
