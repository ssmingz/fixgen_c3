class PlaceHold {
  public String[] mapFileName(String sourceFileName) {
    try {
      Reader stringReader = new StringReader(sourceFileName);
      ChainReaderHelper helper = new ChainReaderHelper();
      helper.setBufferSize(8192);
      helper.setPrimaryReader(stringReader);
      helper.setProject(getProject());
      Vector filterChains = new Vector();
      filterChains.add(this);
      helper.setFilterChains(filterChains);
      String result = FileUtils.safeReadFully(helper.getAssembledReader());
      if (result.length() == 0) {
        return null;
      } else {
        return new String[] {result};
      }
    } catch (BuildException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new BuildException(ex);
    }
  }
}
