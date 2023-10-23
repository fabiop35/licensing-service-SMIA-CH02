package com.smia.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.data.redis.core.RedisTemplate;

import lombok.extern.slf4j.Slf4j;

import com.smia.model.Organization;

@Slf4j
@Repository
public class OrganizationRedisRepository {

    public static final String HASH_KEY = "organization";

    @Autowired
    private RedisTemplate template;

    public Organization save(Organization organization) {
        log.info("### called save() to  Redis DB.id: " + organization.getId());
        template.opsForHash().put(HASH_KEY, organization.getId(), organization);
        log.info("# < saved.name: {} > #", organization.getName());

        return organization;
    }

    public List<Organization> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public Organization findById(String id) {
        log.info("### REDIS.findById({}) ###", id);
        Organization org = (Organization) template.opsForHash().get(HASH_KEY, id);
        return org;
    }

    public Organization findByName(String name) {
        log.info("### REDIS.findByName: {} ###", name);
        Organization org = (Organization) template.opsForHash().get(HASH_KEY, name);
        return org;

    }

}
