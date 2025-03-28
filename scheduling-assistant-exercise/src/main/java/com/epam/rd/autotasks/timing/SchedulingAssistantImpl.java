package com.epam.rd.autotasks.timing;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

public class SchedulingAssistantImpl implements SchedulingAssistant {

    private final Collection<Developer> team;
    private final LocalDate today;

    public SchedulingAssistantImpl(Collection<Developer> team, LocalDate today) {
        this.team = team;
        this.today = today;
    }

    @Override
    public LocalDateTime schedule(long meetingDurationMinutes, MeetingTimingPreferences preferences) {
        List<TimeInterval> possibleWindows = getPossibleMeetingWindows(preferences);

        for (TimeInterval window : possibleWindows) {
            if (preferences.inPeriod == MeetingTimingPreferences.InPeriodPreference.EARLIEST) {
                LocalDateTime start = window.start;
                LocalDateTime end = window.end.minusMinutes(meetingDurationMinutes);
                while (!start.isAfter(end)) {
                    LocalDateTime finalStart = start;
                    if (allDevelopersAvailable(finalStart, meetingDurationMinutes)) return finalStart;
                    start = start.plusMinutes(1);
                }
            } else {
                LocalDateTime start = window.end.minusMinutes(meetingDurationMinutes);
                while (!start.isBefore(window.start)) {
                    LocalDateTime finalStart = start;
                    if (allDevelopersAvailable(finalStart, meetingDurationMinutes)) return finalStart;
                    start = start.minusMinutes(1);
                }
            }
        }
        return null;
    }

    private boolean allDevelopersAvailable(LocalDateTime startGMT, long durationMin) {
        return team.stream().allMatch(dev -> isWithinWorkHours(dev, startGMT, durationMin));
    }

    private List<TimeInterval> getPossibleMeetingWindows(MeetingTimingPreferences preferences) {
        List<TimeInterval> intervals = new ArrayList<>();
        LocalDate startDate, endDate;

        switch (preferences.period) {
            case TODAY -> startDate = endDate = today;
            case TOMORROW -> startDate = endDate = today.plusDays(1);
            case THIS_WEEK -> {
                startDate = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                endDate = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
            }
            default -> throw new IllegalStateException("Unexpected period");
        }

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            LocalDateTime dayStart = date.atTime(0, 0);
            LocalDateTime dayEnd = date.atTime(23, 59);

            if (preferences.inPeriod == MeetingTimingPreferences.InPeriodPreference.EARLIEST)
                intervals.add(new TimeInterval(dayStart, dayEnd));
            else
                intervals.add(0, new TimeInterval(dayStart, dayEnd)); // reverse order for LATEST
        }
        return intervals;
    }

    private boolean isWithinWorkHours(Developer dev, LocalDateTime startGMT, long durationMin) {
        ZoneId zoneId = getZoneId(dev.city);
        ZonedDateTime startLocal = startGMT.atZone(ZoneOffset.UTC).withZoneSameInstant(zoneId);
        LocalTime localStartTime = startLocal.toLocalTime();

        LocalTime workStart = dev.workDayStartTime;
        LocalTime workEnd = workStart.plusHours(8);

        return !localStartTime.isBefore(workStart) && !localStartTime.plusMinutes(durationMin).isAfter(workEnd);
    }

    private ZoneId getZoneId(String city) {
        return switch (city.toLowerCase()) {
            case "tbilisi" -> ZoneId.of("Asia/Tbilisi");
            case "samara" -> ZoneId.of("Europe/Samara");
            case "prague" -> ZoneId.of("Europe/Prague");
            case "los angeles" -> ZoneId.of("America/Los_Angeles");
            case "new york" -> ZoneId.of("America/New_York");
            case "london" -> ZoneId.of("Europe/London");
            case "paris" -> ZoneId.of("Europe/Paris");
            default -> throw new IllegalArgumentException("Unknown city: " + city);
        };
    }

    private static class TimeInterval {
        LocalDateTime start;
        LocalDateTime end;

        TimeInterval(LocalDateTime start, LocalDateTime end) {
            this.start = start;
            this.end = end;
        }
    }
}