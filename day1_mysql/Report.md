# *Gaming Club App*

## *Abstract*

This project aims to develop a *Membership and Game Management System* using *Spring Boot and MySQL* to streamline the operations of a gaming center. The system enables administrators to manage *memberships, games, recharges, transactions, and daily collections* through secure REST APIs. Members can be registered with a joining fee that initializes their balance, recharges can be tracked, and balances are automatically updated when games are played. The application also records daily collections and provides detailed member histories, including recharges and played games. Authentication ensures that only authorized administrators can access the system. This solution eliminates manual tracking, improves accuracy, and provides a scalable digital platform for efficient gaming center management.


## *SQL Tables - Overview:*


| *Table Name*   | *Fields (with constraints)*                                                                                                                                                                                     |
| ---------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| *members*      | id BIGINT PK AUTO_INCREMENT, name VARCHAR(100) NOT NULL, balance DOUBLE DEFAULT 0, phone VARCHAR(15) UNIQUE                                                                                               |
| *games*        | id BIGINT PK AUTO_INCREMENT, name VARCHAR(100) NOT NULL, price DOUBLE NOT NULL, description VARCHAR(255)                                                                                                  |
| *recharges*    | id BIGINT PK AUTO_INCREMENT, member_id BIGINT FK → members(id) ON DELETE CASCADE, amount DOUBLE NOT NULL, date_time DATETIME DEFAULT CURRENT_TIMESTAMP                                                    |
| *transactions* | id BIGINT PK AUTO_INCREMENT, member_id BIGINT FK → members(id) ON DELETE CASCADE, game_id BIGINT FK → games(id) ON DELETE CASCADE, amount DOUBLE NOT NULL, date_time DATETIME DEFAULT CURRENT_TIMESTAMP |
| *collections*  | id BIGINT PK AUTO_INCREMENT, date DATE NOT NULL, amount DOUBLE NOT NULL                                                                                                                                     |
| *admin\_users* (optional) | id BIGINT PK AUTO_INCREMENT, username VARCHAR(50) UNIQUE NOT NULL, password VARCHAR(255) NOT NULL                                                                                                           |

## *SQL Tables* ie *DDL for your schema*:

sql
CREATE TABLE members (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    balance DOUBLE DEFAULT 0,
    phone VARCHAR(15) UNIQUE
);

CREATE TABLE games (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE recharges (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT,
    amount DOUBLE NOT NULL,
    date_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE
);

CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT,
    game_id BIGINT,
    amount DOUBLE NOT NULL,
    date_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE,
    FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE
);

CREATE TABLE collections (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    amount DOUBLE NOT NULL
);


## Pages Design

### 1. Login Page


 ---------------------------------------------------
|                 GAMING CLUB APP                   |
|---------------------------------------------------|
|                                                   |
|                 +----------------+                |
|                 |   Username     |                |
|                 +----------------+                |
|                                                   |
|                 +----------------+                |
|                 |   Password     |                |
|                 +----------------+                |
|                                                   |
|                   [   Login   ]                   |
|                                                   |
|---------------------------------------------------|
|       © 2025 Gaming Club. All rights reserved.    |
 ---------------------------------------------------


### 2. Membership Page


 ---------------------------------------------------
|                 GAMING CLUB APP                   |
|---------------------------------------------------|
| Logo | [Membership] [Member] [Add Game] [Collections]   Admin |Logout|
|---------------------------------------------------|
|                                                   |
|               CREATE MEMBERSHIP                   |
|                                                   |
|   Name           : [__________________________]   |
|                                                   |
|   Phone          : [__________________________]   |
|                                                   |
|   Membership Fee : [__________]                   |
|                                                   |
|                     [ Create Membership ]         |
|                                                   |
|---------------------------------------------------|
|       © 2025 Gaming Club. All rights reserved.    |
 ---------------------------------------------------



### 3. Member Search Page


 ---------------------------------------------------
|                 GAMING CLUB APP                   |
|---------------------------------------------------|
| Logo | [Membership] [Member] [Add Game] [Collections]   Admin |Logout|
|---------------------------------------------------|
|                                                   |
|                  MEMBER SEARCH                    |
|                                                   |
|   Phone : [_____________________]   [ Search ]    |
|                                                   |
|                                                   |
|---------------------------------------------------|
|       © 2025 Gaming Club. All rights reserved.    |
 ---------------------------------------------------




