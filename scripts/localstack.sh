#### Localstack ####
# create sqs queue
aws --endpoint http://localhost:4566 --profile default sqs create-queue --queue-name events-queue

# send message
aws --endpoint http://localhost:4566 --profile default sqs send-message --queue-url http://localhost:4566/000000000000/events-queue --message-body "Hello, World!"

# receive message
aws --endpoint http://localhost:4566 --profile default sqs receive-message --queue-url http://localhost:4566/000000000000/events-queue

# list queues
aws --endpoint http://localhost:4566 --profile default sqs list-queues