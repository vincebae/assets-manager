(ns my.example
  (:import
   [com.badlogic.gdx ApplicationAdapter Gdx]
   [com.badlogic.gdx.backends.lwjgl3 Lwjgl3Application Lwjgl3ApplicationConfiguration]
   [com.badlogic.gdx.graphics GL20]
   [com.badlogic.gdx.scenes.scene2d Stage]
   [com.badlogic.gdx.scenes.scene2d.ui Skin Label TextButton Table]
   [com.badlogic.gdx.scenes.scene2d.utils ChangeListener]
   [com.badlogic.gdx.utils.viewport ScreenViewport]))

(def ^:private ^:const skin-dir "assets/default/")

(defn- create-ui
  [stage]
  (let [skin (Skin. (.local Gdx/files (str skin-dir "uiskin.json")))
        table (Table.)
        initial-label (Label. "Hello from Clojure!" skin)
        btn (TextButton. "Click Me" skin)]
     ;; Layout setup
    (.setFillParent table true)
    
    ;; Add initial label
    (doto table
      (.add initial-label)
      (.row))
    
    ;; Add button and listener
    (doto table
     (.add btn)
     (.row))
    
    (.addListener btn
     (proxy [ChangeListener] []
      (changed [event actor]
       (let [new-label (Label. "Dynamically added label!" skin)]
        (doto table
         (.add new-label)
         (.row)
         (.pack))))))

    ;; Attach to stage
    (.addActor @stage table)))

(defn- create-app
  [{:keys [stage state]}]

  (proxy [ApplicationAdapter] []
    (create []
      (reset! stage (Stage. (ScreenViewport.)))
      (.setInputProcessor Gdx/input @stage)
      (create-ui stage))

    (render []
      (when (= (:mode @state) :exiting)
        (println "Shutting down application...")
        (.exit Gdx/app))

      (.glClearColor Gdx/gl 0.2 0.2 0.25 1)
      (.glClear Gdx/gl GL20/GL_COLOR_BUFFER_BIT)
      (.act @stage (.getDeltaTime Gdx/graphics))
      (.draw @stage))

    (dispose []
     (.dispose @stage))))

(defn- create-config
  [config]
  (let [app-config (Lwjgl3ApplicationConfiguration.)
        {:keys [title w h]} config]
    (doto app-config
     (.setTitle title)
     (.setWindowedMode w h))
    app-config))

(defn init
  []
  {:config {:title "Clojure LibGDX Scene2D" :w 600 :h 400}
   :state (atom {})
   :stage (atom nil)})

(defn start
  [system]
  (Lwjgl3Application. (create-app system) (create-config (:config system))))

(defn stop
  [system]
  (swap! (:state system) assoc :mode :exiting))
