package com.gthm.sample.model;


import com.gthm.sample.config.HitOrFLop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movies {

    int id;
    String movieName;
    Long year;

    HitOrFLop movieRecord;


}