(ns pedestal-rest.db.core-test
  (:require [pedestal-rest.db.core :refer :all]
            [clojure.test :refer :all]
            [clojure.java.jdbc :as jdbc]))



(is (get-user "david"))
