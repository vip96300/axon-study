package com.hhf.axon.study.domain.repository;

import com.hhf.axon.study.domain.entity.ProductInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author huang hong fei
 * @date 2019/7/11
 * @description
 **/
public interface ProductInfoRepository extends JpaRepository<ProductInfoEntity,String> {
}
