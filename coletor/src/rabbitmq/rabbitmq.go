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

// @param
func Connect(url string) (*amqp.Channel, *amqp.Connection) {
	conn, err := amqp.Dial(url)
	failOnError(err, "Failed to connect to RabbitMQ")

	ch, err := conn.Channel()
	failOnError(err, "Failed to open a channel")

	return ch, conn
}

func CreateQueue(ch *amqp.Channel, queueName string) {
	_, err := ch.QueueDeclare(
		queueName, // name
		false,     // durable: quando true, mantém a fila caso o RabbitMQ seja reiniciado
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
		false,     // mandatory: quando true, retorna a msg ao produtor caso não exist filas vinculadas
		false,     // immediate: quando true, entrega para o primeiro consumidor disponivel. Retorna error se não tiver consymidor disponivel
		amqp.Publishing{ // messagem
			ContentType:  "text/plain",
			Body:         tweet,
			DeliveryMode: amqp.Persistent, // Armazena a messagem no disco, fica disponivel caso RabbitMQ seja reiniciado
		})
	return err
}
