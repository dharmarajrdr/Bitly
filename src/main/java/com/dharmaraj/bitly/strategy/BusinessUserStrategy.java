package com.dharmaraj.bitly.strategy;

public class BusinessUserStrategy implements UserPlanStrategy {

    @Override
    public long getTimeToLiveDays() {
        
        return 30;
    }
    
}
