package rabbitmq

type SpecificTopic struct {
	Name string
}

type GenericTopic struct {
	Name string
}

type Message struct {
	GenericTopic   GenericTopic
	SpecificTopics []SpecificTopic
}
