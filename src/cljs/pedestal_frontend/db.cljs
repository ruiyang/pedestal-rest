(ns pedestal-frontend.db
  (:require [alandipert.storage-atom :refer [local-storage]]))

(def default-db
  {})

(def local-store (local-storage (atom {}) :local-store))
