package com.stelinno.aws.lambda;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.lambda.runtime.Context; 
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

//public class ProcessTrade implements RequestHandler<Integer, String> {
public class ProcessTrade implements RequestHandler<Trade, String> {
	static final Logger logger = Logger.getLogger(ProcessTrade.class.getName());
	static final Gson gson = new Gson();
	
    /*public String myHandler(int tradeId, Context context) {
        return String.format("Processed trade with id %d via myHandler method", tradeId);
    }*/

	/*public String handleRequest(Integer tradeId, Context context) {
		return String.format("Processed trade with id %d via handleRequest method", tradeId);
	}*/
	
	public String handleRequest(Trade trade, Context context) {
		
		logger.info("handle request is starting....");
		/*AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDB dynamoDB = new DynamoDB(client);

		Table table = dynamoDB.getTable("Trade");
		Item tradeItem = table.getItem("Id", trade.getTradeId());
		table.putItem(tradeItem);*/
		
		// Must allow the policy in IAM inline policy for this to work
		final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();
		logger.info("ddb created....");
		
		try {
			DynamoDB dynamoDB = new DynamoDB(ddb);
			logger.info("dynamoDB created....");
			Table table = dynamoDB.getTable("Trade");
			logger.info("got table....");
			
			//TableDescription desc = table.describe();
			//logger.info("described table....");
			
			//table.getItem("tradeId", 10987);
			
			//Item tradeItem = table.getItem("Id", trade.getTradeId());
			Item tradeItem = new Item();
			tradeItem.withPrimaryKey("tradeId", trade.getTradeId());
			tradeItem.withDouble("tradeAmount", trade.getTradeAmount());
			tradeItem.withString("tradeBook", trade.getTradeBook());
			tradeItem.withString("trader", trade.getTrader());
			tradeItem.withJSON("trade", gson.toJson(trade));
			
			logger.info("created item....");
			table.putItem(tradeItem);
			logger.info("putting item....");
						
		}
		catch(Throwable e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
		finally {
			//context.getClientContext().
		}
		return String.format("Processed POJO trade with id %d and amount %s via handleRequest method V2", 
				trade.getTradeId(), String.valueOf(trade.getTradeAmount()));
	}
}