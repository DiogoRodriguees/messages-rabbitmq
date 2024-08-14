package rabbitmq

import (
	"log"

	"github.com/streadway/amqp"
)

var RabbitMQUrl = "amqp://guest:guest@rabbitmq:5672/"
var QueueName = "hello"

func failOnError(err error, msg string) {
	if err != nil {
		log.Fatalf("%s: %s", msg, err)
	}
}

func ConnectToRabbitMQ(rabbitMQUrl string) (*amqp.Channel, *amqp.Connection) {
	conn, err := amqp.Dial(rabbitMQUrl)
	failOnError(err, "Failed to connect to RabbitMQ")

	ch, err := conn.Channel()
	failOnError(err, "Failed to open a channel")

	return ch, conn
}

func CreateQueue(ch *amqp.Channel, queueName string) {
	_, err := ch.QueueDeclare(
		queueName, // name
		false,     // durable
		false,     // delete when unused
		false,     // exclusive
		false,     // no-wait
		nil,       // arguments
	)
	failOnError(err, "Failed to declare a queue")
}

func PublishMessage(ch *amqp.Channel, queueName string, message []byte) error {
	err := ch.Publish(
		"",        // exchange
		queueName, // routing key
		false,     //
		false,     //
		amqp.Publishing{
			ContentType: "text/plain",
			Body:        message,
		})
	return err
}
