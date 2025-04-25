## 🧠 What is a DAO?

**DAO = Data Access Object**

> A **DAO class** is responsible for handling all interactions with the **database** for a specific model/entity.

In your case:

* `VehicleDAO` manages saving/fetching **vehicles**
* `FuelDataDAO` manages **fuel log** entries
* `EngineDataDAO` manages **engine log** entries

You do **NOT** put database logic directly in your `Main` or model classes — you isolate it in a DAO.

---

## ✅ Why DAO Classes Are Important


| Benefit             | Why it matters                                                                    |
| ------------------- | --------------------------------------------------------------------------------- |
| 🔁 Reuse            | You can call the same DAO method in multiple places                               |
| 🚫 Clean separation | Keeps DB logic separate from UI or main logic                                     |
| 🧪 Easier testing   | You can test DAO independently from the rest of the app                           |
| 🧱 Scalable         | You can later replace Hibernate or DB logic without touching the rest of your app |


## 📦 What Does a DAO Class Contain?

Let’s take `VehicleDAO` as an example.

### It usually contains methods like:

VehicleDAO

**Purpose**: Handles database operations related to the `Vehicle` entity.
**Methods**:

- `saveVehicle(Vehicle vehicle)`Saves a new vehicle to the database.
- `getAllVehicles()`Retrieves all vehicle records from the database.
- `getVehicleById(int id)`Fetches a single vehicle by its primary key.
- *(Optional)* `deleteVehicle(int id)`
  Deletes a vehicle based on its ID.

---

### 🔹 FuelDataDAO

**Purpose**: Manages data access for `FuelData` entries.

**Methods**:

- `saveFuelData(FuelData fuelData)`Inserts a fuel log record into the database.
- `getFuelDataByVehicleId(int vehicleId)`Returns all fuel logs for a given vehicle.
- *(Optional)* `getFuelEfficiencyStats(int vehicleId)`
  Computes and returns fuel efficiency (e.g., distance/liters).

---

### 🔹 EngineDataDAO

**Purpose**: Provides access to engine performance data via the `EngineData` entity.

**Methods**:

- `saveEngineData(EngineData engineData)`Stores a new engine data entry in the database.
- `getEngineDataByVehicleId(int vehicleId)`Retrieves all engine logs for a specific vehicle.
- *(Optional)* `getAverageRPM(int vehicleId)`
  Calculates and returns average RPM readings.

---

### 🛠️ DAO Implementation Pattern

Each DAO method follows a common Hibernate session pattern:

1. Open a Hibernate session using `HibernateUtil.getSessionFactory().openSession()`
2. Begin a transaction with `session.beginTransaction()`
3. Execute the database operation (`save`, `get`, `createQuery`, etc.)
4. Commit the transaction using `tx.commit()`
5. Close the session

This ensures proper transaction handling and connection management.

---

### ✅ Benefits of Using DAO Pattern

- 🔁 Code reuse
- 🔧 Centralized data logic
- 🚫 Separation of concerns
- 🧪 Easier testing and debugging
- 🧱 Scalable and maintainable architecture

---
