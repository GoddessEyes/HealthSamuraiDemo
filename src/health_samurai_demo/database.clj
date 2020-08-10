(ns health-samurai-demo.database
  (:require [clojure.java.jdbc :as jdbc]))

(def db {:subprotocol "postgresql"
         :subname "//localhost:5432/demo"
         :user "demo"
         :password "demo"
         :zeroDateTimeBehaviour "convertToNull"})

(defn insertPatient [patient]
  (jdbc/insert! db :patient {:date_birth (java.time.LocalDate/parse (get patient :date_birth))
                             :full_name  (get patient :full_name)
                             :address  (get patient :address)
                             :oms  (get patient :oms)
                             :gender (get patient :gender)}))

(defn allPatients []
  (jdbc/query db
              ["SELECT * FROM patient"]))

(defn deletePatient [patient_id]
  (jdbc/delete! db :patient ["id = ?" (get patient_id :id)]))