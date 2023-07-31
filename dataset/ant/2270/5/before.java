class PlaceHold {
  private File getFileList() throws TaskException {
    final Commandline cmd = buildPCLICommand();
    getContext().debug("Executing " + cmd.toString());
    File tmp = null;
    try {
      tmp = File.createTempFile("pvcs_ant_", ".log");
      final File fileList = File.createTempFile("pvcs_ant_", ".log");
      final ExecManager execManager = ((ExecManager) (getService(ExecManager.class)));
      final Execute exe = new Execute(execManager);
      exe.setExecOutputHandler(this);
      m_output = new FileOutputStream(tmp);
      exe.setWorkingDirectory(getBaseDirectory());
      exe.setCommandline(cmd);
      final int result = exe.execute();
      checkResultCode(result, cmd);
      if (!tmp.exists()) {
        final String message =
            "Communication between ant and pvcs failed. No output "
                + "generated from executing PVCS commandline interface \"pcli\" and \"get\"";
        throw new TaskException(message);
      }
      getContext().info("Creating folders");
      createFolders(tmp);
      massagePCLI(tmp, fileList);
      return fileList;
    } catch (final ParseException pe) {
      final String message =
          (("Failed executing: " + cmd.toString()) + ". Exception: ") + pe.getMessage();
      throw new TaskException(message);
    } catch (final IOException ioe) {
      final String message =
          (("Failed executing: " + cmd.toString()) + ". Exception: ") + ioe.getMessage();
      throw new TaskException(message);
    } finally {
      IOUtil.shutdownStream(m_output);
      if (null != tmp) {
        tmp.delete();
      }
    }
  }
}
