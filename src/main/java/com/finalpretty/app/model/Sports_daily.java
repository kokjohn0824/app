package com.finalpretty.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "sports_daily") // 運動內容
public class Sports_daily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sports_daily_id")
    private Integer sports_daily_id;

    @Column(name = "time") // 食品份量
    private Integer time;

    @ManyToOne
    @JoinColumn(name = "fk_sports_id") // 食品ID
    private Food sports;

    @ManyToOne
    @JoinColumn(name = "fk_daily_record_id") // 日記編號
    private DailyRecord daily_record;

}
