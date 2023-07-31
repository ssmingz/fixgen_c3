class PlaceHold {
  private void addSuperClass(Class superClass, Hashtable checkEntries) {
    if (!superClass.getName().startsWith("java")) {
      File superClassFile =
          new File(
              ((srcDir.getAbsolutePath() + File.separatorChar)
                      + superClass.getName().replace('.', File.separatorChar))
                  + ".class");
      if (superClassFile.exists() && superClassFile.isFile()) {
        checkEntries.put(
            superClass.getName().replace('.', File.separatorChar) + ".class", superClassFile);
        Class[] superInterfaces = superClass.getInterfaces();
        for (int i = 0; i < superInterfaces.length; i++) {
          addInterface(superInterfaces[i], checkEntries);
        }
        addSuperClass(superClass.getSuperclass(), checkEntries);
      }
    }
  }
}
