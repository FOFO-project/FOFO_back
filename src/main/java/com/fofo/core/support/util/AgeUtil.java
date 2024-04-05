package com.fofo.core.support.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@UtilityClass
public class AgeUtil {

    public static int toAge(final LocalDateTime birthday) {
        return (int) ChronoUnit.YEARS.between(birthday, LocalDate.now());
    }

    public static int toKoreanAge(final LocalDateTime birthday) {
        return LocalDate.now().getYear() - birthday.getYear() + 1;
    }

}
