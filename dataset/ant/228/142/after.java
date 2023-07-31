class PlaceHold {
  public static VAJBuildInfo parse(String data) {
    VAJBuildInfo result = new VAJBuildInfo();
    try {
      StringTokenizer tok = new StringTokenizer(data, "|");
      result.setOutputMessageLevel(tok.nextToken());
      result.setBuildFileName(tok.nextToken());
      result.setTarget(tok.nextToken());
      while (tok.hasMoreTokens()) {
        result.projectTargets.add(tok.nextToken());
      }
    } catch (Throwable t) {
    }
    return result;
  }
}
