# Tutorial on AWS lambda using Java
https://docs.aws.amazon.com/lambda/latest/dg/lambda-java-how-to-create-deployment-package.html
https://docs.aws.amazon.com/lambda/latest/dg/java-create-jar-pkg-maven-no-ide.html

# AWS Lambda programming model
https://docs.aws.amazon.com/lambda/latest/dg/java-programming-model.html
http://docs.aws.amazon.com/lambda/latest/dg/use-cases.html

# Write from Lambda
https://www.youtube.com/watch?v=G_-aEXmluq8

# Caveats when using DynamoDB
1) Assign policy rights to use the DB
2) Increase timeout to 60 seconds
3) Increase heap size to 512MB

# Using SNS with Lambda
https://aws.amazon.com/blogs/mobile/invoking-aws-lambda-functions-via-amazon-sns/
https://stackoverflow.com/questions/32764138/aws-sns-publishing-to-a-subscribed-lambda-function-logs-null-fields

# Using SQS with Lambda
https://cloudonaut.io/integrate-sqs-and-lambda-serverless-architecture-for-asynchronous-workloads/

# Maven sources
mvn dependency:sources

# JSON Viewer
http://jsonviewer.stack.hu/

# Policy
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "dynamodb:DeleteItem",
                "dynamodb:GetItem",
                "dynamodb:PutItem",
                "dynamodb:Scan",
                "dynamodb:UpdateItem"
            ],
            "Resource": "arn:aws:dynamodb:eu-central-1:877257283291:table/Category"
        }
    ]
}

# Test data
{"tradeId":"90654","tradeAmount":"20000000", "tradeBook":"FX Spot", "trader":"Michael Sundgaard"}

{
"default": "{\"tradeId\":\"88654\",\"tradeAmount\":\"20000000\", \"tradeBook\":\"FX Spot\", \"trader\":\"Michael Sundgaard\"}", 
"lambda": "{\"tradeId\":\"88654\",\"tradeAmount\":\"20000000\", \"tradeBook\":\"FX Spot\", \"trader\":\"Michael Sundgaard\"}"
}

# Large test data (SNS)
{
  "Records": [
    {
      "EventVersion": "1.0",
      "EventSubscriptionArn": "arn:aws:sns:EXAMPLE",
      "EventSource": "aws:sns",
      "Sns": {
        "SignatureVersion": "1",
        "Timestamp": "1970-01-01T00:00:00.000Z",
        "Signature": "EXAMPLE",
        "SigningCertUrl": "EXAMPLE",
        "MessageId": "95df01b4-ee98-5cb9-9903-4c221d41eb5e",
        "Message": "{\"tradeId\":\"88654\",\"tradeAmount\":\"20000000\", \"tradeBook\":\"FX Spot\", \"trader\":\"Michael Sundgaard\"}",
        "MessageAttributes": {
          "Test": {
            "Type": "String",
            "Value": "TestString"
          },
          "TestBinary": {
            "Type": "Binary",
            "Value": "TestBinary"
          }
        },
        "Type": "Notification",
        "UnsubscribeUrl": "EXAMPLE",
        "TopicArn": "arn:aws:sns:EXAMPLE",
        "Subject": "TestInvoke"
      }
    }
  ]
}

# Large test data 2 (Invalid SNS)
{
    "records": [
        {
            "sns": {
                "messageAttributes": {},
                "signingCertUrl": "https://sns.eu-central-1.amazonaws.com/SimpleNotificationService-b95095beb82e8f6a046b3aafc7f4149a.pem",
                "messageId": "1feff445-7966-59aa-94d8-a59a8a9d998d",
                "message": "{\"tradeId\":\"88654\",\"tradeAmount\":\"20000000\", \"tradeBook\":\"FX Spot\", \"trader\":\"Michael Sundgaard\"}",
                "unsubscribeUrl": "https://sns.eu-central-1.amazonaws.com/?Action=Unsubscribe&SubscriptionArn=arn:aws:sns:eu-central-1:877257283291:trade-changes:0cf58b55-4df6-4cd7-b2d0-ddaf55b19136",
                "type": "Notification",
                "signatureVersion": "1",
                "signature": "YJfi+u1jiaMveK1iurRmjrbQHsjgbYz7ARqkKsfHfl54ZeqrD81PoMR70z/rr5glpyv08HbJpAUIS7ci7DmBeaeyS4KSF7EPYTpdZdzkrrJrvP+ozc4xxy0Lv6250zggB8LaUTXE9NqRecQlh/mRYdYWBq6964/enXVyac3Wuno1SjTXHrYpQ/uKnbGDK9x01q6YuBJQ9hBeJdq/7Hqwl7qIZJq6yzYK16L8BVV63fjk4OO3vZlGFK/tvReC1MKxnbl5KrvpZ9W0Eb7bcAvmgeykFz6owpkwjdk0NRKPb5idy9LWVlP/NjTYqYG7qYLapy5bXHqK2LmnuD7eYmY2rw==",
                "timestamp": {
                    "iMillis": 1499853243748,
                    "iChronology": {
                        "iBase": {
                            "iMinDaysInFirstWeek": 4
                        }
                    }
                },
                "topicArn": "arn:aws:sns:eu-central-1:877257283291:trade-changes"
            },
            "eventVersion": "1.0",
            "eventSource": "aws:sns",
            "eventSubscriptionArn": "arn:aws:sns:eu-central-1:877257283291:trade-changes:0cf58b55-4df6-4cd7-b2d0-ddaf55b19136"
        }
    ]
}