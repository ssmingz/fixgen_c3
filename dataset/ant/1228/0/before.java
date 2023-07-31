class PlaceHold {
  public void handleSystemErr(String line) throws ExecutionException {
    if (subbuildKey == null) {
      super.handleSystemErr(line);
    } else {
      execService.handleBuildOutput(subbuildKey, line, true);
    }
  }
}
