class PlaceHold {
  private void determineDependencies() throws IOException, TaskException {
    affectedClassMap = new Hashtable();
    classFileInfoMap = new Hashtable();
    boolean cacheDirty = false;
    Hashtable dependencyMap = new Hashtable();
    File depCacheFile = null;
    boolean depCacheFileExists = true;
    long depCacheFileLastModified = Long.MAX_VALUE;
    if (cache != null) {
      dependencyMap = readCachedDependencies();
      depCacheFile = new File(cache, CACHE_FILE_NAME);
      depCacheFileExists = depCacheFile.exists();
      depCacheFileLastModified = depCacheFile.lastModified();
    }
    for (Iterator e = getClassFiles(destPath).iterator(); e.hasNext(); ) {
      ClassFileInfo info = ((ClassFileInfo) (e.next()));
      getLogger().debug("Adding class info for " + info.className);
      classFileInfoMap.put(info.className, info);
      ArrayList dependencyList = null;
      if (cache != null) {
        if (depCacheFileExists && (depCacheFileLastModified > info.absoluteFile.lastModified())) {
          dependencyList = ((ArrayList) (dependencyMap.get(info.className)));
        }
      }
      if (dependencyList == null) {
        FileInputStream inFileStream = null;
        try {
          inFileStream = new FileInputStream(info.absoluteFile);
          ClassFile classFile = new ClassFile();
          classFile.read(inFileStream);
          dependencyList = classFile.getClassRefs();
          if (dependencyList != null) {
            cacheDirty = true;
            dependencyMap.put(info.className, dependencyList);
          }
        } finally {
          if (inFileStream != null) {
            inFileStream.close();
          }
        }
      }
      for (Iterator depEnum = dependencyList.iterator(); depEnum.hasNext(); ) {
        String dependentClass = ((String) (depEnum.next()));
        Hashtable affectedClasses = ((Hashtable) (affectedClassMap.get(dependentClass)));
        if (affectedClasses == null) {
          affectedClasses = new Hashtable();
          affectedClassMap.put(dependentClass, affectedClasses);
        }
        affectedClasses.put(info.className, info);
      }
    }
    classpathDependencies = null;
    if (dependClasspath != null) {
      classpathDependencies = new Hashtable();
      final URL[] urls = PathUtil.toURLs(dependClasspath);
      final ClassLoader classLoader = new URLClassLoader(urls);
      Hashtable classpathFileCache = new Hashtable();
      Object nullFileMarker = new Object();
      for (Enumeration e = dependencyMap.keys(); e.hasMoreElements(); ) {
        String className = ((String) (e.nextElement()));
        ArrayList dependencyList = ((ArrayList) (dependencyMap.get(className)));
        Hashtable dependencies = new Hashtable();
        classpathDependencies.put(className, dependencies);
        for (Iterator e2 = dependencyList.iterator(); e2.hasNext(); ) {
          String dependency = ((String) (e2.next()));
          Object classpathFileObject = classpathFileCache.get(dependency);
          if (classpathFileObject == null) {
            classpathFileObject = nullFileMarker;
            if ((!dependency.startsWith("java.")) && (!dependency.startsWith("javax."))) {
              final String name = dependency.replace('.', '/') + ".class";
              URL classURL = classLoader.getResource(name);
              if (classURL != null) {
                if (classURL.getProtocol().equals("jar")) {
                  String jarFilePath = classURL.getFile();
                  if (jarFilePath.startsWith("file:")) {
                    int classMarker = jarFilePath.indexOf('!');
                    jarFilePath = jarFilePath.substring(5, classMarker);
                  }
                  classpathFileObject = new File(jarFilePath);
                } else if (classURL.getProtocol().equals("file")) {
                  String classFilePath = classURL.getFile();
                  classpathFileObject = new File(classFilePath);
                }
                getLogger()
                    .debug(
                        (((("Class " + className) + " depends on ") + classpathFileObject)
                                + " due to ")
                            + dependency);
              }
            }
            classpathFileCache.put(dependency, classpathFileObject);
          }
          if ((classpathFileObject != null) && (classpathFileObject != nullFileMarker)) {
            File jarFile = ((File) (classpathFileObject));
            dependencies.put(jarFile, jarFile);
          }
        }
      }
    }
    if ((cache != null) && cacheDirty) {
      writeCachedDependencies(dependencyMap);
    }
  }
}
