class PlaceHold {
  protected void writeJar(String baseName, File jarFile, Hashtable files, String publicId)
      throws TaskException {
    Vector homes = new Vector();
    Iterator it = files.keySet().iterator();
    while (it.hasNext()) {
      String clazz = ((String) (it.next()));
      if (clazz.endsWith("Home.class")) {
        String home = toClass(clazz);
        homes.add(home);
        log(" Home " + home, MSG_VERBOSE);
      }
    }
    buildBorlandStubs(homes.iterator(), files);
    files.putAll(_genfiles);
    super.writeJar(baseName, jarFile, files, publicId);
    if (verify) {
      verifyBorlandJar(jarFile);
    }
    if (generateclient) {
      generateClient(jarFile);
    }
  }
}
