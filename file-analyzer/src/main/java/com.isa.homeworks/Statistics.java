package com.isa.homeworks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.DecimalFormat;
import java.util.*;

public class Statistics {

    private static final Logger logger = LoggerFactory.getLogger("CONSOLE_OUT");
    private final ActivityMethods am = new ActivityMethods();

    public void showStatistics(){
        List<Activity> stats = am.createActivityList();
        activitiesCounter(stats);
        shortestActivity(stats);
        longestActivity(stats);
        mediumTimeOfActivities(stats);
        medianTimeOfActivities(stats);
        totalTimeSpent(stats);
        activityHours(stats, "facebook.com");
        categoryTime(stats, "Browsers");
        mostIntenseHour(stats);
    }

    private void activitiesCounter(List<Activity> activityList){

        long numberOfActivities = activityList.size();

        logger.info("\nNumber of activities: " + numberOfActivities);
    }

    private void shortestActivity(List<Activity> activityList) {

        OptionalInt shortestActivity = activityList.stream()
                .mapToInt(Activity::getSpent_time)
                .min();

        logger.info("\nShortest activity: " + shortestActivity.orElse(0) + " s");
    }

    private void longestActivity(List<Activity> activityList) {

        OptionalInt longestActivity = activityList.stream()
                .mapToInt(Activity::getSpent_time)
                .max();

        logger.info("\nLongest activity: " + longestActivity.orElse(0) + " s");
    }

    private void mediumTimeOfActivities(List<Activity> activityList) {

        OptionalDouble mediumTimeOfActivities = activityList.stream()
                .mapToInt(Activity::getSpent_time)
                .average();

        DecimalFormat df = new DecimalFormat("0.00");
        logger.info("\nAverage time spent on activity: " + df.format(mediumTimeOfActivities.orElse(0.0)) + " s");
    }

    private void medianTimeOfActivities(List<Activity> activityList) {
        long numberOfActivities = activityList.size();
        OptionalInt medianTimeOfActivities = activityList.stream()
                .mapToInt(Activity::getSpent_time)
                .sorted()
                .limit(numberOfActivities / 2)
                .max();

        logger.info("\nMedian of time spent on activity: " + medianTimeOfActivities.orElse(0) + "s");
    }

    private void totalTimeSpent(List<Activity> activityList) {

        int totalTimeOfActivities = activityList.stream()
                .mapToInt(Activity::getSpent_time)
                .sum();

        int hours = totalTimeOfActivities / 3600;
        int minutes = (totalTimeOfActivities / 60) % 60;

        logger.info("\nTotal time spent on activities: " + hours + "h " + minutes + "min");
    }

    private void activityHours(List<Activity> activityList, String activityName) {

        logger.info("\nHours in which user spent time on " + activityName + ": ");

        activityList.stream()
                .filter(a -> a.getActivity_name().equals(activityName))
                .mapToInt(a -> am.converter(a.getStart_time()).getHour())
                .sorted()
                .distinct()
                .forEach(x -> logger.info(x + ", "));

    }

    private void categoryTime(List<Activity> activityList, String categoryName) {

        int totalTimeSpentOnCategory = activityList.stream()
                .filter(a -> a.getCategory_name().equals(categoryName))
                .mapToInt(Activity::getSpent_time)
                .sum();

        int minutes = totalTimeSpentOnCategory / 60;
        int seconds = totalTimeSpentOnCategory % 60;

        logger.info("\nTime spent time on " + categoryName + ": " + minutes + "min " + seconds + "s");
    }
    private void mostIntenseHour(List<Activity> activityList) {

        Map<Integer, Integer> timeInHours = new HashMap<>();
        for (Activity activity : activityList) {
            Integer hour = am.converter(activity.getStart_time()).getHour();
            if (!timeInHours.containsKey(hour)){
                timeInHours.put(hour, activity.getSpent_time());
            }
            else{
                Integer timeSpent = timeInHours.get(hour) + activity.getSpent_time();
                timeInHours.put(hour, timeSpent);
            }
        }
        mostIntensiveHourLog(timeInHours);
    }

    private void mostIntensiveHourLog(Map<Integer, Integer> timeInHours) {
        Integer longestTime = 0;
        Integer hour = 0;
        for (Map.Entry<Integer, Integer> entry : timeInHours.entrySet()) {
            if (entry.getValue() > longestTime){
                longestTime = entry.getValue();
                hour = entry.getKey();
            }
        }
        int minutes = longestTime / 60;
        int seconds = (longestTime) % 60;
        logger.info("\nMost intensive hour is: " + hour + " (working for " + minutes + "min " + seconds + "s)");
    }
}
