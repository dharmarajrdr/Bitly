package com.dharmaraj.bitly.strategy;

public class TeamUserStrategy implements UserPlanStrategy {

    @Override
    public long getTimeToLiveDays() {
        
        return 7;
    }
    
}
