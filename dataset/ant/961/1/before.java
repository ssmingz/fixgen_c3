class PlaceHold {
  protected String[] getParameters() throws TaskException {
    Commandline params = new Commandline();
    params.addArgument("-jp_function=coverage");
    if (m_vm != null) {
      params.addArgument("-jp_vm=" + m_vm);
    }
    if (m_javaExe != null) {
      params.addArgument("-jp_java_exe=" + m_javaExe.getPath());
    }
    params.addArgument("-jp_working_dir=" + m_workingDir.getPath());
    params.addArgument("-jp_snapshot_dir=" + m_snapshotDir.getPath());
    params.addArgument("-jp_record_from_start=" + m_recordFromStart);
    params.addArgument("-jp_warn=" + m_warnLevel);
    if (m_seedName != null) {
      params.addArgument("-jp_output_file=" + m_seedName);
    }
    params.addArgument("-jp_filter=" + m_filters.toString());
    if (m_triggers != null) {
      params.addArgument("-jp_trigger=" + m_triggers.toString());
    }
    if (m_finalSnapshot != null) {
      params.addArgument("-jp_final_snapshot=" + m_finalSnapshot);
    }
    params.addArgument("-jp_exit_prompt=" + m_exitPrompt);
    params.addArgument("-jp_track_natives=" + m_trackNatives);
    params.addArguments(m_vmArgs);
    final String[] classpath = m_classpath.listFiles(getContext());
    if (classpath.length > 0) {
      params.addArgument("-classpath");
      params.addArgument(PathUtil.formatPath(classpath));
    }
    if (m_className != null) {
      params.addArgument(m_className);
    }
    params.addArguments(m_args);
    return params.getArguments();
  }
}
