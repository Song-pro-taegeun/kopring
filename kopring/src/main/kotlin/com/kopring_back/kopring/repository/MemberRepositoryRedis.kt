package com.kopring_back.kopring.repository

import com.kopring_back.kopring.entity.Member
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepositoryRedis : CrudRepository<Member, String>{

}