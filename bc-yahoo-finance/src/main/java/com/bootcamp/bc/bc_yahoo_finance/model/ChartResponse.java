package com.bootcamp.bc.bc_yahoo_finance.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChartResponse {

    private Chart chart;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Chart {
        private List<Result> result;
        private Error error;

        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Result {
            private Meta meta;
            private List<Long> timestamp;
            private Indicators indicators;

            @Getter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Meta {
                private String currency;
                private String symbol;
                private String exchangeName;
                private String fullExchangeName;
                private String instrumentType;
                private Long firstTradeDate;
                private Long regularMarketTime;
                private Boolean hasPrePostMarketData;
                private Long gmtoffset;
                private String timezone;
                private String exchangeTimezoneName;
                private Double regularMarketPrice;
                private Double fiftyTwoWeekHigh;
                private Double fiftyTwoWeekLow;
                private Double regularMarketDayHigh;
                private Double regularMarketDayLow;
                private Long regularMarketVolume;
                private Double chartPreviousClose;

                @Getter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class CurrentTradingPeriod {
                    private Period pre;
                    private Period regular;
                    private Period post;

                    @Getter
                    @JsonIgnoreProperties(ignoreUnknown = true)
                    public static class Period {
                        private String timezone;
                        private Long start;
                        private Long end;
                        private Long gmtoffset;
                    }
                }
            }

            @Getter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Indicators {
                private List<Quote> quote;
                private List<AdjClose> adjclose;
            }

            @Getter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Quote {
                private List<Double> open;
                private List<Double> close;
                private List<Double> low;
                private List<Double> high;
                private List<Long> volume;
            }

            @Getter
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class AdjClose {
                private List<Double> adjclose;
            }
        }

        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Error {
            private String code;
            private String description;
        }
    }
}

// Chart
// --Result
// ----Meta (currency, symbol ...)
// ------CurrentTradingPeriod
// --------Period (pre)
// --------Period (regular)
// --------Period (post)
// ----timestamp
// ----Indicators
// ----Quote
// ------Lists: open, high, low, close, volume
// ----AdjClose
// ------List: adjclose
// error
