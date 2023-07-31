class PlaceHold {
  public String[] getCommandline() {
    int size = (vmCommand.size() + javaCommand.size()) + sysProperties.size();
    if ((classpath != null) && (classpath.size() > 0)) {
      size += 2;
    }
    String[] result = new String[size];
    System.arraycopy(vmCommand.getCommandline(), 0, result, 0, vmCommand.size());
    int pos = vmCommand.size();
    if (sysProperties.size() > 0) {
      System.arraycopy(sysProperties.getVariables(), 0, result, pos, sysProperties.size());
      pos += sysProperties.size();
    }
    if ((classpath != null) && (classpath.size() > 0)) {
      result[pos++] = "-classpath";
      result[pos++] = classpath.concatSystemClasspath("ignore").toString();
    }
    System.arraycopy(javaCommand.getCommandline(), 0, result, pos, javaCommand.size());
    return result;
  }
}
