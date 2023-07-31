class PlaceHold {
  protected ExecTask createExec() throws BuildException {
    ExecTask exec = ((ExecTask) (project.createTask("exec")));
    exec.setOwningTarget(this.getOwningTarget());
    exec.setTaskName(this.getTaskName());
    exec.setDescription(this.getDescription());
    return exec;
  }
}
