# 🏛️ Foro Hub API

Una API REST desarrollada con Spring Boot que replica el funcionamiento de un foro educativo, donde los usuarios pueden crear, consultar, actualizar y eliminar tópicos de discusión.

## 📋 Descripción del Proyecto

Foro Hub es una API que permite gestionar tópicos de un foro educativo, similar al utilizado en plataformas de aprendizaje. Los usuarios pueden interactuar con los tópicos realizando operaciones CRUD completas, todo protegido por un sistema de autenticación JWT.

## ✨ Funcionalidades

- **🔐 Autenticación**: Sistema de registro e inicio de sesión con JWT
- **📝 Gestión de Tópicos**: CRUD completo para tópicos
- **🛡️ Seguridad**: Protección de endpoints con Spring Security
- **📊 Persistencia**: Base de datos para almacenar información
- **🚀 API REST**: Implementación siguiendo mejores prácticas REST

### Operaciones CRUD de Tópicos

- ✅ **CREATE**: Crear un nuevo tópico
- 📖 **READ**: Mostrar todos los tópicos o uno específico  
- ✏️ **UPDATE**: Actualizar un tópico existente
- ❌ **DELETE**: Eliminar un tópico

## 🛠️ Tecnologías Utilizadas

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Security 6**
- **Spring Data JPA**
- **JWT (JSON Web Tokens)**
- **H2 Database** (para desarrollo)
- **Maven**
- **BCrypt** (para encriptación de contraseñas)

## 📁 Estructura del Proyecto

```
src/main/java/com/example/forohub/
├── controller/          # Controladores REST
├── dto/                # Data Transfer Objects
├── entity/             # Entidades JPA
├── repository/         # Repositorios de datos
├── security/           # Configuración de seguridad
├── service/           # Lógica de negocio
└── ForoHubApplication.java
```

## 🚀 Instalación y Configuración

### Prerrequisitos

- Java 17 o superior
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tu-usuario/foro-hub.git
   cd foro-hub
   ```

2. **Configurar base de datos** (application.properties)
   ```properties
   # Base de datos H2 (desarrollo)
   spring.datasource.url=jdbc:h2:mem:forohub
   spring.datasource.username=sa
   spring.datasource.password=
   spring.h2.console.enabled=true
   
   # JPA/Hibernate
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.jpa.hibernate.ddl-auto=create-drop
   spring.jpa.show-sql=true
   
   # JWT Secret
   jwt.secret=tu-clave-secreta-muy-larga-y-segura-de-al-menos-256-bits
   jwt.expiration=86400000
   ```

3. **Compilar y ejecutar**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Acceder a la aplicación**
   - API: http://localhost:8080
   - Console H2: http://localhost:8080/h2-console

## 📚 Uso de la API

### Autenticación

#### Registrar Usuario
```http
POST /auth/register
Content-Type: application/json

{
    "username": "usuario123",
    "password": "password123"
}
```

#### Iniciar Sesión
```http
POST /auth/login
Content-Type: application/json

{
    "username": "usuario123",
    "password": "password123"
}
```

**Respuesta:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Gestión de Tópicos

> **Nota**: Incluir el token JWT en el header `Authorization: Bearer {token}` para endpoints protegidos.

#### Crear Tópico
```http
POST /topicos
Authorization: Bearer {token}
Content-Type: application/json

{
    "titulo": "¿Cómo usar Spring Boot?",
    "mensaje": "Tengo dudas sobre la configuración inicial",
    "autor": "Juan Pérez", 
    "curso": "Spring Framework"
}
```

#### Listar Todos los Tópicos
```http
GET /topicos
```

#### Obtener Tópico por ID
```http
GET /topicos/{id}
```

#### Actualizar Tópico
```http
PUT /topicos/{id}
Authorization: Bearer {token}
Content-Type: application/json

{
    "titulo": "¿Cómo usar Spring Boot? - RESUELTO",
    "mensaje": "Ya encontré la solución, gracias por la ayuda",
    "autor": "Juan Pérez",
    "curso": "Spring Framework"
}
```

#### Eliminar Tópico
```http
DELETE /topicos/{id}
Authorization: Bearer {token}
```

## 🔒 Seguridad

- **Contraseñas**: Encriptadas con BCrypt
- **JWT**: Tokens con expiración configurable
- **Endpoints**: Protegidos según reglas de negocio
- **Validaciones**: Prevención de tópicos duplicados

## 📋 Validaciones Implementadas

- **Tópicos duplicados**: No se permiten tópicos con el mismo título y mensaje
- **Campos obligatorios**: Validación de campos requeridos
- **Autenticación**: Verificación de tokens válidos
- **Usuarios únicos**: No se permiten usernames duplicados

## 🧪 Testing

### Usando cURL

```bash
# Registrar usuario
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"test123"}'

# Login
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"test123"}'

# Crear tópico
curl -X POST http://localhost:8080/topicos \
  -H "Authorization: Bearer {tu-token}" \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Test","mensaje":"Mensaje de prueba","autor":"Test User","curso":"Spring"}'
```

### Usando Postman

1. Importar colección con los endpoints
2. Configurar variables de entorno para el token
3. Ejecutar requests en orden: Register → Login → CRUD Tópicos

## 🚀 Despliegue

### Para producción, configurar:

1. **Base de datos externa** (MySQL, PostgreSQL)
2. **Variables de entorno** para secretos
3. **HTTPS** para seguridad
4. **Logging** apropiado
5. **Monitoring** y métricas

## 🤝 Contribución

1. Fork el proyecto
2. Crear una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -m 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crear un Pull Request

## 📝 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 🙏 Agradecimientos

- Alura por el challenge y la oportunidad de aprendizaje
- Comunidad Spring Boot por la excelente documentación
- Todos los que contribuyeron con feedback y sugerencias

---

⭐ **¡Si te gustó el proyecto, no olvides darle una estrella!** ⭐
