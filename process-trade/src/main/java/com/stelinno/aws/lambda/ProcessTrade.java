package com.stelinno.aws.lambda;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context; 
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.amazonaws.services.lambda.runtime.events.SNSEvent.SNSRecord;
import com.google.gson.Gson;

//public class ProcessTrade implements RequestHandler<Integer, String> {
//public class ProcessTrade implements RequestHandler<Trade, String> {
//public class ProcessTrade implements RequestHandler<Object, String> {
public class ProcessTrade implements RequestHandler<SNSEvent, String> {
	static final Logger logger = Logger.getLogger(ProcessTrade.class.getName());
	static final Gson gson = new Gson();
	
    /*public String myHandler(int tradeId, Context context) {
        return String.format("Processed trade with id %d via myHandler method", tradeId);
    }*/

	/*public String handleRequest(Integer tradeId, Context context) {
		return String.format("Processed trade with id %d via handleRequest method", tradeId);
	}*/
	
	/*public String handleRequest(SNSEvent event, Context context) {
		logger.info("*************** SNSEvent ****************");
		logger.info(gson.toJson(event));		
		logger.info("*********** END OF SNSEvent *************");
		logger.info("*************** Message ****************");
		logger.info(event.getRecords().get(0).getSNS().getMessage());
		logger.info("*********** END OF Message *************");
		return "got an event!";
	}*/
	
	/*public String handleRequest(S3EventNotification eventNotofication, Context context) {
		logger.info(String.format("got %d records", eventNotofication.getRecords().size()));
		logger.info("*************** JSON DATA ****************");
		logger.info(eventNotofication.toJson());
		eventNotofication.
		logger.info("*********** END OF JSON DATA *************");
		return "got an event!";
	}*/
	
	/*public String handleRequest(Object trade, Context context) {
		if(trade != null) {
			logger.info("*************** DATA ****************");
			logger.info(trade.toString());
			logger.info("*********** END OF DATA *************");
			logger.info("CLASS = " + trade.getClass().getName());
			
			try {
				logger.info("Trying to cast object to SubscribeResult...");
				SubscribeResult result = (SubscribeResult)trade;
				logger.info("Cast object succeeded!");
			}
			catch(Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			
			try {
				logger.info("Trying to cast object to S3EventNotification...");
				S3EventNotification result = (S3EventNotification)trade;
				logger.info("Cast object succeeded!");
			}
			catch(Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			
			try {
				logger.info("Trying to parse JSON to S3EventNotification...");
				S3EventNotification result = S3EventNotification.parseJson(trade.toString());
				logger.info("Parse object succeeded!");
			}
			catch(Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}	
			
			try {
				logger.info("Trying via LinkedHashMap ...");
				HashMap<String, Object> map = (LinkedHashMap)trade;
				logger.info("Parse LinkedHashMap succeeded!");
			}
			catch(Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}				
		}
		else
			logger.info("object was null");
		
		return "fully done!";
	}*/
	
	/**
	 * http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBMapper.html
	 * http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBMapper.Methods.html
	 */
	
	public String handleRequest(SNSEvent event, Context context) {
		logger.info("handle request is starting....");
		
		Iterator<SNSRecord> recordsIter = event.getRecords().iterator();
		while(recordsIter.hasNext()) {
			logger.info("found a SNS record....");
			SNSRecord snsRecord = recordsIter.next();
			Trade trade = gson.fromJson(snsRecord.getSNS().getMessage(), Trade.class);
			logger.info("found a trade object in the SNS record....");
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
		}
		
		return "ProcessTrade function done!";
	}
}