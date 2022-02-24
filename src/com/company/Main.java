package com.company;

import com.company.model.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {
        // Challenge 1:
        Map<ActivityType, String> bestPerformers = findBestPerformers();
        bestPerformers.forEach((activityType, username) -> System.out.printf("%s : %s%n", activityType, username));

        // Challenge 2:
        BestTaskDTO messageDTO = createMessageDTO("jovan");
        System.out.println(messageDTO);

    }

    public static Map<ActivityType, String> findBestPerformers() {
        Map<ActivityType, String> bestPerformance = new HashMap<>();
        Arrays.stream(ActivityType.values()).forEach(activityType -> findCompletedActivities()
                .filter(activity -> activity.getType() == activityType)
                .min(Main::timeComparator)
                .ifPresent(activity -> bestPerformance.put(activityType, activity.getUsername())));
        return bestPerformance;
    }

    private static BestTaskDTO createMessageDTO(String username) {
        // Most likely throw custom exception and handle it in with @ExceptionHandler somewhere else (return 404)
        Activity activity = findBestActivityForUsername(username).orElseThrow(RuntimeException::new);

        return new BestTaskDTO(activity, findBestUserForActivityType(activity.getType()).orElse(null),
                findStrongestSuit(username).orElse(null));
    }

    private static Optional<StrongestSuit> findStrongestSuit(String username) {
        List<StrongestSuit> averages = new ArrayList<>();
        Arrays.stream(ActivityType.values()).forEach(activityType -> findCompletedActivities()
                .filter(activity -> activity.getUsername().equals(username))
                .filter(activity -> activity.getType() == activityType)
                .mapToLong(activity -> ChronoUnit.SECONDS.between(activity.getStart(), activity.getStop()))
                .average()
                .ifPresent(value -> averages.add(new StrongestSuit(activityType, value))));

        return averages.stream()
                .min(Comparator.comparingDouble(StrongestSuit::getAverageSeconds));
    }

    private static Optional<String> findBestUserForActivityType(ActivityType activityType) {
        return findCompletedActivities()
                .filter(activity -> activity.getType() == activityType)
                .min(Main::timeComparator)
                .map(Activity::getUsername);
    }

    private static Optional<Activity> findBestActivityForUsername(String username) {
        return findCompletedActivities()
                .filter(activity -> activity.getUsername().equals(username))
                .min(Main::timeComparator);
    }

    private static Stream<Activity> findCompletedActivities() {
        return getAllActivities()
                .stream()
                .filter(activity -> activity.getStatus() == ActivityStatus.COMPLETED);
    }

    private static int timeComparator(Activity a1, Activity a2) {
        long interval1 = ChronoUnit.SECONDS.between(a1.getStart(), a1.getStop());
        long interval2 = ChronoUnit.SECONDS.between(a2.getStart(), a2.getStop());
        return Long.compare(interval1, interval2);
    }

    public static List<Activity> getAllActivities() {
        final LocalDateTime now = LocalDateTime.now();
        return new ArrayList<>() {{
            add(new Activity(1L, ActivityType.CLEANING, ActivityStatus.COMPLETED, now.minusSeconds(1), now, "a"));
            add(new Activity(1L, ActivityType.CLEANING, ActivityStatus.COMPLETED, now.minusSeconds(3), now, "jovan"));
            add(new Activity(1L, ActivityType.CLEANING, ActivityStatus.COMPLETED, now.minusSeconds(2), now, "jovan"));
            add(new Activity(1L, ActivityType.DOING_THE_DISHES, ActivityStatus.COMPLETED, now.minusSeconds(1), now, "b"));
            add(new Activity(1L, ActivityType.DOING_THE_DISHES, ActivityStatus.COMPLETED, now.minusSeconds(41), now, "jovan"));
            add(new Activity(1L, ActivityType.PUTTING_GARBAGE, ActivityStatus.COMPLETED, now.minusSeconds(5), now, "c"));
            add(new Activity(1L, ActivityType.DOING_THE_DISHES, ActivityStatus.COMPLETED, now.minusSeconds(1), now, "jovan"));

        }};
    }
}
