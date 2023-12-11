# scrd
Teste votacao

INSTRUÇÕES:

1- Para criação do banco:
    encontra-se um arquivo "scrd.sql" na raiz do repositório.

2-Para usar a API:
    encontra-e um arquivo do postman para importação na raiz do repositório, asssim como a documentação está disponível pelo link do swagger: http://localhost:8080/swagger-ui/index.html

    Para começar uma votação:
        A- Criar um "Associado":
            POST: {
                        "nome": "Jose",
                        "cpf": "111111"
                    }  

        B: Criar uma "Assembleia":
            POST: {
                        "descricao": "assembleia 1"
                    }

        C: Criar uma "Pauta":
            POST:  {
                        "descricao": "pauta 1"
                    }  

        D: Criar uma "Votacao", com o horário atual (será dado o prazo de votação de tempoLimiteMinutos):
            POST: {
                    "abertura": "2023-12-11T13:57:00.578",
                    "tempoLimiteMinutos": 1,
                    "assembleia": {
                            "idAssembleia": 1
                        },
                    "pauta": {
                        "idPauta": 1
                    }
                        }

        E: Criar "Voto"    :
            POST: {
                    "voto": "SIM",
                    "associado": {
                        "idAssociado":1
                    },
                    "votacao": {
                        "idVotacao": 1
                    }
                        }

        D: Verificar Resultado:
            GET: localhost:8080/api/voto
            {
                "idVotacao": 1
            }