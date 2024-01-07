package com.practice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "FILE_SYSTEM_DATA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileSystemData {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String type;
        private String filePath;
}
