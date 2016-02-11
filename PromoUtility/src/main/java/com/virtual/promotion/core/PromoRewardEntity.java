package com.virtual.promotion.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by QZ88 on 20/08/2015.
 */
public class PromoRewardEntity {
    @JsonProperty("@type")
    public String type;

    @JsonProperty("changeType")
    public String changeType;

    @JsonProperty("changeQty")
    public int changeQty;

    @JsonProperty("changeAmount")
    public double changeAmount;

    @JsonProperty("changeCurrency")
    public String changeCurrency;

    @JsonProperty("changePercent")
    public double changePercent;

    @JsonProperty("changeUom")
    public String changeUom;

    @JsonProperty("voucherNumber")
    public String voucherNumber;

    @JsonProperty("voucherDescription")
    public String voucherDescription;

    public String getChangeType() {
        return changeType;
    }

@Override
    public String toString() {
        return "PromoRewardEntity{" +
                "type='" + type + '\'' +
                ", changeType='" + changeType + '\'' +
                ", changeQty='" + changeQty + '\'' +
                ", changeAmount=" + changeAmount +
                ", changeCurrency='" + changeCurrency + '\'' +
                ", changePercent=" + changePercent +
                ", changeUom='" + changeUom + '\'' +
                ", voucherNumber='" + voucherNumber + '\'' +
                ", voucherDescription='" + voucherDescription + '\'' +
                '}';
    }

}
