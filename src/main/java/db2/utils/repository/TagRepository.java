package db2.utils.repository;

import db2.utils.Model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface TagRepository extends JpaRepository<Tag, BigInteger> {
}
