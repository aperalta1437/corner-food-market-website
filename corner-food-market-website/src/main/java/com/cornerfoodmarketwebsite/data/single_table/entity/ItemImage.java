package com.cornerfoodmarketwebsite.data.single_table.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ITEM_IMAGE")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class ItemImage {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @Column(name = "ITEM_ID")
    private short itemId;
    @NonNull
    @Column(name = "FILE_EXTENSION")
    private String fileExtension;
    @Column(name = "FILE_NAME")
    private String fileName;
    @NonNull
    @Column(name = "SORT_NUMBER")
    private short sortNumber;
    @NonNull
    @ManyToOne(targetEntity = FileRelativePath.class)
    @JoinColumn(name = "FILE_RELATIVE_PATH_ID")
    private FileRelativePath fileRelativePath;

    public short getRelativePathId() {
        return this.fileRelativePath.getId();
    }

    public String getSourceRelativePathName() {
        return this.fileRelativePath.getRelativePath() + this.fileName;
    }

    public String generateNewFileName() {
        return this.id + "." + this.fileExtension;
    }
}