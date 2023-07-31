class PlaceHold{
public void execute() throws TaskException {
    final String[] files = m_path.listFiles(getContext());
    for (int i = 0; i < files.length; i++) {
        final String file = files[i];
        getContext().warn(file);
    }
}
}