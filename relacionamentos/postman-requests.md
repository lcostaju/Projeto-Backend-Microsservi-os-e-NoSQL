# Requisições Postman - UsuarioController

## Configuração Base
- **Base URL**: `http://localhost:8080`
- **Content-Type**: `application/json` (para requisições POST)

---

## 1. Listar Todos os Usuários

### GET - Buscar todos os usuários
- **Método**: GET
- **URL**: `http://localhost:8080/usuarios`
- **Headers**: Não é necessário
- **Body**: Não é necessário

**Descrição**: Retorna todos os usuários cadastrados no sistema.

---

## 2. Criar Usuário

### POST - Criar usuário simples (sem perfil)
- **Método**: POST
- **URL**: `http://localhost:8080/usuarios`
- **Headers**: 
  - `Content-Type: application/json`
- **Body** (raw JSON):
```json
{
  "nome": "João Silva"
}
```

**Descrição**: Cria um usuário sem perfil associado.

---

### POST - Criar usuário com perfil novo
- **Método**: POST
- **URL**: `http://localhost:8080/usuarios`
- **Headers**: 
  - `Content-Type: application/json`
- **Body** (raw JSON):
```json
{
  "nome": "Maria Santos",
  "perfil": {
    "bio": "Desenvolvedora Full Stack apaixonada por tecnologia",
    "avatarUrl": "https://example.com/avatar/maria.jpg"
  }
}
```

**Descrição**: Cria um usuário com um novo perfil. O perfil será salvo automaticamente antes do usuário.

---

### POST - Criar usuário com perfil existente
- **Método**: POST
- **URL**: `http://localhost:8080/usuarios`
- **Headers**: 
  - `Content-Type: application/json`
- **Body** (raw JSON):
```json
{
  "nome": "Pedro Costa",
  "perfil": {
    "id": "ID_DO_PERFIL_EXISTENTE"
  }
}
```

**Descrição**: Cria um usuário referenciando um perfil já existente. Substitua `ID_DO_PERFIL_EXISTENTE` pelo ID real de um perfil cadastrado.

---

## 3. Exemplos de Dados para Teste

### Usuários para criação:
```json
{
  "nome": "Ana Clara",
  "perfil": {
    "bio": "Designer UX/UI com 5 anos de experiência",
    "avatarUrl": "https://example.com/avatar/ana.jpg"
  }
}
```

```json
{
  "nome": "Carlos Eduardo",
  "perfil": {
    "bio": "Engenheiro de Software especializado em microserviços",
    "avatarUrl": "https://example.com/avatar/carlos.jpg"
  }
}
```

```json
{
  "nome": "Fernanda Lima",
  "perfil": {
    "bio": "Product Manager focada em produtos digitais",
    "avatarUrl": "https://example.com/avatar/fernanda.jpg"
  }
}
```

---

## 4. Fluxo de Testes Recomendado

1. **Primeiro**: Execute GET /usuarios para ver o estado inicial (provavelmente vazio)
2. **Segundo**: Crie alguns usuários com perfis usando POST /usuarios
3. **Terceiro**: Execute GET /usuarios novamente para ver os usuários criados
4. **Quarto**: Teste criar um usuário referenciando um perfil existente (use o ID retornado de um dos perfis criados)

---

## 5. Respostas Esperadas

### GET /usuarios (lista vazia):
```json
[]
```

### GET /usuarios (com dados):
```json
[
  {
    "id": "60a1b2c3d4e5f6789abcdef0",
    "nome": "João Silva",
    "perfil": null
  },
  {
    "id": "60a1b2c3d4e5f6789abcdef1",
    "nome": "Maria Santos",
    "perfil": {
      "id": "60a1b2c3d4e5f6789abcdef2",
      "bio": "Desenvolvedora Full Stack apaixonada por tecnologia",
      "avatarUrl": "https://example.com/avatar/maria.jpg"
    }
  }
]
```

### POST /usuarios (resposta de criação):
```json
{
  "id": "60a1b2c3d4e5f6789abcdef1",
  "nome": "Maria Santos",
  "perfil": {
    "id": "60a1b2c3d4e5f6789abcdef2",
    "bio": "Desenvolvedora Full Stack apaixonada por tecnologia",
    "avatarUrl": "https://example.com/avatar/maria.jpg"
  }
}
```

---

## 6. Possíveis Erros

### Erro 500 - Internal Server Error
- **Causa**: Problemas de conexão com MongoDB ou erro na aplicação
- **Verificar**: Se a aplicação está rodando e se o MongoDB está acessível

### Erro 400 - Bad Request
- **Causa**: JSON malformado ou dados inválidos
- **Verificar**: Estrutura do JSON e tipos de dados

---

## 7. Dicas para Teste

1. **Certifique-se** de que a aplicação Spring Boot está rodando (porta 8080)
2. **Use** a aba "Body" > "raw" > "JSON" no Postman para requisições POST
3. **Salve** os IDs retornados para usar em testes posteriores
4. **Observe** que o perfil é salvo automaticamente quando não tem ID
5. **Teste** diferentes cenários: usuário sem perfil, com perfil novo, com perfil existente