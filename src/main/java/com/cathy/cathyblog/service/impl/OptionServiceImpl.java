package com.cathy.cathyblog.service.impl;

import com.cathy.cathyblog.domain.Option;
import com.cathy.cathyblog.repository.OptionRepository;
import com.cathy.cathyblog.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    OptionRepository optionRepository;
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public Option getByOptionKey(String key) {
        Object cachevalue = redisTemplate.opsForValue().get(key);
        if (null != cachevalue) {
            return  (Option)cachevalue;
        }

        Option ret = optionRepository.getByOptionKey(key);
        if (null == ret) {
            return null;
        }

        redisTemplate.opsForValue().set(key,ret);
        return ret;
    }

    @Override
    public void add(Option option) {
        optionRepository.save(option);
        redisTemplate.opsForValue().set(option.getOptionKey(),option);
    }
}
