//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ease.st.model;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class STInfo {
    private static final Logger log = LoggerFactory.getLogger(STInfo.class);
    private String code;
    private String cnName;
    private double todayStart;
    private double yesterdayClose;
    private double current;
    private double rate;
    private double todayMax;
    private double todayMin;

    public static STInfo parse(String line) {
        if (StringUtils.isEmpty(line)) {
            return null;
        } else {
            String[] array = line.split("=");
            if (array.length < 2) {
                log.error("invalid line - missing =: {}", line);
                return null;
            } else {
                String numberStr = array[0];
                if (numberStr.contains("_")) {
                    numberStr = numberStr.substring(numberStr.lastIndexOf("_") + 1);
                }

                String stInfoStr = array[1];
                array = stInfoStr.split(",");
                if (array.length < 6) {
                    log.error("invalid line - : {}", line);
                    return null;
                } else {
                    STInfo stInfo = new STInfo();
                    stInfo.setCode(numberStr);
                    stInfo.setCnName(array[0]);
                    stInfo.setTodayStart(Double.parseDouble(array[1]));
                    stInfo.setYesterdayClose(Double.parseDouble(array[2]));
                    stInfo.setCurrent(Double.parseDouble(array[3]));
                    stInfo.calculate();
                    stInfo.setTodayMax(Double.parseDouble(array[4]));
                    stInfo.setTodayMin(Double.parseDouble(array[5]));
                    return stInfo;
                }
            }
        }
    }

    private void calculate() {
        this.rate = 100.0 * (this.current - this.yesterdayClose) / this.yesterdayClose;
    }

    @Override
    public String toString() {
        String var10000 = this.code;
        return "{" + var10000 + ", " + this.current + " - " + this.yesterdayClose + ", " + String.format("%.2f", this.rate) + "%" + String.format(" (%.2f-%.2f)", this.todayMin, this.todayMax) + "}";
    }

    public String toRichString() {
        String expression = this.toString();
        String prefix = "";
        if (this.rate > 9.9) {
            prefix = "¥¥¥¥¥";
        }
        else if (this.rate > 5.0) {
            prefix = "*****";
        } else {
            int count;
            int i;
            if (this.rate > 0.0) {
                count = (int)this.rate;

                for(i = 0; i < count; ++i) {
                    prefix = prefix + "*";
                }
            } else if (this.rate < 0.0) {
                count = (int)Math.abs(this.rate);

                for(i = 0; i < count; ++i) {
                    prefix = prefix + "0";
                }
            }
        }

        return prefix + "  " + expression;
    }

    public STInfo() {
    }

    public String getCode() {
        return this.code;
    }

    public String getCnName() {
        return this.cnName;
    }

    public double getTodayStart() {
        return this.todayStart;
    }

    public double getYesterdayClose() {
        return this.yesterdayClose;
    }

    public double getCurrent() {
        return this.current;
    }

    public double getRate() {
        return this.rate;
    }

    public double getTodayMax() {
        return this.todayMax;
    }

    public double getTodayMin() {
        return this.todayMin;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }

    public void setTodayStart(final double todayStart) {
        this.todayStart = todayStart;
    }

    public void setYesterdayClose(final double yesterdayClose) {
        this.yesterdayClose = yesterdayClose;
    }

    public void setCurrent(final double current) {
        this.current = current;
    }

    public void setRate(final double rate) {
        this.rate = rate;
    }

    public void setTodayMax(final double todayMax) {
        this.todayMax = todayMax;
    }

    public void setTodayMin(final double todayMin) {
        this.todayMin = todayMin;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof STInfo)) {
            return false;
        } else {
            STInfo other = (STInfo)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (Double.compare(this.getTodayStart(), other.getTodayStart()) != 0) {
                return false;
            } else if (Double.compare(this.getYesterdayClose(), other.getYesterdayClose()) != 0) {
                return false;
            } else if (Double.compare(this.getCurrent(), other.getCurrent()) != 0) {
                return false;
            } else if (Double.compare(this.getRate(), other.getRate()) != 0) {
                return false;
            } else if (Double.compare(this.getTodayMax(), other.getTodayMax()) != 0) {
                return false;
            } else if (Double.compare(this.getTodayMin(), other.getTodayMin()) != 0) {
                return false;
            } else {
                Object this$code = this.getCode();
                Object other$code = other.getCode();
                if (this$code == null) {
                    if (other$code != null) {
                        return false;
                    }
                } else if (!this$code.equals(other$code)) {
                    return false;
                }

                Object this$cnName = this.getCnName();
                Object other$cnName = other.getCnName();
                if (this$cnName == null) {
                    if (other$cnName != null) {
                        return false;
                    }
                } else if (!this$cnName.equals(other$cnName)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof STInfo;
    }

    public int hashCode() {
        int PRIME = 1;
        int result = 1;
        long $todayStart = Double.doubleToLongBits(this.getTodayStart());
        result = result * 59 + (int)($todayStart >>> 32 ^ $todayStart);
        long $yesterdayClose = Double.doubleToLongBits(this.getYesterdayClose());
        result = result * 59 + (int)($yesterdayClose >>> 32 ^ $yesterdayClose);
        long $current = Double.doubleToLongBits(this.getCurrent());
        result = result * 59 + (int)($current >>> 32 ^ $current);
        long $rate = Double.doubleToLongBits(this.getRate());
        result = result * 59 + (int)($rate >>> 32 ^ $rate);
        long $todayMax = Double.doubleToLongBits(this.getTodayMax());
        result = result * 59 + (int)($todayMax >>> 32 ^ $todayMax);
        long $todayMin = Double.doubleToLongBits(this.getTodayMin());
        result = result * 59 + (int)($todayMin >>> 32 ^ $todayMin);
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $cnName = this.getCnName();
        result = result * 59 + ($cnName == null ? 43 : $cnName.hashCode());
        return result;
    }
}
