package coupon;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel="cancellations", path="cancellations")
public interface CancellationRepository extends PagingAndSortingRepository<Cancellation, Long>{
    List<Cancellation> findByMemId(String memId);
    List<Cancellation> findByMemNo(String memNo);

}
