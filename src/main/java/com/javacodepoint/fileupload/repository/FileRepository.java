package com.javacodepoint.fileupload.repository;

import com.javacodepoint.fileupload.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileModel,Long> {
   // FileModel findByName(String name);
}
