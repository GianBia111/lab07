package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;
import it.unibo.nestedenum.IllegalMonthNameException;


/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        
        return new SortByMonthOrder();
    }

    public enum Month{
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        Month(int days){
            this.days=days;
        }

        public final static Month fromString(final String name){
            Objects.requireNonNull(name);
            try {
                return valueOf(name);
            } catch (IllegalArgumentException e) {
                // Fallback to manual scan before giving up
                Month match = null;
                for (final Month month: values()) {
                    if (month.toString().toLowerCase(Locale.ROOT).startsWith(name.toLowerCase(Locale.ROOT))) {
                        if (match != null) {
                            throw new IllegalArgumentException(
                                name + " is ambiguous: both " + match + " and " + month + " would be valid matches",
                                e
                            );
                        }
                        match = month;
                    }
                }
                if (match == null) {
                    throw new IllegalArgumentException("No matching months for " + name, e);
                }
                return match;
            }
        }
    }

    private static class SortByMonthOrder implements Comparator<String>{
        @Override
        public int compare(final String month1,final String month2){
            final Month converted_month1=Month.fromString(month1);
            final Month converted_month2=Month.fromString(month2);
            return Month.fromString(month1).compareTo(Month.fromString(month2));
        }
    }

    private static class SortByDate implements Comparator<String>{
        @Override
        public int compare(final String month1, final String month2){
            final Month converted_month1=Month.fromString(month1);
            final Month converted_month2=Month.fromString(month2);
            return Integer.compare(converted_month1.days, converted_month2.days);
        }
    }
}
