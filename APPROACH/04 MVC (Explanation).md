## 🧱 Understanding the MVC Structure You’re Working With

Your project is being built using a loose version of **MVC (Model–View–Controller)** — this is a design pattern that keeps code organized.


|   Layer   |    Folder    |                                Role                                |
| :--------: | :----------: | :----------------------------------------------------------------: |
|   Model   |  `model/`  |     Represents data (like blueprints for your database tables)     |
|    DAO    |   `dao/`   | Handles talking to the database (save, read, update, delete data) |
| Controller | `servlet/` | Handles requests from the browser and connects frontend to backend |




## 📁 Let's Break It Down Folder by Folder

---

### 🔹 `model/` — **Model Layer**

 **📌 Purpose** : These are **plain Java classes** (POJOs) that represent your **real-world data** (vehicles, fuel logs, engine logs).

In Hibernate, they are called  **entities** .

Think of them like blueprints or templates for rows in your database.

#### Example Classes:

* `Vehicle.java` → represents a car
* `FuelData.java` → represents a fuel usage entry
* `EngineData.java` → represents engine sensor data

#### What They Contain:

* **Attributes** like `make`, `model`, `year`
* **Getters/Setters** for each attribute
* **Annotations** like `@Entity`, `@Table`, etc. (for Hibernate to map them to DB tables)

#### Analogy:

> Like a **form template** at a hospital — it defines what data is collected (name, age, symptoms), but not the actual data itself.



---


### 🔸 `dao/` — **Data Access Layer (DAO = Data Access Object)**

 **📌 Purpose** : These classes contain logic for **talking to your database** — using Hibernate to:

* Save new data
* Fetch data
* Update or delete rows

You keep this logic  **separate from your business logic** , so everything stays clean and maintainable.

#### Example Classes:

* `VehicleDAO.java`
* `FuelDataDAO.java`

#### What They Contain:

* Methods like:
  * `saveVehicle(Vehicle v)`
  * `getAllVehicles()`
  * `getFuelEfficiency(int vehicleId)`

They use Hibernate methods like `session.save()`, `session.createQuery()`, etc. behind the scenes.

#### Analogy:

> Think of DAO like a **waiter at a restaurant** — it takes your request (save this vehicle), goes to the kitchen (database), brings back the result.



---


### 🔺 `servlet/` — **Controller Layer**

 **📌 Purpose** : These are your **Servlets** — Java classes that **handle browser requests** (like when someone clicks a button or submits a form).

They act as the  **middleman** :

* Receives the request (e.g., "add a new vehicle")
* Talks to the DAO to do backend work
* Forwards to a JSP to show the result

#### Example Classes:

* `AddVehicleServlet.java`
* `DashboardServlet.java`
* `FuelDataServlet.java`

#### What They Contain:

* `doGet()` → handles HTTP GET requests
* `doPost()` → handles form submissions (POST)

They use methods like `request.getParameter()`, `request.setAttribute()`, `response.sendRedirect()`.

#### Analogy:

> A Servlet is like a **receptionist** — receives your request, does the paperwork (calls DAO), then forwards you to the right room (JSP page).
