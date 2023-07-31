class PlaceHold {
  private synchronized void checkNamespace(String componentName) {
    String uri = ProjectHelper.extractUriFromComponentName(componentName);
    if ("".equals(uri)) {
      uri = ProjectHelper.ANT_CORE_URI;
    }
    if (!uri.startsWith(ANTLIB_URI)) {
      return;
    }
    if (checkedNamespaces.contains(uri)) {
      return;
    }
    checkedNamespaces.add(uri);
    Typedef definer = new Typedef();
    definer.setProject(project);
    definer.setURI(uri);
    definer.setResource(uri.substring("antlib:".length()).replace('.', '/') + "/antlib.xml");
    definer.setOnError(new Typedef.OnError("ignore"));
    definer.init();
    definer.execute();
  }
}