### 4. Member Page (Tabbed Layout)


 ---------------------------------------------------
|                 GAMING CLUB APP                   |
|---------------------------------------------------|
| Logo | [Membership] [Member] [Add Game] [Collections]   Admin |Logout|
|---------------------------------------------------|
|                                                   |
|                 MEMBER DETAILS                    |
|                                                   |
|   Name    : John Doe                              |
|   Phone   : 9876543210                            |
|   Balance : ₹500                                  |
|                                                   |
|   [ Games ] | [ Recharge History ] | [ Played Games ] 
|---------------------------------------------------|

>>> Games Tab (Default) <<<

   List of Available Games:
   --------------------------------------------
   | Name        | Price   | Description       |
   |-------------|---------|------------------|
   | Chess       | ₹50     | 2 players needed | [ Play Game ]
   | Carrom      | ₹100    | 2–4 players      | [ Play Game ]
   | Foosball    | ₹150    | Multiple allowed | [ Play Game ]
   --------------------------------------------

---------------------------------------------------
>>> Recharge History Tab <<<

   Date/Time          | Amount
   -------------------------------
   2025-09-05 11:30   | ₹200
   2025-09-02 15:10   | ₹300
   -------------------------------

---------------------------------------------------
>>> Played Games History Tab <<<

   Date/Time          | Game      | Amount
   ------------------------------------------
   2025-09-06 19:45   | Chess     | ₹50
   2025-09-05 18:20   | Carrom    | ₹100
   2025-09-02 16:00   | Foosball  | ₹150
   ------------------------------------------
   (Latest First Order)

 ---------------------------------------------------
|       © 2025 Gaming Club. All rights reserved.    |
 ---------------------------------------------------


### 5. Add Game Page


 ---------------------------------------------------
|                 GAMING CLUB APP                   |
|---------------------------------------------------|
| Logo | [Membership] [Member] [Add Game] [Collections]   Admin |Logout|
|---------------------------------------------------|
|                                                   |
|                   ADD GAME                        |
|                                                   |
|   Game Name      : [__________________________]   |
|                                                   |
|   Price (₹)      : [__________]                   |
|                                                   |
|   Description    : [__________________________]   |
|                                                   |
|   Min Players    : [___]     Multiple Allowed : [✔] 
|                                                   |
|                     [   Add Game   ]              |
|                                                   |
|---------------------------------------------------|
|       © 2025 Gaming Club. All rights reserved.    |
 ---------------------------------------------------


### 6. Collections Page

 ---------------------------------------------------
|                 GAMING CLUB APP                   |
|---------------------------------------------------|
| Logo | [Membership] [Member] [Add Game] [Collections]   Admin |Logout|
|---------------------------------------------------|
   Date: [__________]   [   Search  ] (Optional)


            Recharge Collection on 07-09-2025        

   ---------------------------------
   | Membr       | Recharge         |
   |-------------|------------------|
   | Suman       | ₹50              | 
   | Sujan       | ₹100             | 
   | Rakshith    | ₹150             | 
   ----------------------------------
   Total           ₹300 

 ---------------------------------------------------
|       © 2025 Gaming Club. All rights reserved.    |
 ---------------------------------------------------


## 📌 API Endpoints with Request & Response

