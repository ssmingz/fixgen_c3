class PlaceHold {
  public synchronized void complete() throws IOException {
    System.out.flush();
    System.err.flush();
    if (inputStream != null) {
      inputStream.close();
    }
    outputStream.flush();
    outputStream.close();
    errorStream.flush();
    errorStream.close();
    while (threadGroup.activeCount() > 0) {
      try {
        managingTask.log(("waiting for " + threadGroup.activeCount()) + " Threads:", MSG_DEBUG);
        Thread[] thread = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(thread);
        for (int i = 0; (i < thread.length) && (thread[i] != null); i++) {
          try {
            managingTask.log(thread[i].toString(), MSG_DEBUG);
          } catch (NullPointerException enPeaEx) {
          }
        }
        wait(ONE_SECOND);
      } catch (InterruptedException eyeEx) {
        Thread[] thread = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(thread);
        for (int i = 0; (i < thread.length) && (thread[i] != null); i++) {
          thread[i].interrupt();
        }
      }
    }
    setProperties();
    inputStream = null;
    outputStream = null;
    errorStream = null;
    outPrintStream = null;
    errorPrintStream = null;
  }
}
