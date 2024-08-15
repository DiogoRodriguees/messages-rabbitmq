package rabbitmq

type SpecificTopic struct {
	Name string
}

type Topic struct {
	Name string
}

type Tweet struct {
	Topic          Topic
	SpecificTopics []SpecificTopic
}
