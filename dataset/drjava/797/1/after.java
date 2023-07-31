class PlaceHold {
  public void testDebugSourcepath()
      throws DebugException, BadLocationException, IOException, InterruptedException {
    if (printMessages) {
      System.out.println("----testDebugSourcePath----");
    }
    DebugTestListener debugListener =
        new DebugTestListener() {
          public void breakpointSet(Breakpoint bp) {
            breakpointSetCount++;
          }

          public void breakpointReached(Breakpoint bp) {
            synchronized (_notifierLock) {
              breakpointReachedCount++;
              if (printEvents) {
                System.out.println("breakpointReached " + breakpointReachedCount);
              }
              _notifyObject(_notifierLock);
            }
          }

          public void breakpointRemoved(Breakpoint bp) {
            breakpointRemovedCount++;
            if (printEvents) {
              System.out.println("breakpointRemoved " + breakpointRemovedCount);
            }
          }

          public void stepRequested() {
            stepRequestedCount++;
            if (printEvents) {
              System.out.println("stepRequested " + stepRequestedCount);
            }
          }

          public void currThreadSuspended() {
            synchronized (_notifierLock) {
              currThreadSuspendedCount++;
              if (printEvents) {
                System.out.println("threadSuspended " + currThreadSuspendedCount);
              }
              _notifyObject(_notifierLock);
            }
          }

          public void currThreadResumed() {
            currThreadResumedCount++;
            if (printEvents) {
              System.out.println("threadResumed " + currThreadResumedCount);
            }
          }

          public void currThreadDied() {
            synchronized (_notifierLock) {
              currThreadDiedCount++;
              if (printEvents) {
                System.out.println("threadDied " + currThreadDiedCount);
              }
              _notifyObject(_notifierLock);
            }
          }

          public void threadLocationUpdated(OpenDefinitionsDocument doc, int lineNumber) {
            synchronized (_notifierLock) {
              threadLocationUpdatedCount++;
              if (printEvents) {
                System.out.println("threadUpdated " + threadLocationUpdatedCount);
              }
              _notifyObject(_notifierLock);
            }
          }

          public void debuggerShutdown() {
            synchronized (_notifierLock) {
              debuggerShutdownCount++;
              if (printEvents) {
                System.out.println("debuggerShutdown " + debuggerShutdownCount);
              }
              _notifyObject(_notifierLock);
            }
          }

          public void debuggerStarted() {
            synchronized (_notifierLock) {
              debuggerStartedCount++;
              if (printEvents) {
                System.out.println("debuggerStarted " + debuggerStartedCount);
              }
              _notifyObject(_notifierLock);
            }
          }
        };
    File file2 = new File(_tempDir, "DrJavaDebugClass.java");
    OpenDefinitionsDocument doc = _doCompile(DEBUG_CLASS, file2);
    Vector<File> path = new Vector<File>();
    path.addElement(_tempDir);
    _debugManager.addListener(debugListener);
    synchronized (_notifierLock) {
      _startup();
      _waitForNotifies(1);
      _notifierLock.wait();
    }
    _debugManager.toggleBreakpoint(doc, DEBUG_CLASS.indexOf("bar();"), 4);
    synchronized (_notifierLock) {
      interpretIgnoreResult("new DrJavaDebugClass().foo()");
      _waitForNotifies(3);
      _notifierLock.wait();
    }
    debugListener.assertThreadLocationUpdatedCount(1);
    synchronized (_notifierLock) {
      _debugManager.step(STEP_INTO);
      _waitForNotifies(2);
      _notifierLock.wait();
    }
    debugListener.assertStepRequestedCount(1);
    debugListener.assertThreadLocationUpdatedCount(2);
    _model.closeFile(doc);
    debugListener.assertBreakpointRemovedCount(1);
    synchronized (_notifierLock) {
      _debugManager.step(STEP_OVER);
      _waitForNotifies(1);
      _notifierLock.wait();
    }
    debugListener.assertStepRequestedCount(2);
    debugListener.assertThreadLocationUpdatedCount(2);
    synchronized (_debugManager) {
      DrJava.getConfig().setSetting(DEBUG_SOURCEPATH, path);
    }
    synchronized (_notifierLock) {
      _debugManager.step(STEP_OVER);
      _waitForNotifies(2);
      _notifierLock.wait();
    }
    debugListener.assertStepRequestedCount(3);
    debugListener.assertThreadLocationUpdatedCount(3);
    if (printMessages) {
      System.out.println("Shutting down...");
    }
    synchronized (_notifierLock) {
      _debugManager.shutdown();
      _waitForNotifies(2);
      _notifierLock.wait();
    }
    debugListener.assertCurrThreadDiedCount(1);
    debugListener.assertDebuggerShutdownCount(1);
    if (printMessages) {
      System.out.println("Shut down.");
    }
    _debugManager.removeListener(debugListener);
  }
}
