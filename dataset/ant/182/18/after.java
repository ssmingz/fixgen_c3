class PlaceHold {
  private int run(String[] command) throws TaskException {
    FileOutputStream fos = null;
    try {
      Execute exe = null;
      if (out == null) {
        exe = new Execute(new LogStreamHandler(this, Project.MSG_INFO, Project.MSG_WARN), null);
      } else {
        fos = new FileOutputStream(out);
        exe = new Execute(new PumpStreamHandler(fos), null);
      }
      exe.setAntRun(project);
      if (dir == null) {
        dir = getBaseDirectory();
      } else if ((!dir.exists()) || (!dir.isDirectory())) {
        throw new TaskException(dir.getAbsolutePath() + " is not a valid directory");
      }
      exe.setWorkingDirectory(dir);
      exe.setCommandline(command);
      try {
        return exe.execute();
      } catch (IOException e) {
        throw new TaskException("Error", e);
      }
    } catch (IOException io) {
      throw new TaskException("Error", io);
    } finally {
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException io) {
        }
      }
    }
  }
}
