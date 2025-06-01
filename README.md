# Game Asset Manager

## Dev mode

The app can run in dev mode for easier REPL based development.
This dev mode is inspired by 
- [moon](https://github.com/damn/moon)
- [Clojure Workflow Reloaded](https://www.cognitect.com/blog/2013/06/04/clojure-workflow-reloaded)

Start dev mode using
```
$ bb dev
```

- [nREPL server](https://github.com/nrepl/nrepl) will be started with `.nrepl-port` file created.
- Any nREPL client can be used to interact with running app.
- `bb nrepl` can also be used to start a nREPL client
- After the nREPL server is started, the dev loop will start the app.
- The app data will be stored to `dev/system` atom defined in `dev/dev.clj` file.
- When the app is finished, dev loop will automatically tries to reload all the changes and restart the app. 
- This can be triggered from REPL by evaluating `(dev/stop!)`
- When there is any exception, app will print out the error and wait to be restarted
- Once changes are made, app can be restarted from REPL by evaluating `(dev/restart!)`

`user.clj` in the top level directory contains various useful forms for this process.


