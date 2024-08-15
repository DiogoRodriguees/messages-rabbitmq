package rabbitmq

var Messages = []Tweet{
	{
		Topic: Topic{
			Name: "Esportes",
		},
		SpecificTopics: []SpecificTopic{
			{Name: "Futebol"},
			{Name: "Volei"},
		},
	},
	{
		Topic: Topic{
			Name: "Esportes",
		},
		SpecificTopics: []SpecificTopic{
			{Name: "Tenis"},
			{Name: "Basquete"},
		},
	},
	{
		Topic: Topic{
			Name: "Financas",
		},
		SpecificTopics: []SpecificTopic{
			{Name: "Mercado Imobiliario"},
			{Name: "Bolsa de valores"},
		},
	},
	{
		Topic: Topic{
			Name: "Noticias",
		},
		SpecificTopics: []SpecificTopic{
			{Name: "Politica"},
			{Name: "Educação"},
		},
	},
}
