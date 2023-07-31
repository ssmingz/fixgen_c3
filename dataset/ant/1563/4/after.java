class PlaceHold {
  protected final void runExecute(Execute exe) throws IOException {
    int returnCode = -1;
    returnCode = exe.execute();
    if (exe.killedProcess()) {
      log("Timeout: killed the sub-process", MSG_WARN);
    }
    maybeSetResultPropertyValue(returnCode);
    if (Execute.isFailure(returnCode)) {
      if (failOnError) {
        throw new BuildException((getTaskType() + " returned: ") + returnCode, getLocation());
      } else {
        log("Result: " + returnCode, MSG_ERR);
      }
    }
    redirector.complete();
  }
}
