package hyeseo.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hyeseo.boot.domain.FileUp;

public interface FileRepository extends JpaRepository<FileUp, Long> { }

