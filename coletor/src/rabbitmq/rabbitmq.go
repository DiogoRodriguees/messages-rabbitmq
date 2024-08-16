package rabbitmq

import (
	"log"

	"github.com/streadway/amqp"
)

var RabbitMQUrl = "amqp://guest:guest@localhost:5672/"
var QueueName = "tweets"

func failOnError(err error, msg string) {
	if err != nil {
		log.Fatalf("%s: %s", msg, err)
	}
}

func Connect(rabbitMQUrl string) (*amqp.Channel, *amqp.Connection) {
	conn, err := amqp.Dial(rabbitMQUrl)
	failOnError(err, "Failed to connect to RabbitMQ")

	ch, err := conn.Channel()
	failOnError(err, "Failed to open a channel")

	return ch, conn
}

func CreateQueue(ch *amqp.Channel, queueName string) {
	_, err := ch.QueueDeclare(
		queueName, // name
		true,      // durable
		false,     // delete when unused
		false,     // exclusive
		false,     // no-wait
		nil,       // arguments
	)
	failOnError(err, "Failed to declare a queue")
}

func PublishMessage(ch *amqp.Channel, queueName string, tweet []byte) error {
	err := ch.Publish(
		"",        // exchange
		queueName, // routing key
		false,     // mandatory
		false,     // immediate
		amqp.Publishing{
			ContentType: "text/plain",
			Body:        tweet,
		})
	return err
}
