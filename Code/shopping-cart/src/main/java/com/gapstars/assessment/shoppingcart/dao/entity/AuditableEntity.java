

package com.gapstars.assessment.shoppingcart.dao.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/** Entity super class for auditable fields */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity extends GeneratedIdEntity {

    /** The property related to created user  */
    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    /** The property related to created date time  */
    @Column(name = "created_date_time")
    @CreatedDate
    private LocalDateTime createdDateTime;

    /** The property related to modified user  */
    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

    /** The property related to the modified date time  */
    @Column(name = "modified_date_time")
    @LastModifiedDate
    private LocalDateTime modifiedDateTime;

}
