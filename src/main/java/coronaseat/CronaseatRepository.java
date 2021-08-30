package coronaseat;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="cronaseats", path="cronaseats")
public interface CronaseatRepository extends PagingAndSortingRepository<Cronaseat, Long>{

     Cronaseat findByseatId(Long SeatId);
}
