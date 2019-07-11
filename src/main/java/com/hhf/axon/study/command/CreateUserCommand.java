package com.hhf.axon.study.command;

import lombok.Builder;
import lombok.Data;

/**
 * @author huang hong fei
 * @date 2019/7/10
 * @description
 **/
@Data @Builder
public class CreateUserCommand{

    private String userName;

    private String password;

}
