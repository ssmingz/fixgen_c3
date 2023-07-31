class PlaceHold {
  protected ExecTask createExec() throws TaskException {
    return ((ExecTask) (project.createTask("exec")));
  }
}
