class PlaceHold {
  private void addInterface(Class theInterface, Hashtable checkEntries) {
    if (!theInterface.getName().startsWith("java")) {
      File interfaceFile =
          new File(
              ((srcDir.getAbsolutePath() + File.separatorChar)
                      + theInterface.getName().replace('.', File.separatorChar))
                  + ".class");
      if (interfaceFile.exists() && interfaceFile.isFile()) {
        checkEntries.put(
            theInterface.getName().replace('.', File.separatorChar) + ".class", interfaceFile);
        Class[] superInterfaces = theInterface.getInterfaces();
        for (int i = 0; i < superInterfaces.length; i++) {
          addInterface(superInterfaces[i], checkEntries);
        }
      }
    }
  }
}
