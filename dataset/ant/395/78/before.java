class PlaceHold {
  public void execute() throws BuildException {
    if (((fromExtension == null) || (toExtension == null)) || (srcDir == null)) {
      throw new BuildException(
          "srcDir, fromExtension and toExtension " + "attributes must be set!");
    }
    log("DEPRECATED - The renameext task is deprecated.  Use move instead.", MSG_WARN);
    log("Replace this with:", MSG_INFO);
    log(((("<move todir=\"" + srcDir) + "\" overwrite=\"") + replace) + "\">", MSG_INFO);
    log(("  <fileset dir=\"" + srcDir) + "\" />", MSG_INFO);
    log("  <mapper type=\"glob\"", MSG_INFO);
    log(("          from=\"*" + fromExtension) + "\"", MSG_INFO);
    log(("          to=\"*" + toExtension) + "\" />", MSG_INFO);
    log("</move>", MSG_INFO);
    log("using the same patterns on <fileset> as you\'ve used here", MSG_INFO);
    Move move = ((Move) (project.createTask("move")));
    move.setOwningTarget(target);
    move.setTaskName(getTaskName());
    move.setLocation(getLocation());
    move.setTodir(srcDir);
    move.setOverwrite(replace);
    fileset.setDir(srcDir);
    move.addFileset(fileset);
    Mapper me = move.createMapper();
    me.setType(globType);
    me.setFrom("*" + fromExtension);
    me.setTo("*" + toExtension);
    move.execute();
  }
}
