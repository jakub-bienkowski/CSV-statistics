package com.isa.homeworks;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ActivityMethods {

    public List<Activity> createActivityList(){
        try {
            return (List<Activity>) new CsvToBeanBuilder(new FileReader("/home/kuba/iSA/Homeworks/jjdzr1-homeworks/homework_4/file-analyzer/src/main/resources/activities.csv"))
                    .withType(Activity.class).build().parse();
        }
        catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public LocalDateTime converter (String date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(date, dtf);
    }

}
