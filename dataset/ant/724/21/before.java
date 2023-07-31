class PlaceHold {
  protected int run(Commandline cmd) throws TaskException {
    try {
      Execute exe = new Execute(new LogStreamHandler(this, Project.MSG_INFO, Project.MSG_WARN));
      if (m_serverPath != null) {
        String[] env = exe.getEnvironment();
        if (env == null) {
          env = new String[0];
        }
        String[] newEnv = new String[env.length + 1];
        for (int i = 0; i < env.length; i++) {
          newEnv[i] = env[i];
        }
        newEnv[env.length] = "SSDIR=" + m_serverPath;
        exe.setEnvironment(newEnv);
      }
      exe.setAntRun(project);
      exe.setWorkingDirectory(project.getBaseDir());
      exe.setCommandline(cmd.getCommandline());
      return exe.execute();
    } catch (IOException e) {
      throw new TaskException("Error", e);
    }
  }
}
