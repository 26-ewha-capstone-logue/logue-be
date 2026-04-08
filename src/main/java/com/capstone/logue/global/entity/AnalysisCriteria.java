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
import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * 사용자가 확인한 분석 기준 확정본을 저장하는 엔티티입니다.
 */
@Getter
@Entity
@Table(name = "analysis_criteria")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AnalysisCriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "metric_id", nullable = false)
    private MetricMaster metric;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private Message message;

    @Column(name = "formula_numerator", nullable = false, length = 50)
    private String formulaNumerator;

    @Column(name = "formula_denominator", nullable = false, length = 50)
    private String formulaDenominator;

    @Column(name = "base_date_column", nullable = false, length = 50)
    private String baseDateColumn;

    @Column(name = "compare_period", nullable = false, length = 255)
    private String comparePeriod;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "dimensions", nullable = false, columnDefinition = "jsonb")
    private JsonNode dimensions;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "exclude_conditions", nullable = false, columnDefinition = "jsonb")
    private JsonNode excludeConditions;

    @Column(name = "is_confirmed", nullable = false)
    private boolean confirmed;

    @Column(name = "confirmed_at")
    private OffsetDateTime confirmedAt;
}
