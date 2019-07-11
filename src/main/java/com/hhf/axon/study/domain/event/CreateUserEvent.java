package com.hhf.axon.study.domain.event;

import lombok.Builder;
import lombok.Data;

/**
 * @author huang hong fei
 * @date 2019/7/10
 * @description
 **/
@Data @Builder
public class CreateUserEvent {

    private String userId;
    private String userName;
    private String password;
}
