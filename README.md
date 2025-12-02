# RedisUsingJava
Redis implementation in Java
A lightweight, Redis-like in-memory data store built completely in Java, supporting:

✔ Key-Value Store
✔ Lists
✔ Sets
✔ TTL (expire)
✔ Persistence (RDB-style snapshot)
✔ RESP3 Protocol
✔ Pub/Sub Messaging
✔ Multi-Client Handling (Threaded Server)
Features Implemented
1. Key-Value Store
Supports basic Redis commands:
SET key value
GET key
DEL key
EXISTS key
APPEND key value
INCR key
DECR key
2. Lists
Implemented using Java LinkedList:
LPUSH key value
LPOP key
LLEN key
3. Sets
Implemented using Java HashSet:
SADD key value
SREM key value
SMEMBERS key
4. TTL / Expiry
EXPIRE key seconds
TTL key
PERSIST key
Keys automatically delete when expired.
5. RDB-Style Persistence
A snapshot of all data structures is saved to a file.
Commands:
SAVE → save complete snapshot
PDEL → clear persistence file
Data is restored on server startup.
6. RESP3 Protocol
Server responses follow RESP3 format:
Simple strings
Integers
Bulk strings
Arrays
Booleans
Errors
7. Publish / Subscribe
Real-time messaging system:
SUB channel → subscribe to channel
PUBLISH channel message → broadcast message
Supports multi-client using threading.
8. Multithreaded Server
Each client runs in its own thread using:
Thread class
Shared in-memory data
Thread-safe operations
Project Structure
RedisUsingJava/
│
├── red/
│   ├── server/            → Multi-threaded server
│   ├── QueryProcesser/    → Command parser + executor
│   ├── Model/             → DTOs, Subscriber model
│   ├── DataBase/          → Key, List, Set implementations
│   ├── Persistence/       → RDB snapshot logic
│   ├── Resp3Pro/          → RESP3 encoder
│   ├── pub_Sub/           → Publish/Subscribe system
│
├── client/                → Java client CLI
└── README.md
How to Run the Server
Compile
javac red/**/*.java
Run
java red.server.Server
How to Run the Client
javac server/*.java
java server.Server
 Example Usage
Store and retrieve values:
SET name priyanshu
GET name
Lists:
LPUSH mylist A
LPUSH mylist B
LPOP mylist
Sets:
SADD tags java
SADD tags redis
SMEMBERS tags
TTL:
SET a 100
EXPIRE a 5
TTL a
Persistence:
SAVE
PDEL
Pub-Sub:
Client 1:
SUB news
Client 2:
PUBLISH news "Hello World!"
Future Improvements
AOF (Append Only File) persistence
Lua scripting
Cluster mode
Authentication
Memory eviction policies (LRU, LFU)

Author
Priyanshu Singh
A Redis clone built as a college project to understand:
Networking
OS fundamentals
In-memory database design
Concurrency
Protocol design
<img width="1440" height="900" alt="Screenshot 2025-12-02 at 9 51 37 AM" src="https://github.com/user-attachments/assets/ee6109d5-95ef-494a-bebe-f87367c9282f" />
<img width="1440" height="900" alt="Screenshot 2025-12-02 at 9 51 25 AM" src="https://github.com/user-attachments/assets/98393d1a-98bd-457e-bb97-f86f81aa09ee" />
