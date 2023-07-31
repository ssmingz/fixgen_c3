class PlaceHold {
  private void executeWLS() throws TaskException {
    File securityPolicyFile = findSecurityPolicyFile(DEFAULT_WL51_POLICY_FILE);
    File propertiesFile = null;
    if (weblogicPropertiesFile == null) {
      weblogicPropertiesFile = DEFAULT_PROPERTIES_FILE;
    }
    propertiesFile = new File(weblogicSystemHome, weblogicPropertiesFile);
    if (!propertiesFile.exists()) {
      propertiesFile = resolveFile(weblogicPropertiesFile);
      if (!propertiesFile.exists()) {
        throw new TaskException(
            ((("Properties file " + weblogicPropertiesFile) + " not found in weblogic home ")
                    + weblogicSystemHome)
                + " or as absolute file");
      }
    }
    Java weblogicServer = ((Java) (project.createTask("java")));
    weblogicServer.setFork(true);
    weblogicServer.setClassname(weblogicMainClass);
    String jvmArgs = additionalJvmArgs;
    if (weblogicClasspath != null) {
      jvmArgs += " -Dweblogic.class.path=" + weblogicClasspath;
    }
    jvmArgs += " -Djava.security.manager -Djava.security.policy==" + securityPolicyFile;
    jvmArgs += " -Dweblogic.system.home=" + weblogicSystemHome;
    jvmArgs += " -Dweblogic.system.name=" + weblogicSystemName;
    jvmArgs += " -Dweblogic.system.propertiesFile=" + weblogicPropertiesFile;
    weblogicServer.createJvmarg().setLine(jvmArgs);
    weblogicServer.createArg().setLine(additionalArgs);
    if (classpath != null) {
      weblogicServer.setClasspath(classpath);
    }
    if (weblogicServer.executeJava() != 0) {
      throw new TaskException("Execution of weblogic server failed");
    }
  }
}
