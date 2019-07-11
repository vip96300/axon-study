package com.hhf.axon.study.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * @author huang hong fei
 * @date 2019/7/10
 * @description
 **/
@Data @Builder
public class UpdatePasswordCommand {

    @TargetAggregateIdentifier
    private String userId;

    private String password;

}
