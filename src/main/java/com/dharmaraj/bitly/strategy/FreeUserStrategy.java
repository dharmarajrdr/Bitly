package com.dharmaraj.bitly.strategy;

public class FreeUserStrategy implements UserPlanStrategy {

    @Override
    public long getTimeToLiveDays() {
        
        return 1;
    }

}
