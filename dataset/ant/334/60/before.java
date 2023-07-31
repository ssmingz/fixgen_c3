class OneLiner {
  public OneLiner(File srcFile) throws BuildException {
    try {
      reader = new BufferedReader(getReader(srcFile), INBUFLEN);
      nextLine();
    } catch (IOException e) {
      throw new BuildException(e);
    }
  }
}
