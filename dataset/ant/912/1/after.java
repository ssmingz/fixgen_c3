class PlaceHold {
  public void setBuildnodefiles(boolean buildNodeFiles) {
    optionalAttrs.put(BUILD_NODE_FILES, buildNodeFiles ? Boolean.TRUE : Boolean.FALSE);
  }
}
