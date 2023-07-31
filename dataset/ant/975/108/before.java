class PlaceHold {
  private void change_stdout(String line) {
    if (util.match("/Change/", line)) {
      line = util.substitute("s/[^0-9]//g", line);
      final int changenumber = Integer.parseInt(line);
      getLogger().info("Change Number is " + changenumber);
      try {
        getContext().setProperty("p4.change", "" + changenumber);
      } catch (final TaskException te) {
        registerError(te);
      }
    } else if (util.match("/error/", line)) {
      final String message = "Perforce Error, check client settings and/or server";
      registerError(new TaskException(message));
    }
  }
}
