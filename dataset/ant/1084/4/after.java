class PlaceHold {
  private Properties getTestProperties() throws Exception {
    Properties testProps = new Properties();
    FileInputStream propsFile =
        new FileInputStream(new File(buildRule.getOutputDir(), testPropsFilePath));
    testProps.load(propsFile);
    propsFile.close();
    return testProps;
  }
}
