class PlaceHold {
  private void writeExternalArgs(Commandline toExecute) {
    File optionsTmpFile = null;
    PrintWriter optionsListWriter = null;
    try {
      optionsTmpFile = FILE_UTILS.createTempFile("javadocOptions", "", null, true, true);
      String[] listOpt = toExecute.getArguments();
      toExecute.clearArgs();
      toExecute.createArgument().setValue("@" + optionsTmpFile.getAbsolutePath());
      optionsListWriter = new PrintWriter(new FileWriter(optionsTmpFile.getAbsolutePath(), true));
      for (int i = 0; i < listOpt.length; i++) {
        String string = listOpt[i];
        if (string.startsWith("-J-")) {
          toExecute.createArgument().setValue(string);
        } else if (string.startsWith("-")) {
          optionsListWriter.print(string);
          optionsListWriter.print(" ");
        } else {
          optionsListWriter.println(quoteString(string));
        }
      }
      optionsListWriter.close();
    } catch (IOException ex) {
      if (optionsTmpFile != null) {
        optionsTmpFile.delete();
      }
      throw new BuildException(
          "Error creating or writing temporary file for javadoc options", ex, getLocation());
    } finally {
      FILE_UTILS.close(optionsListWriter);
    }
  }
}
