package com.cornerfoodmarketwebsite.data.single_table.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "BANNER_IMAGE")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class BannerImage implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @Column(name = "DISCOUNT_ID")
    private Integer discountId;
    @NonNull
    @Column(name = "FILE_EXTENSION")
    private String fileExtension;
    @Column(name = "FILE_NAME")
    private String fileName;
    @NonNull
    @Column(name = "SORT_NUMBER")
    private int sortNumber;
    @NonNull
    @Column(name = "HAS_TEXT_OVERLAY")
    private boolean hasTextOverlay;
    @NonNull
    @Column(name = "TEXT_OVERLAY_1")
    private String textOverlay1;
    @NonNull
    @Column(name = "TEXT_OVERLAY_1_CSS_LEFT_VALUE")
    private String textOverlay1CssLeftValue;
    @NonNull
    @Column(name = "TEXT_OVERLAY_1_CSS_TOP_VALUE")
    private String textOverlay1CssTopValue;
    @Column(name = "TEXT_OVERLAY_2")
    private String textOverlay2;
    @NonNull
    @Column(name = "TEXT_OVERLAY_2_CSS_LEFT_VALUE")
    private String textOverlay2CssLeftValue;
    @NonNull
    @Column(name = "TEXT_OVERLAY_2_CSS_TOP_VALUE")
    private String textOverlay2CssTopValue;
    @NonNull
    @Column(name = "IS_DISABLED")
    private boolean isDisabled;
    @NonNull
    @Column(name = "IS_DELETED")
    private boolean isDeleted;
    @NonNull
    @Column(name = "CREATED_BY_ADMINISTRATOR_ID")
    private short createdByAdministratorId;
    @Column(name = "MODIFIED_BY_ADMINISTRATOR_ID")
    private Short modifiedByAdministratorId;
    @Column(name = "CREATED_AT")
    private Timestamp createdAt;
    @Column(name = "MODIFIED_AT")
    private Timestamp modifiedAt;
    @Column(name = "DELETED_AT")
    private Timestamp deletedAt;

    @NonNull
    @ManyToOne(targetEntity = FileRelativePath.class)
    @JoinColumn(name = "FILE_RELATIVE_PATH_ID")
    private FileRelativePath fileRelativePath;

    public String getSourceRelativePathName() {
        return this.fileRelativePath.getRelativePath() + this.fileName;
    }

    public String generateNewFileName() {
        return this.id + "." + this.fileExtension;
    }
}
