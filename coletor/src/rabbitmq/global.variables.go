package rabbitmq

var Messages = []Message{
	{
		GenericTopic: GenericTopic{
			Name: "Esportes",
		},
		SpecificTopics: []SpecificTopic{
			{Name: "Futebol"},
			{Name: "Volei"},
		},
	},
	{
		GenericTopic: GenericTopic{
			Name: "Esportes",
		},
		SpecificTopics: []SpecificTopic{
			{Name: "Tenis"},
			{Name: "Basquete"},
		},
	},
	{
		GenericTopic: GenericTopic{
			Name: "Financas",
		},
		SpecificTopics: []SpecificTopic{
			{Name: "Mercado Imobiliario"},
			{Name: "Bolsa de valores"},
		},
	},
	{
		GenericTopic: GenericTopic{
			Name: "Noticias",
		},
		SpecificTopics: []SpecificTopic{
			{Name: "Politica"},
			{Name: "Educação"},
		},
	},
}
