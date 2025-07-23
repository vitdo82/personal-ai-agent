# Personal AI Agent Platform

This project is a personal AI agent platform featuring:
- **Backend REST API** (Spring Boot) for quizzes, users, and AI assistants
- **Web Admin App** (React + Vite) for quiz management and administration
- **Postgres**, **Ollama**, and **Kafka** integration via Docker Compose

---

## Prerequisites
- [Docker](https://www.docker.com/)
- [Java 21+](https://adoptopenjdk.net/)
- [Node.js 18+/npm] (for frontend)
- [Podman]
    Installation:
    ```sh
    export DOCKER_HOST="unix:///Users/$USER/.local/share/containers/podman/machine/podman.sock"
    ```
    ```sh
    podman machine ssh -- systemctl --user enable --now podman.socket
    ```

---

## Setup & Build Instructions

### 1. Start Dependencies
Run Compose to start Postgres, Ollama, and Kafka:

* Docker
```sh
docker-compose up -d
```

* Podman
```sh
podman compose --file compose.yaml up
```

### 2. Backend (service-api)
Build and run the Spring Boot backend:

```sh
./gradlew service-api:build
./gradlew service-api:bootRun
```

- API docs: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### 3. Frontend (web-admin-app)
In a new terminal:

```sh
cd web-admin-app
npm install
npm run dev
```

- Web app: [http://localhost:5173/](http://localhost:5173/) (default Vite port)

---

## Features
- Quiz management (create, list, answer quizzes)
- User management
- AI-powered text assistance (correction, translation)
- REST API with Swagger UI
- Modern React admin interface

---

## Development Notes
- See `service-api/README.md` for backend tech references
- See `web-admin-app/README.md` for frontend tech notes

---

## License
MIT or as specified in project
