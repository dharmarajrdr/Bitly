package com.dharmaraj.bitly.factory;

import com.dharmaraj.bitly.models.UserPlan;
import com.dharmaraj.bitly.strategy.BusinessUserStrategy;
import com.dharmaraj.bitly.strategy.EnterpriseUserStrategy;
import com.dharmaraj.bitly.strategy.FreeUserStrategy;
import com.dharmaraj.bitly.strategy.TeamUserStrategy;
import com.dharmaraj.bitly.strategy.UserPlanStrategy;

public class UserPlanFactory {

    public static UserPlanStrategy getStrategy(UserPlan userPlan) {

        switch (userPlan.toString()) {
            case "FREE" : {
                return new FreeUserStrategy();
            }
            case "TEAM" : {
                return new TeamUserStrategy();
            }
            case "BUSINESS" : { 
                return new BusinessUserStrategy();
            }
            case "ENTERPRISE" : {
                return new EnterpriseUserStrategy();
            }
        }
        return null;
    }
}
