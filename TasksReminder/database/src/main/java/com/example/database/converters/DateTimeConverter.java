package com.example.database.converters;

import android.arch.persistence.room.TypeConverter;

import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

public final class DateTimeConverter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    private DateTimeConverter(){}
    @TypeConverter public static String fromOffsetDateTime(OffsetDateTime date){
        return date.format(FORMATTER);
    }
    @TypeConverter public static OffsetDateTime toOffsetTime(String value){
        return FORMATTER.parse(value,OffsetDateTime.FROM);
    }
}
