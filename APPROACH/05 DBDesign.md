## 🎯 Your Use Case Reminder:

You’re analyzing  **vehicle data** , like:

* Fuel consumption over time
* Engine behavior (RPM, temperature)
* Environmental impact

So the **Engine** and **Fuel** tables should not represent *engine models* or  *fuel types* , but rather **sensor/log data entries** tied to a vehicle over time.


## ✅ Correct Data Modeling Approach

### 🔹 `Vehicle` table – the main entity

```pgsql
Table Vehicle {
  id integer [primary key]
  manufacturer varchar
  model varchar
  fuel_type varchar
  year integer
  type varchar  // (e.g., sedan, SUV)
}
```


* Each row = 1 car
* This table holds the **static metadata** about the car

---

### 🔸 `FuelData` table – dynamic fuel usage logs

```pgsql
Table FuelData {
  id integer [primary key]
  vehicle_id integer [ref: > Vehicle.id]
  liters_used decimal
  distance_km decimal
  timestamp datetime
}

```

* Each row = 1 fuel log (e.g., a trip or a refuel entry)
* You can calculate  **fuel efficiency** : `distance_km / liters_used`

---

### 🔸 `EngineData` table – dynamic engine behavior logs

```pgsql
Table EngineData {
  id integer [primary key]
  vehicle_id integer [ref: > Vehicle.id]
  rpm integer
  temperature decimal
  timestamp datetime
}

```


* Each row = 1 engine snapshot (from sensors or logs)
* You can analyze:

  * Avg RPM
  * Max/min engine temp
  * Idle vs high-performance behavior
