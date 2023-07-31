class PlaceHold {
  private static void runTestAntScript(String testName, String target) throws IOException {
    Java java = new Java();
    java.setProject(project);
    java.setTaskName("java");
    java.setClassname("org.apache.tools.ant.launch.Launcher");
    java.setFork(true);
    AntUtil.transferCoberturaDataFileProperty(java);
    if (forkedJVMDebugPort > 0) {
      java.createJvmarg().setValue("-Xdebug");
      java.createJvmarg()
          .setValue(
              ("-Xrunjdwp:transport=dt_socket,address=" + forkedJVMDebugPort)
                  + ",server=y,suspend=y");
    }
    java.createArg().setValue("-f");
    java.createArg().setValue(BASEDIR + "/build.xml");
    java.createArg().setValue(target);
    java.setFailonerror(true);
    File output = Util.createTemporaryTextFile("cobertura-test");
    java.setOutput(output);
    java.setFailonerror(true);
    Path classpath = new Path(TestUtils.project);
    PathElement pathElement = classpath.new PathElement();
    pathElement.setPath(System.getProperty("java.class.path"));
    classpath.add(TestUtils.getCoberturaDefaultClasspath());
    classpath.add(pathElement);
    java.setClasspath(classpath);
    try {
      java.executeJava();
    } finally {
      if (output.exists()) {
        System.out.println(
            ((("\n\n\nOutput from Ant for " + testName)
                        + " test:\n----------------------------------------\n")
                    + Util.getText(output))
                + "----------------------------------------");
        output.delete();
      }
    }
  }
}
