package com.hhf.axon.study.domain;

import com.hhf.axon.study.domain.entity.UserInfoEntity;
import com.hhf.axon.study.command.event.CreateUserEvent;
import com.hhf.axon.study.command.event.UpdatePasswordEvent;
import com.hhf.axon.study.domain.repository.UserInfoRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author huang hong fei
 * @date 2019/7/10
 * @description
 **/
@Component
public class UserInfoDomain {

    private static final Logger log= LoggerFactory.getLogger(UserInfoDomain.class);

    @Autowired
    private UserInfoRepository userInfoRepository;

    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    public void createUserHandler(CreateUserEvent event){
        userInfoRepository.save(UserInfoEntity.builder().userId(event.getUserId()).userName(event.getUserName()).password(event.getPassword()).build());
    }

    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    public void updatePasswordHandler(UpdatePasswordEvent event){
        UserInfoEntity userInfoEntity=userInfoRepository.findById(event.getUserId()).orElse(null);
        if(userInfoEntity==null){
            log.error("update password error,user info is empty,userId:{}",event.getUserId());
            return;
        }
        userInfoEntity.setPassword(event.getPassword());
        userInfoRepository.save(userInfoEntity);
    }
}
