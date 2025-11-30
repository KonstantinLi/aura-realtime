<h1 align="center">AURA Realtime Service</h1>

<p align="center">

<img alt="GitHub issues" src="https://img.shields.io/github/issues-raw/KonstantinLi/aura-realtime">
<img alt="GitHub watchers" src="https://img.shields.io/github/watchers/KonstantinLi/aura-realtime">
<img alt="GitHub contributors" src="https://img.shields.io/github/contributors/KonstantinLi/aura-realtime">
<img alt="GitHub labels" src="https://img.shields.io/github/labels/KonstantinLi/aura-realtime/help%20wanted">
<img alt="GitHub labels" src="https://img.shields.io/github/labels/KonstantinLi/aura-realtime/invalid">
<img alt="GitHub labels" src="https://img.shields.io/github/labels/KonstantinLi/aura-realtime/bug">

</p>

**AURA Realtime Service** is a reactive microservice responsible for processing and streaming real-time events to clients. It integrates with Kafka, Redis, external APIs, and provides SSE and WebSocket endpoints for low-latency updates. This service is part of a high-availability ecosystem including `aura-auth` (authentication) and `aura-risk` (risk evaluation).

---

## Features

* **Reactive Event Streaming**: Uses Spring WebFlux, Reactor Kafka, and Redis Sinks to stream events in real time.
* **Front-end Integration**: Supports Server-Sent Events (SSE) and WebSocket connections for live dashboards.
* **Event Replay**: Manual replay of historical events via REST API.
* **ACL and Security Filters**: Filters events based on user/device permissions, integrating JWT tokens from `aura-auth`.
* **External API Integration**: Aggregates data from third-party sources such as:

    * `alerts.in.ua` for real-time alerts
    * Earthquake API
    * Weather API
    * Video surveillance cameras (location-based access)
* **Caching**: Redis caching for device sessions, event context, and replay data.
* **Observability**: Metrics for throughput, latency, and delivery reliability.

---

## Architecture

```
Kafka Topics (aura-risk, external APIs)
       │
       ▼
Reactive Kafka Consumer (stream ingestion)
       │
       ▼
EventProcessor (filtering & fan-out)
       │
       ├─▶ Redis Cache (event context & replay)
       │
       ├─▶ SSE / WebSocket Subscribers
       │
       └─▶ ReplayService (manual replay)
```

* **Config**: Kafka, Redis, WebFlux, CORS, and application properties
* **Security**: ACL filters, access control, JWT integration
* **DTOs**: Transport objects for events, devices, locations, replay requests
* **Kafka**: Reactive Kafka consumer/producer with mapping and retry
* **Stream**: Event sinks, fan-out, ACL-based filtering, metrics
* **API**: SSE, WebSocket, REST controllers, external API connectors

---

## Configuration

All configurable properties are in `application.yml`, including:

* Kafka bootstrap servers and topics
* Redis host and TTLs
* SSE/WebSocket buffer sizes
* Replay retention settings

---

## Observability

* Metrics via Micrometer/Prometheus
* Logs for event processing and fan-out delivery
* Health checks for Kafka, Redis, and external API integrations

---

## Notes

* This service assumes authentication is handled externally by `aura-auth`.
* Risk evaluation and step-up authentication integration is provided by `aura-risk`.