class PlaceHold {
  protected void tar(ResourceCollection rc, TarOutputStream tOut) throws IOException {
    ArchiveFileSet afs = null;
    if (rc instanceof ArchiveFileSet) {
      afs = ((ArchiveFileSet) (rc));
    }
    if (((afs != null) && (afs.size() > 1)) && (afs.getFullpath(this.getProject()).length() > 0)) {
      throw new BuildException(
          (("fullpath attribute may only " + "be specified for ") + "filesets that specify a ")
              + "single file.");
    }
    TarFileSet tfs = asTarFileSet(afs);
    if (isFileFileSet(rc)) {
      FileSet fs = ((FileSet) (rc));
      String[] files = getFileNames(fs);
      for (int i = 0; i < files.length; i++) {
        File f = new File(fs.getDir(getProject()), files[i]);
        String name = files[i].replace(File.separatorChar, '/');
        tarFile(f, tOut, name, tfs);
      }
    } else if (rc.isFilesystemOnly()) {
      Iterator iter = rc.iterator();
      while (iter.hasNext()) {
        File f = ((FileProvider) (iter.next())).getFile();
        tarFile(f, tOut, f.getName(), tfs);
      }
    } else {
      Iterator iter = rc.iterator();
      while (iter.hasNext()) {
        Resource r = ((Resource) (iter.next()));
        tarResource(r, tOut, r.getName(), tfs);
      }
    }
  }
}
