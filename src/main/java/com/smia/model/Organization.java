package com.smia.model;

import java.io.Serializable;

//import javax.persistence.Id;

//import org.springframework.hateoas.RepresentationModel;

import  org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RedisHash("organization")
public class Organization implements Serializable {
    
    //@Id
    String id;
    String name;
    String contactName;
    String contactEmail;
    String contactPhone;

}
