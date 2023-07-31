class PlaceHold {
  protected Path getCompileClasspath() {
    Path classpath = new Path(attributes.getProject());
    classpath.setLocation(attributes.getBase());
    if (attributes.getClasspath() == null) {
      if (attributes.getIncludeantruntime()) {
        classpath.addExisting(systemClasspath);
      }
    } else if (attributes.getIncludeantruntime()) {
      classpath.addExisting(attributes.getClasspath().concatSystemClasspath("last"));
    } else {
      classpath.addExisting(attributes.getClasspath().concatSystemClasspath("ignore"));
    }
    if (attributes.getIncludejavaruntime()) {
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
      } else if (System.getProperty("java.vm.name").equals("Kaffe")) {
        FileSet kaffeJarFiles = new FileSet();
        kaffeJarFiles.setDir(
            new File(
                (((System.getProperty("java.home") + File.separator) + "share") + File.separator)
                    + "kaffe"));
        kaffeJarFiles.setIncludes("*.jar");
        classpath.addFileset(kaffeJarFiles);
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
