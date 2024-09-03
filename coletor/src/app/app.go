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
	// Connectando com rabbitmq
	ch, conn := rabbitmq.Connect(rabbitmq.RabbitMQUrl)

	// Fecha conn e channel ao final dessa função ou caso ocorra algum erro
	defer conn.Close()
	defer ch.Close()

	createSchedulers(ch) // Cria agendamentos para enviar messagens com os topicos
	startServer()        // Loop pra manter o serviço rodando
}

func createSchedulers(ch *amqp.Channel) {
	var schedulersList []*schedulers.Scheduler   // Criando uma lista de schedular. Inicia vazia
	rabbitmq.CreateQueue(ch, rabbitmq.QueueName) // Cria a fila de envio do topicos

	// Para cada menssagem padrão ...
	for _, message := range rabbitmq.Messages {
		// Cria um agendamento para enviar a messagem
		s := schedulers.Create(func() {
			// Marshalling da message para enviar como string utf-8
			msg, err := json.Marshal(message)
			if err != nil {
				panic("Failed o marsahl json")
			}

			fmt.Println("Send message with topic: ", message.Topic.Name)

			// Publica message na fila (connecção, nome da fila, message serializada)
			err = rabbitmq.PublishMessage(ch, rabbitmq.QueueName, msg)
			if err != nil {
				log.Printf("Failed to publish message: %s", err)
			}
		})
		// Adicionando na lista de agendamentos para execução
		schedulersList = append(schedulersList, s)
	}

	// Executa todos os agendamentos criados
	schedulers.RunMany(schedulersList)
}

func startServer() {
	for {
		time.Sleep(time.Second * 1)
	}
}
