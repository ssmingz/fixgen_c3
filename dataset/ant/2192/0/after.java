class PlaceHold {
  private void determineOutOfDateClasses() {
    outOfDateClasses = new Hashtable();
    for (int i = 0; i < srcPathList.length; i++) {
      File srcDir = ((File) (project.resolveFile(srcPathList[i])));
      if (srcDir.exists()) {
        DirectoryScanner ds = this.getDirectoryScanner(srcDir);
        String[] files = ds.getIncludedFiles();
        scanDir(srcDir, files);
      }
    }
    if (classpathDependencies == null) {
      return;
    }
    Enumeration classpathDepsEnum = classpathDependencies.keys();
    while (classpathDepsEnum.hasMoreElements()) {
      String className = ((String) (classpathDepsEnum.nextElement()));
      if (outOfDateClasses.containsKey(className)) {
        continue;
      }
      ClassFileInfo info = ((ClassFileInfo) (classFileInfoMap.get(className)));
      if (info != null) {
        Hashtable dependencies = ((Hashtable) (classpathDependencies.get(className)));
        for (Enumeration e2 = dependencies.elements(); e2.hasMoreElements(); ) {
          File classpathFile = ((File) (e2.nextElement()));
          if (classpathFile.lastModified() > info.absoluteFile.lastModified()) {
            log(
                (("Class " + className) + " is out of date with respect to ") + classpathFile,
                MSG_DEBUG);
            outOfDateClasses.put(className, className);
            break;
          }
        }
      }
    }
  }
}
