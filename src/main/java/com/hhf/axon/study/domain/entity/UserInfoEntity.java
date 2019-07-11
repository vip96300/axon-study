package com.hhf.axon.study.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author huang hong fei
 * @date 2019/7/10
 * @description
 **/
@Entity @Table(name="t_user_info")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UserInfoEntity {

    @Id
    private String userId;
    private String userName;
    private String password;

}
