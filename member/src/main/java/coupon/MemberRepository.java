package coupon;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel="members", path="members")
public interface MemberRepository extends PagingAndSortingRepository<Member, Long>{
    Optional<Member> findById(Long Id);
    List<Member> findByMemId(String memId);

}
