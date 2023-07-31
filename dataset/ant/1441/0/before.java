class PlaceHold {
  protected Path getCompileClasspath() {
    Path classpath = new Path(project);
    if (destDir != null) {
      classpath.setLocation(destDir);
    }
    if (compileClasspath == null) {
      if (includeAntRuntime) {
        classpath.addExisting(systemClasspath);
      }
    } else if (includeAntRuntime) {
      classpath.addExisting(compileClasspath.concatSystemClasspath("last"));
    } else {
      classpath.addExisting(compileClasspath.concatSystemClasspath("ignore"));
    }
    if (includeJavaRuntime) {
      if (System.getProperty("java.vendor").toLowerCase().indexOf("microsoft") >= 0) {
        FileSet msZipFiles = new FileSet();
        msZipFiles.setDir(
            new File((System.getProperty("java.home") + File.separator) + "Packages"));
        msZipFiles.setIncludes("*.ZIP");
        classpath.addFileset(msZipFiles);
      } else if (Project.getJavaVersion() == Project.JAVA_1_1) {
        classpath.addExisting(
            new Path(
                null,
                (((System.getProperty("java.home") + File.separator) + "lib") + File.separator)
                    + "classes.zip"));
      } else {
        classpath.addExisting(
            new Path(
                null,
                (((System.getProperty("java.home") + File.separator) + "lib") + File.separator)
                    + "rt.jar"));
        classpath.addExisting(
            new Path(
                null,
                (((((System.getProperty("java.home") + File.separator) + "jre") + File.separator)
                            + "lib")
                        + File.separator)
                    + "rt.jar"));
        classpath.addExisting(
            new Path(
                null,
                (((((System.getProperty("java.home") + File.separator) + "..") + File.separator)
                            + "Classes")
                        + File.separator)
                    + "classes.jar"));
        classpath.addExisting(
            new Path(
                null,
                (((((System.getProperty("java.home") + File.separator) + "..") + File.separator)
                            + "Classes")
                        + File.separator)
                    + "ui.jar"));
      }
    }
    return classpath;
  }
}
