package com.smia.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smia.model.Organization;
import com.smia.repository.OrganizationRedisRepository;
import com.smia.utils.UserContext;

@Component
public class OrganizationRestTemplateClient {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OrganizationRedisRepository redisRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrganizationRestTemplateClient.class);

    public Organization getOrganization(String organizationId) {

        logger.debug("### In Licensing Service.getOrganization: {}", UserContext.getCorrelationId());
        Organization organization = checkRedisCache(organizationId);

        if (organization != null) {
            logger.debug("#### I have successfully retrieved an organization {} from the redis cache: {}", organizationId, organization);
            return organization;
        }

        logger.info("### Redis: Unable to locate organization from the redis cache: {}.", organizationId);

        //organizationId = "4d847d4e-517d-4b8c-a6e8-64675272f854";
        logger.info("~~~> calling the Organization service: http://localhost:8072/organization-service/v1/organization/{}", organizationId);

        ResponseEntity<Organization> restExchange
                = restTemplate.exchange(
                        "http://localhost:8072/organization-service/v1/organization/{organizationId}",
                        HttpMethod.GET,
                        null, Organization.class, organizationId);
        logger.info("###~~~> Saving to Redis <~~~");
        /* Save the record obtained from the service to the Redis cache*/
        organization = restExchange.getBody();
        if (organization != null) {
            cacheOrganizationObject(organization);
        }

        return restExchange.getBody();
    }

    private Organization checkRedisCache(String organizationId) {
        logger.info("~~~> checkRedisCache.findById() <~~~");
        try {
            Organization org = redisRepository.findById(organizationId);

            if (org != null) {
                return org;
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("Error encountered while trying to retrieve organization {} check Redis Cache.  Exception {}", organizationId, ex);
            return null;
        }
    }

    private void cacheOrganizationObject(Organization organization) {
        try {
            redisRepository.save(organization);
        } catch (Exception ex) {
            logger.error("Unable to cache organization {} in Redis. Exception {}", organization.getId(), ex);
        }
    }
}
