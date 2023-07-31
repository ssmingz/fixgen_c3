class PlaceHold {
  public void execute() throws BuildException {
    TaskThread[] threads = new TaskThread[nestedTasks.size()];
    int threadNumber = 0;
    for (Enumeration e = nestedTasks.elements(); e.hasMoreElements(); threadNumber++) {
      Task nestedTask = ((Task) (e.nextElement()));
      threads[threadNumber] = new TaskThread(threadNumber, nestedTask);
    }
    for (int i = 0; i < threads.length; ++i) {
      threads[i].start();
    }
    for (int i = 0; i < threads.length; ++i) {
      try {
        threads[i].join();
      } catch (InterruptedException ie) {
      }
    }
    StringBuffer exceptionMessage = new StringBuffer();
    int numExceptions = 0;
    Throwable firstException = null;
    Location firstLocation = Location.UNKNOWN_LOCATION;
    for (int i = 0; i < threads.length; ++i) {
      Throwable t = threads[i].getException();
      if (t != null) {
        numExceptions++;
        if (firstException == null) {
          firstException = t;
        }
        if ((t instanceof BuildException) && (firstLocation == Location.UNKNOWN_LOCATION)) {
          firstLocation = ((BuildException) (t)).getLocation();
        }
        exceptionMessage.append(LINE_SEP);
        exceptionMessage.append(t.getMessage());
      }
    }
    if (numExceptions == 1) {
      if (firstException instanceof BuildException) {
        throw ((BuildException) (firstException));
      } else {
        throw new BuildException(firstException);
      }
    } else if (numExceptions > 1) {
      throw new BuildException(exceptionMessage.toString(), firstLocation);
    }
  }
}
