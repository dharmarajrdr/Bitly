package com.dharmaraj.bitly.strategy;

public class EnterpriseUserStrategy implements UserPlanStrategy {

    @Override
    public long getTimeToLiveDays() {
        
        return 365;
    }
    
}
