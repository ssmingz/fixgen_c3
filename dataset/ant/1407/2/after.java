class PlaceHold {
  private void initBuildPropFile() throws Exception {
    Properties buildProps = new Properties();
    buildProps.put(testPropertyFileKey, testPropertyFile);
    buildProps.put(FNAME_KEY, NEW_FNAME);
    buildProps.put(LNAME_KEY, NEW_LNAME);
    buildProps.put(EMAIL_KEY, NEW_EMAIL);
    buildProps.put(PHONE_KEY, NEW_PHONE);
    buildProps.put(AGE_KEY, NEW_AGE);
    buildProps.put(DATE_KEY, NEW_DATE);
    FileOutputStream fos = new FileOutputStream(new File(getOutputDir(), buildPropsFilePath));
    buildProps.store(fos, null);
    fos.close();
  }
}
