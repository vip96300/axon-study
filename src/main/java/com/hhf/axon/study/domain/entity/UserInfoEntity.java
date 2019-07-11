package com.hhf.axon.study.domain.entity;

import lombok.Builder;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author huang hong fei
 * @date 2019/7/10
 * @description
 **/
@Entity @Table(name="t_user_info")
@Data @Builder
public class UserInfoEntity {

    @Id
    private String userId;
    private String userName;
    private String password;

}
