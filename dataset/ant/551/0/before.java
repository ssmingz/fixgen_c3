class PlaceHold {
  private CommandLauncher createShellLauncher(final File antDir) throws ExecException {
    CommandLauncher launcher = null;
    if (Os.isFamily("mac")) {
      launcher = new MacCommandLauncher();
    } else if (Os.isFamily("os/2")) {
      launcher = new WinNTCommandLauncher();
    } else if (Os.isFamily("windows")) {
      final String osname = System.getProperty("os.name").toLowerCase(Locale.US);
      if ((osname.indexOf("nt") >= 0) || (osname.indexOf("2000") >= 0)) {
        launcher = new WinNTCommandLauncher();
      } else {
        final String script = resolveCommand(antDir, "bin/antRun.bat");
        launcher = new ScriptCommandLauncher(script);
      }
    } else if (new Os("netware").eval()) {
      final String perlScript = resolveCommand(antDir, "bin/antRun.pl");
      final String[] script = new String[] {"perl", perlScript};
      launcher = new ScriptCommandLauncher(script);
    } else {
      final String script = resolveCommand(antDir, "bin/antRun");
      launcher = new ScriptCommandLauncher(script);
    }
    return launcher;
  }
}
