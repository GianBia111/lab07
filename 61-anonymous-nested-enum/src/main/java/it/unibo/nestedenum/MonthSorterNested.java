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
        return null;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return null;
    }

    public enum Month{
        GENNAIO(31),
        FEBBRAIO(28),
        MARZO(31),
        APRILE(30),
        MAGGIO(31),
        GIUGNO(30),
        LUGLIO(31),
        AGOSTO(31),
        SETTEMBRE(30),
        OTTOBRE(31),
        NOVEMBRE(30),
        DICEMBRE(31);

        private final int days;

        Month(int days){
            this.days=days;
        }

        public final static Month fromString(String s_month){
            Month guessed=null;
            for(final Month m : values()){
                if(s_month==m.toString() || m.toString().toLowerCase(Locale.ROOT).startsWith(s_month.toLowerCase(Locale.ROOT))){
                    if(guessed!=null)
                        throw new IllegalMonthNameException("Il nome che hai passato è ambiguo, sono statri trovati piu' di un match");
                    else
                        guessed=m;
                }
            }
            if(guessed==null)
                throw new IllegalMonthNameException("Il nome che hai passato non match in alcun modo con nessun nome del mese");

            return guessed;
        }
    }

    



}
