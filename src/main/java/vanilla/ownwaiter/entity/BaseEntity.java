package vanilla.ownwaiter.entity;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    private String createdDate;

    @LastModifiedDate
    private String updatedDate;

    @PrePersist
    public void onPrePersist(){
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        this.updatedDate = this.createdDate;
    }

    @PreUpdate
    public void onPreUpdate(){
        this.updatedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
