class PlaceHold {
  protected FileSystem createFileSystem(final ParsedUri uri) throws FileSystemException {
    final ParsedSmbUri smbUri = ((ParsedSmbUri) (uri));
    final FileName rootName = new DefaultFileName(m_parser, smbUri.getRootUri(), "/");
    return new SmbFileSystem(rootName);
  }
}
