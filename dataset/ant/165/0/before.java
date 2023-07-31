class PlaceHold {
  public Process exec(final ExecMetaData metaData) throws IOException, ExecException {
    if (ExecUtil.isCwd(metaData.getWorkingDirectory())) {
      return Runtime.getRuntime().exec(metaData.getCommand(), metaData.getEnvironment());
    } else if (null == c_execWithCWD) {
      final String message =
          "Unable to launch native command in a " + "working directory other than \".\"";
      throw new ExecException(message);
    } else {
      return execJava13(metaData);
    }
  }
}
