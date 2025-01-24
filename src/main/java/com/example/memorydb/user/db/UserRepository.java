package com.example.memorydb.user.db;


import com.example.memorydb.user.model.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // select * from user where score >= [??]
    public List<UserEntity> findAllByScoreGreaterThanEqual(int sc);

    // select * from user where score >= ?? AND score <= ??
    List<UserEntity> findAllByScoreGreaterThanEqualAndScoreLessThanEqual(int min, int max);
}
