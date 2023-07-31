class PlaceHold{
public void execute(Project project) throws BuildException {
    final String classname = javaCommand.getExecutable();
    AntClassLoader loader = null;
    try {
        if (sysProperties != null) {
            sysProperties.setSystem();
        }
        Class target = null;
        if (classpath == null) {
            target = Class.forName(classname);
        } else {
            loader = project.createClassLoader(classpath);
            loader.setParent(project.getCoreLoader());
            loader.setParentFirst(false);
            loader.addJavaLibraries();
            loader.setIsolated(true);
            loader.setThreadContextLoader();
            loader.forceLoadClass(classname);
            target = Class.forName(classname, true, loader);
        }
        main = target.getMethod("main", new Class[]{ String[].class });
        if (main == null) {
            throw new BuildException("Could not find main() method in " + classname);
        }
        if ((main.getModifiers() & Modifier.STATIC) == 0) {
            throw new BuildException(("main() method in " + classname) + " is not declared static");
        }
        if (timeout == null) {
            run();
        } else {
            thread = new Thread(this, "ExecuteJava");
            Task currentThreadTask = project.getThreadTask(Thread.currentThread());
            project.registerThreadTask(thread, currentThreadTask);
            thread.setDaemon(true);
            Watchdog w = new Watchdog(timeout.longValue());
            w.addTimeoutObserver(this);
            synchronized(this) {
                thread.start();
                w.start();
                try {
                    wait();
                } catch (InterruptedException e) {
                }
                if (timedOut) {
                    project.log("Timeout: sub-process interrupted", MSG_WARN);
                } else {
                    thread = null;
                    w.stop();
                }
            }
        }
        if (caught != null) {
            throw caught;
        }
    } catch (ClassNotFoundException e) {
        throw new BuildException(((("Could not find " + classname) + ".") + " Make sure you have it in your") + " classpath");
    } catch (BuildException e) {
        throw e;
    } catch (SecurityException e) {
        throw e;
    } catch (ThreadDeath e) {
        throw e;
    } catch (Throwable e) {
        throw new BuildException(e);
    } finally {
        if (loader != null) {
            loader.resetThreadContextLoader();
            loader.cleanup();
            loader = null;
        }
        if (sysProperties != null) {
            sysProperties.restoreSystem();
        }
    }
}
}