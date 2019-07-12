package com.hhf.axon.study.command.event;

import lombok.Builder;
import lombok.Data;

/**
 * @author huang hong fei
 * @date 2019/7/10
 * @description
 **/
@Data @Builder
public class UpdatePasswordEvent {

    private String userId;
    private String password;
}
