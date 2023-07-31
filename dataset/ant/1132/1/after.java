class PlaceHold {
  private void writeCachedDependencies(Hashtable dependencyMap) throws IOException {
    if (cache != null) {
      PrintWriter pw = null;
      try {
        cache.mkdirs();
        File depFile = new File(cache, CACHE_FILE_NAME);
        pw = new PrintWriter(new FileWriter(depFile));
        Enumeration e = dependencyMap.keys();
        while (e.hasMoreElements()) {
          String className = ((String) (e.nextElement()));
          pw.println(CLASSNAME_PREPEND + className);
          Vector dependencyList = ((Vector) (dependencyMap.get(className)));
          int size = dependencyList.size();
          for (int x = 0; x < size; x++) {
            pw.println(dependencyList.elementAt(x));
          }
        }
      } finally {
        FileUtils.close(pw);
      }
    }
  }
}
