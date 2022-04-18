package org.mycompany.strategy;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.mycompany.entity.FtpAccountEntity;

public class UsernamePassCheckStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if(newExchange == null) {
			return oldExchange;
		}
		FtpAccountEntity account = newExchange.getMessage().getBody(FtpAccountEntity.class);
		if(account == null || account.getPassword().equals(newExchange.getMessage().getHeader("password"))) {
			newExchange.getMessage().setHeader("isValid", false);
			return newExchange;
		} else {
			oldExchange.getMessage().setHeader("isValid", true);
			return oldExchange;
		}
	}

}
