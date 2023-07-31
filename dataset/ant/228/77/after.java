class PlaceHold {
  protected void scanDir(File srcDir, File destDir, String[] files) {
    long now = new Date().getTime();
    for (int i = 0; i < files.length; i++) {
      File srcFile = new File(srcDir, files[i]);
      if (files[i].endsWith(".jsp")) {
        int fileStart = files[i].lastIndexOf(File.separatorChar) + 1;
        File javaFile =
            new File(destDir, files[i].substring(fileStart, files[i].indexOf(".jsp")) + ".java");
        if (srcFile.lastModified() > now) {
          log("Warning: file modified in the future: " + files[i], MSG_WARN);
        }
        if ((!javaFile.exists()) || (srcFile.lastModified() > javaFile.lastModified())) {
          if (!javaFile.exists()) {
            log(
                ((("Compiling " + srcFile.getPath()) + " because java file ") + javaFile.getPath())
                    + " does not exist",
                MSG_DEBUG);
          } else {
            log(
                (("Compiling " + srcFile.getPath()) + " because it is out of date with respect to ")
                    + javaFile.getPath(),
                MSG_DEBUG);
          }
          compileList.add(srcFile.getAbsolutePath());
        }
      }
    }
  }
}
