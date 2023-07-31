class PlaceHold{
protected ArrayList getOptions() throws TaskException {
    ArrayList options = new ArrayList(512);
    final Path classpath = new Path();
    classpath.add(getSourcePath());
    classpath.add(getClassPath());
    final String formattedClasspath = FileListUtil.formatPath(classpath, getContext());
    if (formattedClasspath.length() > 0) {
        options.add("-classpath");
        options.add(formattedClasspath);
    }
    options.add("-output");
    options.add(tmpFile.toString());
    options.add("-" + granularity);
    options.add("-format");
    options.add("tab");
    options.add("-i");
    options.add("/");
    final String[] dirs = path.listFiles(getContext());
    for (int i = 0; i < dirs.length; i++) {
        options.add(dirs[i]);
    }
    addAllArrayList(options, getIncludedFiles().keySet().iterator());
    return options;
}
}