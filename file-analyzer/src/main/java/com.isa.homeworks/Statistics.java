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

        long numberOfActivities = activityList.stream()
                .count();
        logger.info("\nNumber of activities: " + numberOfActivities);
    }

    private void shortestActivity(List<Activity> activityList) {

        int shortestActivity = activityList.stream()
                .min((a1, a2) -> a1.getSpent_time() - a2.getSpent_time())
                .map(Activity::getSpent_time)
                .get();

        logger.info("\nShortest activity: " + shortestActivity + " s");
    }
    private void longestActivity(List<Activity> activityList) {

        int longestActivity = activityList.stream()
                .max((a1, a2) -> a1.getSpent_time() - a2.getSpent_time())
                .map(Activity::getSpent_time)
                .get();

        logger.info("\nLongest activity: " + longestActivity + " s");
    }
    private void mediumTimeOfActivities(List<Activity> activityList) {

        Integer time = 0;
        for (Activity activity : activityList) {
            time += activity.getSpent_time();
        }
        double mediumTime = (double) time / activityList.size();
        DecimalFormat df = new DecimalFormat("0.00");
        logger.info("\nAverage time spent on activity: "  + df.format(mediumTime) + " s");
    }
    private void medianTimeOfActivities(List<Activity> activityList) {

        List<Integer> medianList = new ArrayList<>();
        for (Activity activity : activityList) {
            medianList.add(activity.getSpent_time());
        }
        Collections.sort(medianList);
        int medianPosition = medianList.size()/2;
        logger.info("\nMedian of time spent on activity: " + medianList.get(medianPosition) + "s");
    }
    private void totalTimeSpent(List<Activity> activityList) {

        Integer totalTime = 0;
        for (Activity activity : activityList) {
            totalTime += activity.getSpent_time();
        }
        int hours = totalTime / 3600;
        int minutes = (totalTime / 60) % 60;

        logger.info("\nTotal time spent on activities: " + hours + "h " + minutes + "min");
    }
    private void activityHours(List<Activity> activityList, String activityName) {

        List<Integer> hours = new ArrayList<>();
        for (Activity activity : activityList) {
            if (activity.getActivity_name().equals(activityName)){
                Integer hour = am.converter(activity.getStart_time()).getHour();
                if (!hours.contains(hour)) {
                    hours.add(hour);
                }
            }
        }
        logger.info("\nHours in which user spent time on " + activityName + ": ");
        for (Integer hour : hours) {
            logger.info(hour + ", ");
        }
    }
    private void categoryTime(List<Activity> activityList, String categoryName) {

        Integer timeSpent = 0;
        for (Activity activity : activityList) {
            if (activity.getCategory_name().equals(categoryName)){
                timeSpent += activity.getSpent_time();
            }
        }
        int minutes = timeSpent / 60;
        int seconds = (timeSpent) % 60;
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
