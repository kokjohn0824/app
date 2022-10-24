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
@Table(name = "food_daily") // 食品內容
public class Food_daily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_daily_id")
    private Integer food_daily_id;

    @Column(name = "side") // 食品份量
    private Integer side;

    @ManyToOne
    @JoinColumn(name = "fk_food_id") // 食品ID
    private Food food;

    @ManyToOne
    @JoinColumn(name = "fk_daily_record_id") // 日記編號
    private DailyRecord daily_record;

}