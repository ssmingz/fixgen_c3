class PlaceHold {
  protected ExecTask createExec() throws BuildException {
    ExecTask exec = new ExecTask(this);
    return exec;
  }
}
