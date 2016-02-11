package com.virtual.promotion.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Created by QZ88 on 20/08/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PromoThresholdEntity {
    @JsonProperty("@type")
    public String type;
    @JsonProperty("thresholdType")
    public String thresholdType;
    @JsonProperty("thresholdAmount")
    public double thresholdAmount;
    @JsonProperty("thresholdCurrency")
    public String thresholdCurrency;
    @JsonProperty("thresholdQty")
    public int thresholdQty;

    @Override
    public String toString() {
        return "PromoThresholdEntity{" +
                "type='" + type + '\'' +
                ", thresholdType='" + thresholdType + '\'' +
                ", thresholdAmount=" + thresholdAmount +
                ", thresholdCurrency='" + thresholdCurrency + '\'' +
                ", thresholdQty=" + thresholdQty +
                '}';
    }
}
