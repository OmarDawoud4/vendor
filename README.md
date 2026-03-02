# Ecommerce Backend

A backend API for an e-commerce platform, built with Spring Boot and Java 21.

## What It Does

This is the server-side of an online store. It handles:

- **User Accounts** — Register, log in, and manage users with JWT-based authentication.
- **Product Catalog** — Browse products, categories, and product variants (like sizes or colors). Admins can create and manage catalog items.
- **Shopping Cart** — Add items to a cart, view cart contents, and manage quantities.

## Tech Stack

| Layer        | Technology             |
|--------------|------------------------|
| Framework    | Spring Boot 4          |
| Language     | Java 21                |
| Database     | PostgreSQL 16          |
| Caching      | Redis 7                |
| Auth         | JWT (jjwt)             |
| Migrations   | Flyway                 |
| Build Tool   | Maven                  |

## Project Structure

```
src/main/java/com/ecommerce/backend/
├── auth/           # Login, registration, user entity, JWT auth
├── cart/           # Shopping cart and cart items
├── catalog/        # Products, categories, variants (+ admin endpoints)
└── common/
    ├── config/     # App and Redis configuration
    └── security/   # JWT filter, JWT service, Spring Security config
```

## Getting Started

### Prerequisites

- Java 21
- Docker & Docker Compose

### 1. Start the databases

```bash
docker compose up -d
```

This spins up PostgreSQL and Redis containers.

### 2. Run the app

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

Flyway will automatically run all database migrations on startup.

## API Overview

| Method | Endpoint                        | Description            | Auth Required |
|--------|---------------------------------|------------------------|---------------|
| POST   | `/api/auth/register`            | Create a new account   | No            |
| POST   | `/api/auth/login`               | Log in, get JWT token  | No            |
| GET    | `/api/catalog/products`         | Browse products        | No            |
| GET    | `/api/catalog/categories`       | Browse categories      | No            |
| POST   | `/api/admin/catalog/products`   | Create a product       | Admin         |
| POST   | `/api/admin/catalog/categories` | Create a category      | Admin         |
| GET    | `/api/cart`                     | View your cart         | Yes           |
| POST   | `/api/cart/items`               | Add item to cart       | Yes           |

## Configuration

Key settings live in `src/main/resources/application.yaml`:

- **Database** — Postgres connection on port 5432
- **Redis** — Cache connection on port 6379
- **JWT** — Token secret and 24-hour expiration
- **Actuator** — Health, info, and metrics endpoints exposed at `/actuator`
