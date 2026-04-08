package com.capstone.logue.global.entity;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * 사용자가 업로드한 데이터 소스 파일과 스키마 메타데이터를 저장하는 엔티티입니다.
 */
@Getter
@Entity
@Table(name = "data_sources")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DataSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "file_name", nullable = false, length = 255)
    private String fileName;

    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    @Column(name = "storage_key", columnDefinition = "TEXT")
    private String storageKey;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "schema_json", nullable = false, columnDefinition = "jsonb")
    private JsonNode schemaJson;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "date_status", columnDefinition = "jsonb")
    private JsonNode dateStatus;

    @Column(name = "row_count", nullable = false)
    private Integer rowCount;

    @Column(name = "column_count", nullable = false)
    private Integer columnCount;

    @Builder.Default
    @OneToMany(mappedBy = "dataSource", fetch = FetchType.LAZY)
    private List<DataSourceColumn> dataSourceColumns = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "dataSource", fetch = FetchType.LAZY)
    private List<Conversation> conversations = new ArrayList<>();
}