| *Method* | *Endpoint*             | *Request Body*                                                      | *Sample Response Body*                                                                                                                                                                                                                                                                                                                                                                | *Description*                                                             |
| ---------- | ------------------------ | --------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| *POST*   | /auth                  | `json { "username": "admin", "password": "admin" } `                  | `json "fake-jwt-token" `                                                                                                                                                                                                                                                                                                                                                                | Login (currently returns a dummy JWT token)                                 |
| *POST*   | /members               | `json { "name": "John", "phone": "9876543210", "fee": 500 } `         | `json { "id": 1, "name": "John", "balance": 500.0, "phone": "9876543210" } `                                                                                                                                                                                                                                                                                                            | Create new membership                                                       |
| *POST*   | /members/search        | `json { "phone": "9876543210" } `                                     | `json { "member": { "id": 1, "name": "John", "phone": "9876543210", "balance": 450.0 }, "recharge_history": [ { "id": 1, "amount": 500.0, "dateTime": "2025-09-07T09:30:00" } ], "games": [ { "id": 2, "name": "Chess", "price": 50.0, "description": "Board game" } ], "played_history": [ { "id": 3, "date_time": "2025-09-07T10:00:00", "game_name": "Chess", "amount": 50.0 } ] } ` | Search member (returns profile, recharges, available games, played history) |
| *POST*   | /game                  | `json { "name": "Chess", "price": 50, "description": "Board game" } ` | `json { "id": 2, "name": "Chess", "price": 50.0, "description": "Board game" } `                                                                                                                                                                                                                                                                                                        | Add new game                                                                |
| *POST*   | /play                  | `json { "member_id": 1, "game_id": 2 } `                              | `json "Game played successfully!" ` or `json "Insufficient balance!" `                                                                                                                                                                                                                                                                                                                  | Play game (deducts balance & creates transaction)                           |
| *GET*    | /collection/2025-09-07 | (no body, path variable = date)                                     | `json [ { "transaction_id": 1, "member": "John", "recharge_amount": 500.0 } ] `                                                                                                                                                                                                                                                                                                         | Get recharge transactions for a specific date                               |

## Software Requirements:
    - Java 21 + env Path
    - STS + Desktop shortcut
    - Maven + env Path 

# *clean Spring Boot app code structure* (using JPA + MySQL) covering:

* Entities (@Entity classes for each table)
* Repositories (JpaRepository)
* Services (business logic)
* Controllers (your REST APIs)

Here’s a *working starter project* ⬇

---

## 📂 Project Structure


src/main/java/com/example/gameapp
 ├── controller
 │     ├── AuthController.java
 │     ├── MemberController.java
 │     ├── GameController.java
 │     └── CollectionController.java
 ├── entity
 │     ├── Member.java
 │     ├── Game.java
 │     ├── Recharge.java
 │     ├── Collection.java
 │     └── Transaction.java
 ├── repository
 │     ├── MemberRepository.java
 │     ├── GameRepository.java
 │     ├── RechargeRepository.java
 │     ├── CollectionRepository.java
 │     └── TransactionRepository.java
 └── GameAppApplication.java


---

## ⚙ application.properties

properties
spring.datasource.url=jdbc:mysql://localhost:3306/gameapp
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
# to make mysql to work 
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver


---

## 🏗 Entities

java
// Member.java
package com.example.gameapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double balance = 0.0;
    private String phone;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Recharge> recharges;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}


java
// Game.java
package com.example.gameapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Game {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private String description;
}


java
// Recharge.java
package com.example.gameapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Recharge {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private LocalDateTime dateTime = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;
}


java
// Transaction.java
package com.example.gameapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private LocalDateTime dateTime = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;
}


java
// Collection.java
package com.example.gameapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Collection {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date = LocalDateTime.now();
    private Double amount;
}


---

## 📦 Repositories

java
package com.example.gameapp.repository;

import com.example.gameapp.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByPhone(String phone);
}

public interface GameRepository extends JpaRepository<Game, Long> {}

public interface RechargeRepository extends JpaRepository<Recharge, Long> {
    List<Recharge> findByMemberId(Long memberId);
}

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByMemberId(Long memberId);
    List<Transaction> findByDateTimeBetween(LocalDate start, LocalDate end);
}

public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Optional<Collection> findByDate(LocalDate date);
}


---

## 🚀 Controllers (Endpoints)

java
// AuthController.java
package com.example.gameapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping
    public String login(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");
        // TODO: replace with real JWT
        if(username.equals("admin") && password.equals("admin")) {
            return "fake-jwt-token";
        }
        return "Invalid credentials";
    }
}


java
// MemberController.java
package com.example.gameapp.controller;

