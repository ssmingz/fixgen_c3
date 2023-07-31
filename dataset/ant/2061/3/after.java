class PlaceHold {
  protected void checkAndAddDependants(Hashtable checkEntries) throws BuildException {
    if (dependencyAnalyzer == null) {
      return;
    }
    dependencyAnalyzer.reset();
    Iterator i = checkEntries.keySet().iterator();
    while (i.hasNext()) {
      String entryName = ((String) (i.next()));
      if (entryName.endsWith(".class")) {
        String className = entryName.substring(0, entryName.length() - ".class".length());
        className = className.replace(File.separatorChar, '/');
        className = className.replace('/', '.');
        dependencyAnalyzer.addRootClass(className);
      }
    }
    Enumeration e = dependencyAnalyzer.getClassDependencies();
    while (e.hasMoreElements()) {
      String classname = ((String) (e.nextElement()));
      String location = classname.replace('.', File.separatorChar) + ".class";
      File classFile = new File(config.srcDir, location);
      if (classFile.exists()) {
        checkEntries.put(location, classFile);
        log((("dependent class: " + classname) + " - ") + classFile, MSG_VERBOSE);
      }
    }
  }
}
