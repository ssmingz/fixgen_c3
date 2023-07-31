class PlaceHold {
  private Hashtable readCachedDependencies(File depFile) throws IOException {
    Hashtable dependencyMap = new Hashtable();
    BufferedReader in = null;
    try {
      in = new BufferedReader(new FileReader(depFile));
      String line = null;
      Vector dependencyList = null;
      String className = null;
      int prependLength = CLASSNAME_PREPEND.length();
      while ((line = in.readLine()) != null) {
        if (line.startsWith(CLASSNAME_PREPEND)) {
          dependencyList = new Vector();
          className = line.substring(prependLength);
          dependencyMap.put(className, dependencyList);
        } else {
          dependencyList.addElement(line);
        }
      }
    } finally {
      if (in != null) {
        in.close();
      }
    }
    return dependencyMap;
  }
}
