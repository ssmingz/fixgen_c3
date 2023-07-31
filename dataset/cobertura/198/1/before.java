class PlaceHold {
  protected Java getJava() {
    if (java == null) {
      java = ((Java) (getProject().createTask("java")));
      java.setTaskName(getTaskName());
      java.setClassname(getClassName());
      java.setFork(true);
      java.setDir(getProject().getBaseDir());
      if (maxMemory != null) {
        java.setJvmargs("-Xmx" + maxMemory);
      }
      if (forkedJVMDebugPort > 0) {
        java.setJvmargs("-Xdebug");
        java.setJvmargs(
            ("-Xrunjdwp:transport=dt_socket,address=" + forkedJVMDebugPort)
                + ",server=y,suspend=y");
      }
      if (getClass().getClassLoader() instanceof AntClassLoader) {
        String classpath = ((AntClassLoader) (getClass().getClassLoader())).getClasspath();
        createClasspath().setPath(StringUtil.replaceAll(classpath, "%20", " "));
      } else if (getClass().getClassLoader() instanceof URLClassLoader) {
        URL[] earls = ((URLClassLoader) (getClass().getClassLoader())).getURLs();
        for (int i = 0; i < earls.length; i++) {
          String classpath = new File(earls[i].getFile()).getAbsolutePath();
          createClasspath().setPath(StringUtil.replaceAll(classpath, "%20", " "));
        }
      }
    }
    return java;
  }
}
