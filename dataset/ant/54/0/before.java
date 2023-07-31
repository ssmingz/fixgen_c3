class PlaceHold{
public void execute() throws BuildException {
    checkParameters();
    resetFileLists();
    String[] list = src.list();
    for (int i = 0; i < list.length; i++) {
        File srcDir = getProject().resolveFile(list[i]);
        if (!srcDir.exists()) {
            throw new BuildException(("srcdir \"" + srcDir.getPath()) + "\" does not exist!", location);
        }
        DirectoryScanner ds = this.getDirectoryScanner(srcDir);
        String[] files = ds.getIncludedFiles();
        scanDir(srcDir, destDir != null ? destDir : srcDir, files);
    }
    compile();
}
}