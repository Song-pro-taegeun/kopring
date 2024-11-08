package com.kopring_back.kopring.repository.redis

import com.kopring_back.kopring.entity.redisEntity.Member
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepositoryRedis : CrudRepository<Member, String>{

}