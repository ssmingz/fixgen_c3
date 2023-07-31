class PlaceHold {
  protected String[] getParameters() throws TaskException {
    ArrayList params = new ArrayList();
    params.add("-jp_function=coverage");
    if (m_vm != null) {
      params.add("-jp_vm=" + m_vm);
    }
    if (m_javaExe != null) {
      params.add("-jp_java_exe=" + getContext().resolveFile(m_javaExe.getPath()));
    }
    params.add("-jp_working_dir=" + m_workingDir.getPath());
    params.add("-jp_snapshot_dir=" + m_snapshotDir.getPath());
    params.add("-jp_record_from_start=" + m_recordFromStart);
    params.add("-jp_warn=" + m_warnLevel);
    if (m_seedName != null) {
      params.add("-jp_output_file=" + m_seedName);
    }
    params.add("-jp_filter=" + m_filters.toString());
    if (m_triggers != null) {
      params.add("-jp_trigger=" + m_triggers.toString());
    }
    if (m_finalSnapshot != null) {
      params.add("-jp_final_snapshot=" + m_finalSnapshot);
    }
    params.add("-jp_exit_prompt=" + m_exitPrompt);
    params.add("-jp_track_natives=" + m_trackNatives);
    String[] vmargs = cmdlJava.getVmCommand().getArguments();
    for (int i = 0; i < vmargs.length; i++) {
      params.add(vmargs[i]);
    }
    Path classpath = cmdlJava.getClasspath();
    if ((classpath != null) && (classpath.size() > 0)) {
      params.add("-classpath " + classpath.toString());
    }
    if (cmdlJava.getClassname() != null) {
      params.add(cmdlJava.getClassname());
    }
    String[] args = cmdlJava.getJavaCommand().getArguments();
    for (int i = 0; i < args.length; i++) {
      params.add(args[i]);
    }
    return ((String[]) (params.toArray(new String[params.size()])));
  }
}
