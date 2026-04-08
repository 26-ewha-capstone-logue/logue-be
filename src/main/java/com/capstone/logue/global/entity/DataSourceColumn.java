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
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * 업로드된 데이터 소스의 컬럼별 메타데이터와 시맨틱 분류 결과를 캐싱하는 엔티티입니다.
 */
@Getter
@Entity
@Table(name = "data_source_columns")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DataSourceColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "data_source_id", nullable = false)
    private DataSource dataSource;

    @Column(name = "column_name", nullable = false, length = 255)
    private String columnName;

    @Column(name = "data_type", nullable = false, length = 255)
    private String dataType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "semantic_role_option_id", nullable = false)
    private SemanticRoleOption semanticRoleOption;

    @Column(name = "null_ratio", nullable = false)
    private Double nullRatio;

    @Column(name = "unique_count", nullable = false)
    private Integer uniqueCount;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "sample_values", columnDefinition = "jsonb")
    private JsonNode sampleValues;

    @Column(name = "is_confirmed", nullable = false)
    private boolean confirmed;
}
