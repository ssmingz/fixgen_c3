class PlaceHold {
  public boolean isSelected(Resource resource) {
    if (resource.isFilesystemOnly()) {
      FileResource fileResource = ((FileResource) (resource));
      File file = fileResource.getFile();
      String filename = fileResource.getName();
      File basedir = fileResource.getBaseDir();
      return isSelected(basedir, filename, file);
    } else {
      try {
        FileUtils fu = FileUtils.getFileUtils();
        File tmpFile = fu.createTempFile("modified-", ".tmp", null, true, false);
        Resource tmpResource = new FileResource(tmpFile);
        ResourceUtils.copyResource(resource, tmpResource);
        boolean isSelected =
            isSelected(tmpFile.getParentFile(), tmpFile.getName(), resource.toLongString());
        tmpFile.delete();
        return isSelected;
      } catch (UnsupportedOperationException uoe) {
        log(
            (((("The resource '" + resource.getName())
                            + "' does not provide an InputStream, so it is not checked. ")
                        + "Akkording to 'selres' attribute value it is ")
                    + (selectResourcesWithoutInputStream ? "" : " not"))
                + "selected.",
            MSG_INFO);
        return selectResourcesWithoutInputStream;
      } catch (Exception e) {
        throw new BuildException(e);
      }
    }
  }
}
