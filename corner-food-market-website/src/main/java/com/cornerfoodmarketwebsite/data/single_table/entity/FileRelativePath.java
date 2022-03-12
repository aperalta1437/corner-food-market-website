package com.cornerfoodmarketwebsite.data.single_table.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "FILE_RELATIVE_PATH")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class FileRelativePath {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @NonNull
    @Column(name = "FILE_TYPE_ID")
    private short fileTypeId;
    @NonNull
    @Column(name = "RELATIVE_PATH")
    private String relativePath;
}