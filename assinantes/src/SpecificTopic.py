# Descrição: responsavel por codificar messagens
# vindas do rabbitmq


class SpecificTopic:
    def __init__(self, name):
        self.name = name

    def __repr__(self):
        return f"SpecificTopic(name={self.name})"
