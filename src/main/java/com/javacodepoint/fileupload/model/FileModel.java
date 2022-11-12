package com.javacodepoint.fileupload.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
//@NoArgsConstructor
public class FileModel {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileId;
    private String fileName;
    private File file;

    public FileModel(String fileName, File file) {
        this.fileName = fileName;
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FileModel fileModel = (FileModel) o;
        return fileId != null && Objects.equals(fileId, fileModel.fileId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
