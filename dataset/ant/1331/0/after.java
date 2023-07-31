class PlaceHold {
  protected String resolveExecutable(String exec, boolean mustSearchPath) {
    if (!resolveExecutable) {
      return exec;
    }
    File executableFile = getProject().resolveFile(exec);
    if (executableFile.exists()) {
      return executableFile.getAbsolutePath();
    }
    if (dir != null) {
      executableFile = FILE_UTILS.resolveFile(dir, exec);
      if (executableFile.exists()) {
        return executableFile.getAbsolutePath();
      }
    }
    if (mustSearchPath) {
      Path p = null;
      String[] environment = env.getVariables();
      if (environment != null) {
        for (int i = 0; i < environment.length; i++) {
          if (isPath(environment[i])) {
            p = new Path(getProject(), environment[i].substring(5));
            break;
          }
        }
      }
      if (p == null) {
        Vector envVars = Execute.getProcEnvironment();
        Enumeration e = envVars.elements();
        while (e.hasMoreElements()) {
          String line = ((String) (e.nextElement()));
          if (isPath(line)) {
            p = new Path(getProject(), line.substring(5));
            break;
          }
        }
      }
      if (p != null) {
        String[] dirs = p.list();
        for (int i = 0; i < dirs.length; i++) {
          executableFile = FILE_UTILS.resolveFile(new File(dirs[i]), exec);
          if (executableFile.exists()) {
            return executableFile.getAbsolutePath();
          }
        }
      }
    }
    return exec;
  }
}
