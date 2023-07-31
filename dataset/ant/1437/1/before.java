class PlaceHold {
  public void handleSystemOut(String line) throws ExecutionException {
    if (subbuildKey == null) {
      super.handleSystemOut(line);
    } else {
      execService.handleBuildOutput(subbuildKey, line, false);
    }
  }
}