import com.example.gameapp.entity.*;
import com.example.gameapp.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberRepository memberRepo;
    private final RechargeRepository rechargeRepo;
    private final GameRepository gameRepo;
    private final TransactionRepository txRepo;

    public MemberController(MemberRepository m, RechargeRepository r, GameRepository g, TransactionRepository t) {
        this.memberRepo = m; this.rechargeRepo = r; this.gameRepo = g; this.txRepo = t;
    }

    @PostMapping
    @Transactional
    public Member createMember(@RequestBody Map<String, Object> payload) {
        // 1. Create Member
        Member m = new Member();
        m.setName((String) payload.get("name"));
        m.setPhone((String) payload.get("phone"));

        Double fee = Double.valueOf(payload.get("fee").toString());
        m.setBalance(fee); // set initial balance = joining fee
        Member savedMember = memberRepo.save(m);

        // 2. Add entry in Recharge table
        Recharge recharge = new Recharge();
        recharge.setMember(savedMember);
        recharge.setAmount(fee);
        recharge.setDate(LocalDateTime.now());
        rechargeRepo.save(recharge);

        // 3. Update Collection table
        Collection collection = collectionRepo.findByDate(LocalDate.now())
                .orElse(new Collection(LocalDate.now(), 0.0));

        collection.setAmount(collection.getAmount() + fee);
        collectionRepo.save(collection);

        return savedMember;
    }


    @PostMapping("/search")
    public Map<String, Object> search(@RequestBody Map<String, String> payload) {
        Member m = memberRepo.findByPhone(payload.get("phone"));
        Map<String, Object> resp = new HashMap<>();
        resp.put("member", m);
        resp.put("recharge_history", rechargeRepo.findByMemberId(m.getId()));
        resp.put("games", gameRepo.findAll());
        List<Map<String,Object>> played = new ArrayList<>();
        for(Transaction tx : txRepo.findByMemberId(m.getId())) {
            Map<String,Object> row = new HashMap<>();
            row.put("id", tx.getId());
            row.put("date_time", tx.getDateTime());
            row.put("game_name", tx.getGame().getName());
            row.put("amount", tx.getAmount());
            played.add(row);
        }
        resp.put("played_history", played);
        return resp;
    }
}


java
// GameController.java
package com.example.gameapp.controller;

import com.example.gameapp.entity.*;
import com.example.gameapp.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class GameController {
    private final GameRepository gameRepo;
    private final MemberRepository memberRepo;
    private final TransactionRepository txRepo;

    public GameController(GameRepository g, MemberRepository m, TransactionRepository t) {
        this.gameRepo = g; this.memberRepo = m; this.txRepo = t;
    }

    @PostMapping("/game")
    public Game addGame(@RequestBody Game game) {
        return gameRepo.save(game);
    }

    @PostMapping("/play")
    @Transactional
    public String play(@RequestBody Map<String, Long> payload) {
        Long memberId = payload.get("member_id");
        Long gameId = payload.get("game_id");

        Member member = memberRepo.findById(memberId).orElseThrow();
        Game game = gameRepo.findById(gameId).orElseThrow();

        if(member.getBalance() < game.getPrice()) {
            return "Insufficient balance!";
        }

        member.setBalance(member.getBalance() - game.getPrice());
        memberRepo.save(member);

        Transaction tx = new Transaction();
        tx.setMember(member);
        tx.setGame(game);
        tx.setAmount(game.getPrice());
        txRepo.save(tx);

        return "Game played successfully!";
    }
}


java
// CollectionController.java
package com.example.gameapp.controller;

import com.example.gameapp.entity.*;
import com.example.gameapp.repository.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/collection")
public class CollectionController {
    private final CollectionRepository collectionRepo;
    private final RechargeRepository rechargeRepo;

    public CollectionController(CollectionRepository c, RechargeRepository r) {
        this.collectionRepo = c; this.rechargeRepo = r;
    }

    @GetMapping("/{date}")
    public List<Map<String,Object>> getCollection(@PathVariable String date) {
        LocalDate d = LocalDate.parse(date);
        List<Map<String,Object>> resp = new ArrayList<>();

        for(Recharge r : rechargeRepo.findAll()) {
            if(r.getDateTime().toLocalDate().equals(d)) {
                Map<String,Object> row = new HashMap<>();
                row.put("transaction_id", r.getId());
                row.put("member", r.getMember().getName());
                row.put("recharge_amount", r.getAmount());
                resp.add(row);
            }
        }
        return resp;
    }
}
