class PlaceHold {
  String getGenicClassName(Path classpath) {
    log("Looking for GenIC class in classpath: " + classpath.toString(), MSG_VERBOSE);
    AntClassLoader cl = classpath.getProject().createClassLoader(classpath);
    try {
      cl.loadClass(JonasDeploymentTool.GENIC_CLASS);
      log(
          ("Found GenIC class '" + JonasDeploymentTool.GENIC_CLASS) + "' in classpath.",
          MSG_VERBOSE);
      return JonasDeploymentTool.GENIC_CLASS;
    } catch (ClassNotFoundException cnf1) {
      log(
          ("GenIC class '" + JonasDeploymentTool.GENIC_CLASS) + "' not found in classpath.",
          MSG_VERBOSE);
    }
    try {
      cl.loadClass(JonasDeploymentTool.OLD_GENIC_CLASS_1);
      log(
          ("Found GenIC class '" + JonasDeploymentTool.OLD_GENIC_CLASS_1) + "' in classpath.",
          MSG_VERBOSE);
      return JonasDeploymentTool.OLD_GENIC_CLASS_1;
    } catch (ClassNotFoundException cnf2) {
      log(
          ("GenIC class '" + JonasDeploymentTool.OLD_GENIC_CLASS_1) + "' not found in classpath.",
          MSG_VERBOSE);
    }
    try {
      cl.loadClass(JonasDeploymentTool.OLD_GENIC_CLASS_2);
      log(
          ("Found GenIC class '" + JonasDeploymentTool.OLD_GENIC_CLASS_2) + "' in classpath.",
          MSG_VERBOSE);
      return JonasDeploymentTool.OLD_GENIC_CLASS_2;
    } catch (ClassNotFoundException cnf3) {
      log(
          ("GenIC class '" + JonasDeploymentTool.OLD_GENIC_CLASS_2) + "' not found in classpath.",
          MSG_VERBOSE);
    }
    return null;
  }
}
