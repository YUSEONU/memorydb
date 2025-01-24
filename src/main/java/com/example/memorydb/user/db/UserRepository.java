package com.example.memorydb.user.db;


import com.example.memorydb.user.model.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // select * from user where score >= [??]
    public List<UserEntity> findAllByScoreGreaterThanEqual(int sc);

    // select * from user where score >= ?? AND score <= ??
    List<UserEntity> findAllByScoreGreaterThanEqualAndScoreLessThanEqual(int min, int max);

    @Query(
            "select u from user u where u.score >= ?1 AND u.score <= ?2"
    )
    List<UserEntity> score(int min, int max);
}
