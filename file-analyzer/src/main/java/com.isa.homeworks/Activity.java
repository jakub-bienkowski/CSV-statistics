package com.isa.homeworks;

import com.opencsv.bean.CsvBindByName;
import java.util.Objects;


public class Activity {

    @CsvBindByName
    private String start_time;

    @CsvBindByName
    private String end_time;

    @CsvBindByName
    private Integer productivity;

    @CsvBindByName
    private String activity_name;

    @CsvBindByName
    private String category_name;

    @CsvBindByName
    private Integer spent_time;

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public Integer getProductivity() {
        return productivity;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public Integer getSpent_time() {
        return spent_time;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", productivity=" + productivity +
                ", activity_name='" + activity_name + '\'' +
                ", category_name='" + category_name + '\'' +
                ", spent_time=" + spent_time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        Activity activity = (Activity) o;
        return Objects.equals(getStart_time(), activity.getStart_time()) &&
                Objects.equals(getEnd_time(), activity.getEnd_time()) &&
                Objects.equals(getProductivity(), activity.getProductivity()) &&
                Objects.equals(getActivity_name(), activity.getActivity_name()) &&
                Objects.equals(getCategory_name(), activity.getCategory_name()) &&
                Objects.equals(getSpent_time(), activity.getSpent_time());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStart_time(), getEnd_time(), getProductivity(), getActivity_name(), getCategory_name(), getSpent_time());
    }
}
