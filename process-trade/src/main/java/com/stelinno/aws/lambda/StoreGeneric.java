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

public class StoreGeneric  implements RequestHandler<GenericItem, String> {
	static final Logger logger = Logger.getLogger(ProcessTrade.class.getName());
	static final Gson gson = new Gson();
	
	/***
	 * 	{"key":"","keyValue":"","tableName":"","json":""}
	 * {"key":"CategoryName","keyValue":"medical","tableName":"Category","json":"{\"title\":\"Medical\"}"}
	 */
	public String handleRequest(GenericItem genericItem, Context context) {
		logger.info("handle request is starting....");
				
		// Must allow the policy in IAM inline policy for this to work
		final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();
		logger.info("ddb created....");
			
		try {
			DynamoDB dynamoDB = new DynamoDB(ddb);
			logger.info("dynamoDB created....");
			Table table = dynamoDB.getTable(genericItem.getTableName());
			logger.info("got table....");

			Item dynamoItem = new Item();
			dynamoItem.withPrimaryKey(genericItem.getKey(), genericItem.getKeyValue());
			dynamoItem.withJSON(genericItem.getTableName(), genericItem.getJson());
			
			logger.info("created item....");
			table.putItem(dynamoItem);
			logger.info("putting item....");
						
		}
		catch(Throwable e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
		finally {
			//context.getClientContext().
		}
		
		
		return "StoreGeneric function done!";
	}
}
