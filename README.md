# OnlineVotingSystem
Secure REST API for an Online Voting System using Spring Boot, Spring Security, and JPA/Hibernate. Implements unique multi-table authentication for Voters and Candidates with an isolated Super-Admin bypass layer.

# Online Voting System API 🗳️

A secure, robust, and scalable backend RESTful API built using **Spring Boot 3.x / 4.x** and **Java 21** designed to handle an automated electoral process. This system features an advanced stateless security matrix with independent access boundaries for Voters and Candidates, managed via a unified authentication engine.

---

## 🚀 Key Architectural Features

### 1. Dual-Table Dynamic Authentication
Unlike standard single-user database architectures, this system manages authentication across two entirely separate entities (**Voters** and **Candidates**). The custom `UserDetailsService` (`CustomeUserDetail`) dynamically queries both tables sequentially over a single authentication gate:
* First, it scans the `Voter` table safely via `VoterRepository`. If found, it assigns `ROLE_VOTER`.
* If not found, it falls back to the `Candidate` table via `CandidateRepository`. If found, it assigns `ROLE_CANDIDATE`.

### 2. High-Performance Hybrid Security Layer
* **Database-Bypass Super Admin:** Intercepts authentication requests at the topmost layer. If the incoming username matches a hardcoded system master email (`admin@voting.com`), it bypasses the physical MySQL databases entirely and generates a secure runtime `ROLE_ADMIN` principal using a live **BCrypt cryptographic hash function**.
* **Role-Based Access Control (RBAC):** Protects paths using rigorous Ant-style deep matchers to eliminate directory-traversal vulnerabilities:
  * `/api/admin/**` ──► Restricted exclusively to `ROLE_ADMIN`.
  * `/api/voter/**` ──► Restructured for authenticated accounts with `ROLE_VOTER`.
  * `/api/candidate/**` ──► Protected gate for profiles holding `ROLE_CANDIDATE`.
* **Stateless Tokenless Interactions:** Formulated with stateless HTTP Basic Auth and global CSRF deactivation, removing cookie tracking dependencies to allow seamless client testing in platforms like Postman.

### 3. Database & Persistence Performance
* Powered by **Hibernate 7 / JPA** and an enterprise-grade **HikariCP Connection Pool** to talk to a native **MySQL 8** engine.
* Automatic schema updates are active (`spring.jpa.hibernate.ddl-auto=update`), synchronizing structural code changes directly to the MySQL database tables at runtime.

---

## 🛠️ Tech Stack & Dependencies

* **Core Language:** Java 21 (JDK 21)
* **Framework Engine:** Spring Boot (v4.x.x / v3.x.x)
* **Security Layer:** Spring Security 6.x (HTTP Basic Auth & BCrypt Encryption)
* **Data Access:** Spring Data JPA & Hibernate 7
* **Database Connector:** MySQL Connector/J
* **Connection Pool:** HikariCP
* **Boilerplate Reduction:** Project Lombok (`@RequiredArgsConstructor`)

---

## 📂 Filter Chain Visual Journey

When an HTTP request hits the backend server from Postman, it journeys through this strict filter sequence before executing your controller logic:

```text
Postman Basic Auth Request
           │
           ▼
┌────────────────────────────────────────────────────────┐
│ Filter 1: CsrfFilter                                   │
│ └── Status: DISABLED (Skips token evaluation)           │
└──────────────────────────┬─────────────────────────────┘
                           │
                           ▼
┌────────────────────────────────────────────────────────┐
│ Filter 2: BasicAuthenticationFilter                    │
│ ├── 1. Extracts & Decodes Base64 plain-text strings    │
│ └── 2. Invokes CustomeUserDetail.loadUserByUsername()   │
└──────────────────────────┬─────────────────────────────┘
                           │ Authenticated Successfully!
                           ▼
┌────────────────────────────────────────────────────────┐
│ Filter 3: AuthorizationFilter                          │
│ └── Evaluates: Does user role match .hasRole("ADMIN")? │
└──────────────────────────┬─────────────────────────────┘
                           │ Access Granted!
                           ▼
             Target REST API Controller Router
