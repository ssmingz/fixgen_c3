class PlaceHold {
  protected String[] getParameters() {
    Vector params = new Vector();
    params.addElement("-jp_function=" + function);
    if (vm != null) {
      params.addElement("-jp_vm=" + vm);
    }
    if (javaExe != null) {
      params.addElement("-jp_java_exe=" + project.resolveFile(javaExe.getPath()));
    }
    params.addElement("-jp_working_dir=" + workingDir.getPath());
    params.addElement("-jp_snapshot_dir=" + snapshotDir.getPath());
    params.addElement("-jp_record_from_start=" + recordFromStart);
    params.addElement("-jp_warn=" + warnLevel);
    if (seedName != null) {
      params.addElement("-jp_output_file=" + seedName);
    }
    params.addElement("-jp_filter=" + filters.toString());
    if (triggers != null) {
      params.addElement("-jp_trigger=" + triggers.toString());
    }
    if (finalSnapshot != null) {
      params.addElement("-jp_final_snapshot=" + finalSnapshot);
    }
    params.addElement("-jp_exit_prompt=" + exitPrompt);
    params.addElement("-jp_track_natives=" + trackNatives);
    String[] vmargs = cmdlJava.getVmCommand().getArguments();
    for (int i = 0; i < vmargs.length; i++) {
      params.addElement(vmargs[i]);
    }
    Path classpath = cmdlJava.getClasspath();
    if ((classpath != null) && (classpath.size() > 0)) {
      params.addElement("-classpath " + classpath.toString());
    }
    if (cmdlJava.getClassname() != null) {
      params.addElement(cmdlJava.getClassname());
    }
    String[] args = cmdlJava.getJavaCommand().getArguments();
    for (int i = 0; i < args.length; i++) {
      params.addElement(args[i]);
    }
    String[] array = new String[params.size()];
    params.copyInto(array);
    return array;
  }
}
