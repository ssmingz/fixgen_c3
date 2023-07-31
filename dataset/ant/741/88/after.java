class PlaceHold {
  protected ExecTask createExec() throws TaskException {
    return ((ExecTask) (getProject().createTask("exec")));
  }
}
