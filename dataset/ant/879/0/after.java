class PlaceHold {
  protected void slowScan() {
    synchronized (slowScanLock) {
      if (haveSlowResults) {
        return;
      }
      if (slowScanning) {
        while (slowScanning) {
          try {
            slowScanLock.wait();
          } catch (InterruptedException e) {
          }
        }
        return;
      }
      slowScanning = true;
    }
    try {
      synchronized (this) {
        boolean nullIncludes = includes == null;
        includes = (nullIncludes) ? new String[] {SelectorUtils.DEEP_TREE_MATCH} : includes;
        boolean nullExcludes = excludes == null;
        excludes = (nullExcludes) ? new String[0] : excludes;
        String[] excl = new String[dirsExcluded.size()];
        dirsExcluded.copyInto(excl);
        String[] notIncl = new String[dirsNotIncluded.size()];
        dirsNotIncluded.copyInto(notIncl);
        processSlowScan(excl);
        processSlowScan(notIncl);
        clearCaches();
        includes = (nullIncludes) ? null : includes;
        excludes = (nullExcludes) ? null : excludes;
      }
    } finally {
      synchronized (slowScanLock) {
        haveSlowResults = true;
        slowScanning = false;
        slowScanLock.notifyAll();
      }
    }
  }
}
