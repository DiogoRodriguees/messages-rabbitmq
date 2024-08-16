package app

import (
	"colectors/rabbitmq"
	"colectors/schedulers"
	"encoding/json"
	"fmt"
	"log"
	"time"

	"github.com/streadway/amqp"
)

func Run() {
	log.Println("Starting colector application ...")
	ch, conn := rabbitmq.Connect(rabbitmq.RabbitMQUrl)

	defer conn.Close()
	defer ch.Close()

	createSchedulers(ch)
	startServer()
}

func createSchedulers(ch *amqp.Channel) {
	var schedulersList []*schedulers.Scheduler
	rabbitmq.CreateQueue(ch, rabbitmq.QueueName)

	for _, message := range rabbitmq.Messages {
		s := schedulers.Create(func() {

			msg, err := json.Marshal(message)
			if err != nil {
				panic("Failed o marsahl json")
			}

			fmt.Println("Send message with topic: ", message.Topic.Name)

			err = rabbitmq.PublishMessage(ch, rabbitmq.QueueName, msg)
			if err != nil {
				log.Printf("Failed to publish message: %s", err)
			}
		})
		schedulersList = append(schedulersList, s)
	}

	schedulers.RunMany(schedulersList)
}

func startServer() {
	for {
		time.Sleep(time.Second * 1)
	}
}
