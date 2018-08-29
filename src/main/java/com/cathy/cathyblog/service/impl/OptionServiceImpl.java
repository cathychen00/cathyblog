package com.cathy.cathyblog.service.impl;

import com.cathy.cathyblog.common.exceptions.ServiceException;
import com.cathy.cathyblog.domain.Option;
import com.cathy.cathyblog.domain.extend.OptionKey;
import com.cathy.cathyblog.repository.OptionRepository;
import com.cathy.cathyblog.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    OptionRepository optionRepository;

    //    @Autowired
//    RedisTemplate redisTemplate;
    @Override
    public Option getByOptionKey(String key) {
//        Object cachevalue = redisTemplate.opsForValue().get(key);
//        if (null != cachevalue) {
//            return  (Option)cachevalue;
//        }

        Option ret = optionRepository.getByOptionKey(key);
        if (null == ret) {
            return null;
        }

//        redisTemplate.opsForValue().set(key,ret);
        return ret;
    }

    @Override
    public void add(Option option) {
        optionRepository.save(option);
//        redisTemplate.opsForValue().set(option.getOptionKey(),option);
    }

    @Override
    public List<Option> getPreference() throws ServiceException {
        try {
            final Option checkInit = optionRepository.getByOptionKey(OptionKey.ID_C_ADMIN_EMAIL);
            if (null == checkInit) {
                return null;
            }

            //todo preferenceCache
//            Option ret = preferenceCache.getPreference();
//            if (null == ret) {
                List<Option> list = optionRepository.getByOptionCategory(OptionKey.CATEGORY_C_PREFERENCE);
//                preferenceCache.putPreference(ret);
//            }

            return list;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
