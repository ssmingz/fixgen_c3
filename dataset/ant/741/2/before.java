class PlaceHold {
  private void executeWLS6() throws TaskException {
    File securityPolicyFile = findSecurityPolicyFile(DEFAULT_WL60_POLICY_FILE);
    if (!beaHome.isDirectory()) {
      throw new TaskException(("BEA home " + beaHome.getPath()) + " is not valid");
    }
    File configFile =
        new File(weblogicSystemHome, ("config/" + weblogicDomainName) + "/config.xml");
    if (!configFile.exists()) {
      throw new TaskException(("Server config file " + configFile) + " not found.");
    }
    if (managementPassword == null) {
      throw new TaskException("You must supply a management password to start the server");
    }
    Java weblogicServer = ((Java) (project.createTask("java")));
    weblogicServer.setFork(true);
    weblogicServer.setDir(weblogicSystemHome);
    weblogicServer.setClassname(weblogicMainClass);
    String jvmArgs = additionalJvmArgs;
    jvmArgs += " -Dweblogic.Domain=" + weblogicDomainName;
    jvmArgs += " -Dweblogic.Name=" + weblogicSystemName;
    jvmArgs += " -Dweblogic.system.home=" + weblogicSystemHome;
    jvmArgs += " -Dbea.home=" + beaHome;
    jvmArgs += " -Djava.security.policy==" + securityPolicyFile;
    jvmArgs += " -Dweblogic.management.username=" + managementUsername;
    jvmArgs += " -Dweblogic.management.password=" + managementPassword;
    if (pkPassword != null) {
      jvmArgs += " -Dweblogic.pkpassword=" + pkPassword;
    }
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
