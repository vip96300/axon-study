package com.hhf.axon.study.domain.repository;

import com.hhf.axon.study.domain.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author huang hong fei
 */
public interface UserInfoRepository extends JpaRepository<UserInfoEntity,String> {
}
