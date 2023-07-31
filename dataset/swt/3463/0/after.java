class PlaceHold {
  JNIClass[] getASTClasses() {
    if (classes != null) {
      return classes;
    }
    if (mainClassName == null) {
      return new JNIClass[0];
    }
    String root = (classesDir != null) ? classesDir : new File(outputDir).getParent() + "/";
    String mainPath =
        new File((root + mainClassName.replace('.', '/')) + ".java").getAbsolutePath();
    ArrayList<JNIClass> classes = new ArrayList<JNIClass>();
    String packageName = getPackageName(mainClassName);
    File dir = new File((root + "/") + packageName.replace('.', '/'));
    File[] files = dir.listFiles();
    for (int i = 0; i < files.length; i++) {
      File file = files[i];
      try {
        String path = file.getAbsolutePath().replace('\\', '/');
        if (path.endsWith(".java")) {
          if (mainPath.equals(path)) {
            classes.add(mainClass);
          } else {
            classes.add(new ASTClass(path, metaData));
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return classes.toArray(new JNIClass[classes.size()]);
  }
}
