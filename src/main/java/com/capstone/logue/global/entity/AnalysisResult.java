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
 * 메시지에 대한 구조화된 분석 결과를 저장하는 엔티티입니다.
 */
@Getter
@Entity
@Table(name = "analysis_results")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AnalysisResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @Column(name = "metric", nullable = false, length = 30)
    private String metric;

    @Column(name = "formula", nullable = false, length = 255)
    private String formula;

    @Column(name = "base_date", nullable = false, length = 50)
    private String baseDate;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "dimensions", nullable = false, columnDefinition = "jsonb")
    private JsonNode dimensions;

    @Column(name = "compare_period", nullable = false, length = 255)
    private String comparePeriod;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "exclude_conditions", nullable = false, columnDefinition = "jsonb")
    private JsonNode excludeConditions;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "chart_data", columnDefinition = "jsonb")
    private JsonNode chartData;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "data_warnings", columnDefinition = "jsonb")
    private JsonNode dataWarnings;
}
