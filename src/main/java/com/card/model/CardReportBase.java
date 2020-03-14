package com.card.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tenece on 13/03/2020.
 */
@Getter
@Setter
@ToString
public class CardReportBase {

    private Boolean success;
    private Integer start;
    private Integer limit;
    private Integer size;
    private Map<String, Integer> payload = new HashMap<>();

    public CardReportBase(Boolean success, Integer start, Integer limit, Integer size, Map<String, Integer> payload) {
        this.success = success;
        this.start = start;
        this.limit = limit;
        this.size = size;
        this.payload = payload;
    }
}
