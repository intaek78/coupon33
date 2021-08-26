package coupon;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel="coupons", path="coupons")
public interface CouponRepository extends PagingAndSortingRepository<Coupon, Long>{
    Optional<Coupon> findById(Long Id);
    List<Coupon> findByMemId(String memId);
}
