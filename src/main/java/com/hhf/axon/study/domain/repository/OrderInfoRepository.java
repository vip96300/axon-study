package com.hhf.axon.study.domain.repository;

import com.hhf.axon.study.domain.entity.OrderInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
public interface OrderInfoRepository extends JpaRepository<OrderInfoEntity,String> {
}
