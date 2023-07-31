class PlaceHold {
  private Properties getTestProperties() throws Exception {
    Properties testProps = new Properties();
    FileInputStream propsFile =
        new FileInputStream(new File(System.getProperty("root"), testPropsFilePath));
    testProps.load(propsFile);
    propsFile.close();
    return testProps;
  }
}
