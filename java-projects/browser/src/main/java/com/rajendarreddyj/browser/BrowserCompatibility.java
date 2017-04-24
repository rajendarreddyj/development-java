package com.rajendarreddyj.browser;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class BrowserCompatibility {

    private static final int IE_VALID_MAJORVERSION = 10;
    private static final int SAFARI_VALID_MAJORVERSION = 7;
    private static final int CHROME_VALID_MAJORVERSION = 37;
    private static final int OPERA_VALID_MAJORVERSION = 12;
    private static final int FIREFOX_VALID_MAJORVERSION = 25;

    public boolean showOldBrowserPopup(final String userAgent) {
        boolean showPopup = false;
        try {
            String browserVersion = "UNKNOWNVERSION";
            String majorVersion = "1";
            int validVersion = 1;
            String user = userAgent.toLowerCase();
            if (userAgent != null) {
                if (user.contains("msie")) {
                    String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
                    browserVersion = substring.split(" ")[1];
                    validVersion = IE_VALID_MAJORVERSION;
                } else if (user.contains("safari") && user.contains("version")) {
                    browserVersion = (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
                    validVersion = SAFARI_VALID_MAJORVERSION;
                } else if (user.contains("opr") || user.contains("opera")) {
                    validVersion = OPERA_VALID_MAJORVERSION;
                    if (user.contains("opera")) {
                        browserVersion = (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
                    } else if (user.contains("opr")) {
                        String substring = userAgent.substring(userAgent.indexOf("OPR"));
                        String Info[] = (substring.split(" ")[0]).split("/");
                        browserVersion = Info[1];
                    }
                } else if (user.contains("chrome")) {
                    String substring = userAgent.substring(userAgent.indexOf("Chrome"));
                    String Info[] = (substring.split(" ")[0]).split("/");
                    browserVersion = Info[1];
                    validVersion = CHROME_VALID_MAJORVERSION;
                } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf("mozilla/4.7") != -1)
                        || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
                    browserVersion = "UNKNOWNVERSION";
                } else if (user.contains("firefox")) {
                    String substring = userAgent.substring(userAgent.indexOf("Firefox"));
                    String Info[] = (substring.split(" ")[0]).split("/");
                    browserVersion = Info[1];
                    validVersion = FIREFOX_VALID_MAJORVERSION;
                } else if (user.contains("rv")) {
                    String substring = userAgent.substring(userAgent.indexOf("rv"), userAgent.length());
                    browserVersion = substring.substring(substring.indexOf(":") + 1, substring.indexOf(")"));
                    validVersion = IE_VALID_MAJORVERSION;
                } else {
                    browserVersion = "UNKNOWNVERSION";
                }
                if (!"UNKNOWNVERSION".equals(browserVersion)) {
                    if (browserVersion.contains(".")) {
                        majorVersion = (browserVersion.substring(0, browserVersion.indexOf("."))).trim();
                    }
                }
                int version = Integer.parseInt(majorVersion.trim());
                if (validVersion > version) {
                    showPopup = true;
                }
            }
        } catch (Exception e) {
        }
        return showPopup;
    }
}
