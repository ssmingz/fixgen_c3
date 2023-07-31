class PlaceHold {
  public void scan() throws IllegalStateException {
    synchronized (scanLock) {
      if (scanning) {
        while (scanning) {
          try {
            scanLock.wait();
          } catch (InterruptedException e) {
            continue;
          }
        }
        if (illegal != null) {
          throw illegal;
        }
        return;
      }
      scanning = true;
    }
    File savedBase = basedir;
    try {
      synchronized (this) {
        illegal = null;
        clearResults();
        boolean nullIncludes = includes == null;
        includes = (nullIncludes) ? new String[] {SelectorUtils.DEEP_TREE_MATCH} : includes;
        boolean nullExcludes = excludes == null;
        excludes = (nullExcludes) ? new String[0] : excludes;
        if (((basedir != null) && (!followSymlinks))
            && FILE_UTILS.isSymbolicLink(basedir.getParentFile(), basedir.getName())) {
          basedir = null;
        }
        if (basedir == null) {
          if (nullIncludes) {
            return;
          }
        } else {
          if (!basedir.exists()) {
            if (errorOnMissingDir) {
              illegal = new IllegalStateException(("basedir " + basedir) + DOES_NOT_EXIST_POSTFIX);
            } else {
              return;
            }
          } else if (!basedir.isDirectory()) {
            illegal =
                new IllegalStateException((("basedir " + basedir) + " is not a") + " directory.");
          }
          if (illegal != null) {
            throw illegal;
          }
        }
        if (isIncluded("")) {
          if (!isExcluded("")) {
            if (isSelected("", basedir)) {
              dirsIncluded.addElement("");
            } else {
              dirsDeselected.addElement("");
            }
          } else {
            dirsExcluded.addElement("");
          }
        } else {
          dirsNotIncluded.addElement("");
        }
        checkIncludePatterns();
        clearCaches();
        includes = (nullIncludes) ? null : includes;
        excludes = (nullExcludes) ? null : excludes;
      }
    } catch (IOException ex) {
      throw new BuildException(ex);
    } finally {
      basedir = savedBase;
      synchronized (scanLock) {
        scanning = false;
        scanLock.notifyAll();
      }
    }
  }
}
