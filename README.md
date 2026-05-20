# URL Shortener API

Uma API funcional e performática desenvolvida em **Clojure** para encurtamento de URLs, focada em simplicidade e eficiência na persistência de dados.

## Funcionalidades

* **Encurtamento de URLs:** Conversão de URLs longas em códigos curtos exclusivos.
* **Redirecionamento:** Resolução eficiente de códigos para as suas respectivas URLs originais.
* **Consulta:** Listagem de todas as URLs registadas e busca detalhada por código.
* **Health Check:** Endpoint para monitoramento da disponibilidade da API.

## Tecnologias Utilizadas

* **Linguagem:** Clojure
* **Framework Web:** Compojure (para roteamento) e Ring
* **Serialização:** Cheshire (para JSON)
* **Gerenciador de Dependências:** Leiningen

## Rotas da API

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/api/health` | Verifica se o serviço está online. |
| `GET` | `/api` | Retorna todas as URLs cadastradas. |
| `GET` | `/api/:code` | Retorna os metadados de uma URL via código. |
| `GET` | `/api/redirect/:code` | Redireciona o utilizador para a URL original. |
| `POST` | `/api` | Encurta uma nova URL (recebe JSON no body). |

### Exemplo de Uso (POST `/api`)

**Requisição:**
```json
{
  "url": "[https://www.google.com](https://www.google.com)"
}
```